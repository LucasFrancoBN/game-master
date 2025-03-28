package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;

public class ImageMapper {
    public static Image toDomain(ImageEntity entity) {
        return new Image(
                entity.getName(),
                entity.getPath(),
                entity.getType(),
                entity.getSize()
        );
    }

    public static ImageEntity toEntity(Image domain) {
        ImageEntity entity = new ImageEntity();
        entity.setName(domain.getName());
        entity.setPath(domain.getPath());
        entity.setType(domain.getType());
        entity.setSize(domain.getSize());

        return entity;
    }
}
