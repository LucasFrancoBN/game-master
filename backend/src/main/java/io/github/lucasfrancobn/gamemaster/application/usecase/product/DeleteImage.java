package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

import java.util.UUID;

public class DeleteImage {
    private final ProductRepository repository;
    private final FileCleanupService fileCleanupService;

    public DeleteImage(ProductRepository repository, FileCleanupService fileCleanupService) {
        this.repository = repository;
        this.fileCleanupService = fileCleanupService;
    }

    public void delete(UUID id, String name) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        product.removeImage(name);

        fileCleanupService.cleanFileByFilename(name);
        repository.save(product);
    }
}
