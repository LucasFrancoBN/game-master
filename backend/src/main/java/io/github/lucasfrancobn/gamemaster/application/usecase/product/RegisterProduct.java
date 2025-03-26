package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.services.StorageService;

import java.util.List;

public class RegisterProduct {
    private final ProductRepository repository;
    private final StorageService storageService;

    public RegisterProduct(ProductRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    public void registerProduct(Product product, List<byte[]> images, List<String> fileNames) {
        List<Image> uploadedImages = storageService.uploadAllFiles(images, fileNames);
        uploadedImages.forEach(product::addImage);

        repository.save(product);
    }
}
