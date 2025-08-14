package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProductWithMaxImages;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DeleteProductTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private FileCleanupService fileCleanupService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private DeleteProduct useCase;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void shouldDeleteProduct() {
        var id = UUID.randomUUID();
        var searchedProduct = generateProductWithMaxImages();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(searchedProduct));
        when(authService.isLoggedUserAnAdmin()).thenReturn(true);
        doNothing().when(fileCleanupService).cleanFileByFilename(anyString());
        doNothing().when(repository).delete(any(Product.class));

        useCase.delete(id);

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, times(1)).delete(any(Product.class));
        verify(fileCleanupService, times(searchedProduct.getImages().size())).cleanFileByFilename(anyString());
        verify(authService, times(1)).isLoggedUserAnAdmin();

    }

    @Test
    void shouldThrowException_WhenDeleteProduct_ProductNotFound() {
        var id = UUID.randomUUID();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.delete(id))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, never()).delete(any(Product.class));
        verify(fileCleanupService, never()).cleanFileByFilename(anyString());
        verify(authService, never()).isLoggedUserAnAdmin();

    }

    @Test
    void shouldThrowException_WhenDeleteProduct_UserIsNotAdmin() {
        var id = UUID.randomUUID();
        var searchedProduct = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(searchedProduct));
        when(authService.isLoggedUserAnAdmin()).thenReturn(false);


        assertThatThrownBy(() -> useCase.delete(id))
                .isNotNull()
                .isInstanceOf(AccessDeniedException.class)
                .hasMessage("Você não tem acesso a esse recurso");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, never()).delete(any(Product.class));
        verify(fileCleanupService, never()).cleanFileByFilename(anyString());
        verify(authService, times(1)).isLoggedUserAnAdmin();

    }
}
