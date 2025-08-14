package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateProductTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UpdateProduct useCase;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldUpdateProduct() {
        var id = UUID.randomUUID();
        var updatedProduct = generateProduct();
        var product = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(authService.isLoggedUserAnAdmin()).thenReturn(true);
        doNothing().when(repository).save(any(Product.class));

        useCase.update(id, updatedProduct);

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, times(1)).save(any(Product.class));
        verify(authService, times(1)).isLoggedUserAnAdmin();
    }

    @Test
    void shouldThrowException_WhenUpdateProduct_ProductNotFound() {
        var id = UUID.randomUUID();
        var updatedProduct = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.update(id, updatedProduct))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, never()).save(any(Product.class));
        verify(authService, never()).isLoggedUserAnAdmin();
    }

    @Test
    void shouldThrowException_WhenUpdateProduct_AcessDenied() {
        var id = UUID.randomUUID();
        var updatedProduct = generateProduct();
        var product = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(authService.isLoggedUserAnAdmin()).thenReturn(false);


        assertThatThrownBy(() -> useCase.update(id, updatedProduct))
                .isNotNull()
                .isInstanceOf(AccessDeniedException.class)
                .hasMessage("Você não tem permissão para alterar o preço do produto");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, never()).save(any(Product.class));
        verify(authService, times(1)).isLoggedUserAnAdmin();
    }
}
