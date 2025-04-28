package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

import java.util.UUID;

public class DeleteProduct {
    private final ProductRepository repository;
    private final FileCleanupService fileCleanupService;
    private final AuthService authService;

    public DeleteProduct(ProductRepository repository, FileCleanupService fileCleanupService, AuthService authService) {
        this.repository = repository;
        this.fileCleanupService = fileCleanupService;
        this.authService = authService;
    }

    public void delete(UUID id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        if(!authService.isLoggedUserAnAdmin())
            throw new AccessDeniedException("Você não tem acesso a esse recurso");

        product.getImages().forEach(image -> fileCleanupService.cleanFileByFilename(image.getName()));
        repository.delete(product);
    }
}
