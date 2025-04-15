package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.response.FullImage;

public class ImageMapper {
    public static Image toDomain(ImageEntity entity) {
        return new Image(
                entity.getName(),
                entity.getPath(),
                entity.getType(),
                entity.getSize(),
                entity.getUrl()
        );
    }

    public static FullImage toFullImage(Image image) {
        return new FullImage(
                image.getName(),
                image.getPath(),
                image.getType(),
                image.getSize(),
                image.getUrl()
        );
    }

    public static ImageEntity toEntity(Image domain) {
        ImageEntity entity = new ImageEntity();
        entity.setName(domain.getName());
        entity.setPath(domain.getPath());
        entity.setType(domain.getType());
        entity.setSize(domain.getSize());
        entity.setUrl(domain.getUrl());

        return entity;
    }
}
