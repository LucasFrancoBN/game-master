package io.github.lucasfrancobn.gamemaster.application.gateway;

import java.util.List;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;

public interface ImageRepository {
    List<Image> listAllImages();
}
