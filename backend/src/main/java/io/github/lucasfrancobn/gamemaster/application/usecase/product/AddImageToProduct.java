package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.InvalidProductImageCountException;
import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.StorageService;

import java.util.List;
import java.util.UUID;

public class AddImageToProduct {
    private final static int MAX_ALLOWED = 5;

    private final ProductRepository productRepository;
    private final StorageService storageService;

    public AddImageToProduct(ProductRepository productRepository, StorageService storageService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    public void add(UUID id, List<byte[]> images, List<String> filenames) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        int currentImageCount = product.getImages().size();
        int attemptedToAdd = images.size();

        if(currentImageCount >= MAX_ALLOWED || currentImageCount + attemptedToAdd > MAX_ALLOWED)
            throw new InvalidProductImageCountException(
                    String.format(
                            "Não é possível adicionar %d imagens. Contagem atual de imagens: %d (máximo permitido: %d)",
                            attemptedToAdd, currentImageCount, MAX_ALLOWED
                    )
            );

        List<Image> uploadedFiles = storageService.uploadAllFiles(images, filenames);

        uploadedFiles.forEach(product::addImage);

        productRepository.save(product);
    }
}
