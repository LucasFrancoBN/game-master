package io.github.lucasfrancobn.gamemaster.application.usecase.product;

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

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.*;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class RegisterProductTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private RegisterProduct useCase;

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
    void shouldRegisterProduct() {
        var product = generateProduct();
        var imageBytes = generateImageBytes();
        var filenames = generateFilenames();
        var listImages = List.of(generateImage(0), generateImage(1), generateImage(2));

        when(storageService.uploadAllFiles(anyList(), anyList())).thenReturn(listImages);
        doNothing().when(repository).save(any(Product.class));

        useCase.registerProduct(product, imageBytes, filenames);

        verify(storageService, times(1)).uploadAllFiles(anyList(), anyList());
        verify(repository, times(1)).save(any(Product.class));
    }
}
