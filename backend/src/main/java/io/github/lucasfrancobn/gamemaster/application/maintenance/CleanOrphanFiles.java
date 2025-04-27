package io.github.lucasfrancobn.gamemaster.application.maintenance;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

import java.util.List;

public class CleanOrphanFiles {
    private final ProductRepository productRepository;
    private final FileCleanupService fileCleanupService;
    
    public CleanOrphanFiles(ProductRepository productRepository, FileCleanupService fileCleanupService) {
        this.productRepository = productRepository;
        this.fileCleanupService = fileCleanupService;
    }

    public void clean() {
        List<String> filenames = productRepository.findAllImages().stream().map(Image::getName).toList();
        fileCleanupService.cleanOrphanFiles(filenames);
    }
}
