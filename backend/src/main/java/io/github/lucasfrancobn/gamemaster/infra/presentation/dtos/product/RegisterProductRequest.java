package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RegisterProductRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @Positive(message = "Price must be positive")
        @Digits(integer = 16, fraction = 2)
        BigDecimal price,
        @Positive(message = "Weight must be positive")
        @Digits(integer = 5, fraction = 2)
        Long weight,
        @NotNull
        ProductStatus status
) {
}
