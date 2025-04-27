package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;

import java.util.List;
import java.util.UUID;

public class UpdateImageIndex {
    private final ProductRepository repository;

    public UpdateImageIndex(ProductRepository repository) {
        this.repository = repository;
    }
    
    public void updateIndex(UUID id, List<Image> images) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        product.updateImageIndex(images);

        repository.save(product);
    }
}
