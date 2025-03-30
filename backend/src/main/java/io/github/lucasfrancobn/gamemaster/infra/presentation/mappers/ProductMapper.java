package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.RegisterProductRequest;

public class ProductMapper {
    public static Product toDomain(RegisterProductRequest request) {
        return new Product(
                request.name(),
                request.description(),
                request.price(),
                request.weight(),
                request.status(),
                request.amount()
        );
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = toEntityWithoutImages(product);

        product.getImages().forEach(i -> {
            ImageEntity ie = ImageMapper.toEntity(i);

            entity.getImages().add(ie);
        });

        entity.getImages().forEach(i -> i.setProduct(entity));

        return entity;
    }

    public static ProductEntity toEntityWithoutImages(Product product) {
        ProductEntity entity = new ProductEntity();

        if (product.getId() != null) {
            entity.setId(product.getId());
        }

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setWeight(product.getWeight());
        entity.setStatus(product.getStatus());
        entity.setAmount(product.getAmount());

        return entity;
    }
}
