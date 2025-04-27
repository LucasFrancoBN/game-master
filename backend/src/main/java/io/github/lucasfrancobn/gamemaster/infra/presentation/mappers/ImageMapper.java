package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.request.UpdateImageIndexRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.response.FullImage;

public class ImageMapper {
    public static Image toDomain(ImageEntity entity) {
        return new Image(
                entity.getName(),
                entity.getPath(),
                entity.getType(),
                entity.getSize(),
                entity.getUrl(),
                entity.getIndex()
        );
    }

    public  static Image toDomainWithProduct(ImageEntity entity) {
        return new Image(
                entity.getName(),
                entity.getPath(),
                entity.getType(),
                entity.getSize(),
                entity.getUrl(),
                entity.getIndex(),
                ProductMapper.toDomain(entity.getProduct())
        );
    }

    public static Image toDomain(UpdateImageIndexRequest request) {
        return new Image(
                request.name(),
                request.path(),
                request.type(),
                request.size(),
                request.url(),
                request.index()
        );
    }

    public static FullImage toFullImage(Image image) {
        return new FullImage(
                image.getName(),
                image.getPath(),
                image.getType(),
                image.getSize(),
                image.getUrl(),
                image.getIndex()
        );
    }

    public static ImageEntity toEntity(Image domain) {
        ImageEntity entity = new ImageEntity();
        entity.setName(domain.getName());
        entity.setPath(domain.getPath());
        entity.setType(domain.getType());
        entity.setSize(domain.getSize());
        entity.setUrl(domain.getUrl());
        entity.setIndex(domain.getIndex());

        return entity;
    }

    public static ImageEntity toEntityWithProduct(Image domain) {
        ImageEntity entity = new ImageEntity();
        entity.setName(domain.getName());
        entity.setPath(domain.getPath());
        entity.setType(domain.getType());
        entity.setSize(domain.getSize());
        entity.setUrl(domain.getUrl());
        entity.setIndex(domain.getIndex());
        entity.setProduct(ProductMapper.toEntity(domain.getProduct()));

        return entity;
    }
}
