package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.request;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank(message = "Nome não pode estar vazio")
        String name,
        @NotBlank(message = "Descrição não pode estar vazio")
        String description,
        @Positive(message = "Preço precisa ser positivo")
        @Digits(integer = 16, fraction = 2)
        BigDecimal price,
        @Positive(message = "Quantidade precisa ser positivo")
        Integer amount,
        @Positive(message = "Peso precisa ser positivo")
        @Digits(integer = 5, fraction = 2)
        Long weight,
        @NotNull(message = "Status não pode ser nulo")
        ProductStatus status
) {
}
