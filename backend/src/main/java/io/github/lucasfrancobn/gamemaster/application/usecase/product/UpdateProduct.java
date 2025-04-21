package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;

import java.util.UUID;

public class UpdateProduct {
    private final ProductRepository repository;
    private final AuthService authService;

    public UpdateProduct(ProductRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public void update(UUID id, Product updatedProduct) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        if(!authService.isLoggedUserAnAdmin() && product.getPrice() != null)
            throw new AccessDeniedException("Você não tem permissão para alterar o preço do produto");

        product.updateProduct(updatedProduct);

        repository.save(product);
    }
}
