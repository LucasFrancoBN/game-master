package io.github.lucasfrancobn.gamemaster.application.shared.filter.product;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.util.List;

public record ProductFilter(String name, List<ProductStatus> statusList) {
}
