package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.response;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.response.FullImage;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record FullProduct(
        UUID id,
        String name,
        String description,
        Integer amount,
        BigDecimal price,
        Long weight,
        ProductStatus status,
        List<FullImage> images
) {
}
