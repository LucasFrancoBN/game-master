package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.request.RegisterProductRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.request.UpdateProductRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.response.FullProduct;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.response.ListProduct;

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

    public static Product toDomain(UpdateProductRequest request) {
        return new Product(
                request.name(),
                request.description(),
                request.price(),
                request.weight(),
                request.status(),
                request.amount()
        );
    }

    public static Product toDomain(ProductEntity entity) {
        Product product = new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getWeight(),
                entity.getStatus(),
                entity.getAmount()
        );

        entity.getImages().forEach(ie -> product.addImage(ImageMapper.toDomain(ie)));

        return product;
    }

    public static FullProduct toFullProduct(Product product) {
        return new FullProduct(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAmount(),
                product.getPrice(),
                product.getWeight(),
                product.getStatus(),
                product.getImages().stream().map(ImageMapper::toFullImage).toList()
        );
    }

    public static ListProduct toListProduct(Product product) {
        return new ListProduct(
                product.getId().toString(),
                product.getName(),
                product.getAmount(),
                product.getPrice(),
                product.getWeight(),
                product.getStatus()
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
