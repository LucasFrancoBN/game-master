package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.PaginatedProducts;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.RegisterProduct;
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
}
