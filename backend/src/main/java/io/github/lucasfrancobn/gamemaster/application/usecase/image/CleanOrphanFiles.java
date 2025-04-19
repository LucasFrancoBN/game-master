package io.github.lucasfrancobn.gamemaster.application.usecase.image;

import java.util.List;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

public class CleanOrphanFiles {
    private ImageRepository imageRepository;
    private FileCleanupService fileCleanupService;
    
    public CleanOrphanFiles(ImageRepository imageRepository, FileCleanupService fileCleanupService) {
        this.imageRepository = imageRepository;
        this.fileCleanupService = fileCleanupService;
    }

    public void clean() {
        List<String> filenames = imageRepository.listAllImages().stream().map(i -> i.getName()).toList();
        fileCleanupService.cleanOrphanFiles(filenames);
    }
}
