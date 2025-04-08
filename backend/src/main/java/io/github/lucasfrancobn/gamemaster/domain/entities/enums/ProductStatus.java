package io.github.lucasfrancobn.gamemaster.domain.entities.enums;

import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

public enum ProductStatus {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    OUT_OF_STOCK("out_of_stock"),
    DISCOUNTED("discounted");

    private final String status;

    private ProductStatus(String status) {
        this.status = status;
    }

    public static ProductStatus fromString(String string) {
        if(string == null) return null;

        for(ProductStatus productStatus : ProductStatus.values()) {
            if(productStatus.status.equalsIgnoreCase(string)) return productStatus;
        }

        throw new DomainValidationException("Status inválido");
    }
}
