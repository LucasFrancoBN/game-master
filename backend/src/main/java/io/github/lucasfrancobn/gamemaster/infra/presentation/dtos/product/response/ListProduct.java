package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.response;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.math.BigDecimal;

public record ListProduct(
        String id,
        String name,
        Integer amount,
        BigDecimal price,
        Long weight,
        ProductStatus status
) {
}
