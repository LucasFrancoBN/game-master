package io.github.lucasfrancobn.gamemaster.application.gateway;

import java.util.List;
import java.util.UUID;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;

public interface ImageRepository {
    List<Image> listAllImages();

    Image getImageByName(String name);

    void saveAll(List<Image> images);

    void delete(Image image);
}
