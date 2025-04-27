package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.*;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;
import io.github.lucasfrancobn.gamemaster.domain.services.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    public RegisterProduct registerProduct(ProductRepository productRepository, StorageService storageService) {
        return new RegisterProduct(productRepository, storageService);
    }

    @Bean
    public PaginatedProducts paginatedProducts(ProductRepository productRepository) {
        return new PaginatedProducts(productRepository);
    }

    @Bean
    public GetProductById getProductById(ProductRepository productRepository) {
        return new GetProductById(productRepository);
    }

    @Bean
    public UpdateProduct updateProduct(AuthService authService, ProductRepository productRepository) {
        return new UpdateProduct(productRepository, authService);
    }

    @Bean
    public AddImageToProduct addImageToProduct(ProductRepository productRepository, StorageService storageService) {
        return new AddImageToProduct(productRepository, storageService);
    }

    @Bean
    public UpdateImageIndex updateImageIndex(ProductRepository productRepository) {
        return new UpdateImageIndex(productRepository);
    }

    @Bean
    public DeleteImage deleteImage(ProductRepository productRepository, FileCleanupService fileCleanupService) {
        return new DeleteImage(productRepository, fileCleanupService);
    }
}
