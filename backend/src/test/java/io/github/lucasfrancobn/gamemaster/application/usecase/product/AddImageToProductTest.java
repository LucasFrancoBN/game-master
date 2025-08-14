package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.InvalidProductImageCountException;
import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.StorageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.*;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class AddImageToProductTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private AddImageToProduct useCase;

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
    void shouldAddImageToProduct() {
        var id = UUID.randomUUID();
        var images = generateImageBytes();
        var filenames = generateFilenames();
        var searchedProduct = generateProduct();
        var listImages = List.of(generateImage(0), generateImage(1), generateImage(2));


        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(searchedProduct));
        when(storageService.uploadAllFiles(anyList(), anyList())).thenReturn(listImages);
        doNothing().when(repository).save(any(Product.class));

        useCase.add(id, images, filenames);

        verify(repository, times(1)).findById(any(UUID.class));
        verify(storageService, times(1)).uploadAllFiles(anyList(), anyList());
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldThrowException_WhenAddImageToProduct_ProductNotFound() {
        var id = UUID.randomUUID();
        var images = generateImageBytes();
        var filenames = generateFilenames();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.add(id, images, filenames))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");


        verify(repository, times(1)).findById(any(UUID.class));
        verify(storageService, never()).uploadAllFiles(anyList(), anyList());
        verify(repository, never()).save(any(Product.class));
    }

    @Test
    void shouldThrowException_WhenAddImageToProduct_MaximumNumberOfImagesReached() {
        var id = UUID.randomUUID();
        var images = generateSixImageBytes();
        var filenames = generateSixFilenames();
        var searchedProduct = generateProduct();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(searchedProduct));

        assertThatThrownBy(() -> useCase.add(id, images, filenames))
                .isNotNull()
                .isInstanceOf(InvalidProductImageCountException.class)
                .hasMessage(
                        String.format(
                                "Não é possível adicionar %d imagens. Contagem atual de imagens: %d (máximo permitido: %d)",
                                images.size(), searchedProduct.getImages().size(), 5
                        )
                );


        verify(repository, times(1)).findById(any(UUID.class));
        verify(storageService, never()).uploadAllFiles(anyList(), anyList());
        verify(repository, never()).save(any(Product.class));
    }
}
