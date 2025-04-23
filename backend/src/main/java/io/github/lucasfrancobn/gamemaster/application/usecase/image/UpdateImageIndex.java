package io.github.lucasfrancobn.gamemaster.application.usecase.image;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;

import java.util.ArrayList;
import java.util.List;

public class UpdateImageIndex {
    private final ImageRepository repository;

    public UpdateImageIndex(ImageRepository repository) {
        this.repository = repository;
    }
    
    public void updateIndex(List<Image> images) {
        List<Image> updatedImages = new ArrayList<>();

        images.forEach(image -> {
            Image foundImage = repository.getImageByName(image.getName());
            foundImage.updateIndex(image.getIndex());
            updatedImages.add(foundImage);
        });

        repository.saveAll(updatedImages);
    }
}
