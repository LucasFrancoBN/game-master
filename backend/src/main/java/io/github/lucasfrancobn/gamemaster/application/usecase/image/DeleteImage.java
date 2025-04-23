package io.github.lucasfrancobn.gamemaster.application.usecase.image;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

import java.util.UUID;

public class DeleteImage {
    private final ImageRepository repository;
    private final FileCleanupService fileCleanupService;

    public DeleteImage(ImageRepository repository, FileCleanupService fileCleanupService) {
        this.repository = repository;
        this.fileCleanupService = fileCleanupService;
    }

    public void delete(String name) {
        Image foundedImage = repository.getImageByName(name);

        fileCleanupService.cleanFileByFilename(name);
        repository.delete(foundedImage);
    }
}
