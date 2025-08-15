package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
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

public class DeleteImageTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private FileCleanupService fileCleanupService;

    @InjectMocks
    private DeleteImage useCase;

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
    void shouldDeleteImage() {
        var id = UUID.randomUUID();
        var searchedProduct = generateProductWithMaxImages();
        var name = searchedProduct.getImages().getFirst().getName();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(searchedProduct));
        doNothing().when(fileCleanupService).cleanFileByFilename(anyString());

        useCase.delete(id, name);

        verify(repository, times(1)).findById(any(UUID.class));
        verify(fileCleanupService, times(1)).cleanFileByFilename(anyString());
        verify(repository, times(1)).save(any(Product.class));

    }

    @Test
    void shouldThrowException_WhenDeleteImage_ProductNotFound() {
        var id = UUID.randomUUID();
        var searchedProduct = generateProductWithMaxImages();
        var name = searchedProduct.getImages().getFirst().getName();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.delete(id, name))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(fileCleanupService, never()).cleanFileByFilename(anyString());
        verify(repository, never()).save(any(Product.class));
    }
}
