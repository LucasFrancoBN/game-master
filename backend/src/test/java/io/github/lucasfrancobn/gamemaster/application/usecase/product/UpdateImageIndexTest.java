package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.generateImage;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateImageIndexTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private UpdateImageIndex useCase;

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
    void shouldUpdateImage() {
        var listImages = List.of(generateImage(0), generateImage(1), generateImage(2));
        var id = UUID.randomUUID();
        var product = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        doNothing().when(repository).save(any(Product.class));

        useCase.updateIndex(id, listImages);

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldThrowException_WhenUpdateImage_ProductNotFound() {
        var listImages = List.of(generateImage(0), generateImage(1), generateImage(2));
        var id = UUID.randomUUID();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.updateIndex(id, listImages))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(any(UUID.class));
        verify(repository, never()).save(any(Product.class));
    }
}
