package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;

public class ProductUtil {
    public static Product generateProduct() {
        return new Product(
                UUID.randomUUID(),
                "Console NextGen",
                "Console de última geração com 1TB de armazenamento e resolução 4K. " +
                        "Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(4999.90),
                3500L,
                ProductStatus.AVAILABLE,
                50
        );
    }

    public static Product generateProduct(
            String name,
            String description,
            BigDecimal price,
            Long weight,
            ProductStatus status,
            Integer amount
    ) {
        return new Product(
                name,
                description,
                price,
                weight,
                status,
                amount
        );
    }

    public static Product generateProductWithIdNull() {
        return new Product(
                null,
                "Console NextGen",
                "Console de última geração com 1TB de armazenamento e resolução 4K. " +
                        "Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(4999.90),
                3500L,
                ProductStatus.AVAILABLE,
                50
        );
    }

    public static Product generateProductWithShortName() {
        return new Product(
                UUID.randomUUID(),
                "A",
                "Descrição válida com mais de trinta caracteres necessários.",
                BigDecimal.valueOf(2999.90),
                2500L,
                ProductStatus.AVAILABLE,
                30
        );
    }

    public static Product generateProductWithLongName() {
        String longName = "Console Super Mega Hyper Ultra Deluxe Edition Limited Version Pro Max Xtreme Plus 50000000000000000000";
        return new Product(
                UUID.randomUUID(),
                longName,
                "Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(5999.90),
                4000L,
                ProductStatus.UNAVAILABLE,
                15
        );
    }

    public static Product generateProductWithShortDescription() {
        return new Product(
                UUID.randomUUID(),
                "Console Básico",
                "Descrição muito curta.",
                BigDecimal.valueOf(999.90),
                1500L,
                ProductStatus.AVAILABLE,
                100
        );
    }

    public static Product generateProductWithZeroPrice() {
        return new Product(
                UUID.randomUUID(),
                "Produto Grátis",
                "Descrição válida com mais de trinta caracteres necessários.",
                BigDecimal.ZERO,
                500L,
                ProductStatus.AVAILABLE,
                200
        );
    }

    public static Product generateProductWithNegativePrice() {
        return new Product(
                UUID.randomUUID(),
                "Produto com Desconto",
                "Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(-100.00),
                1000L,
                ProductStatus.AVAILABLE,
                10
        );
    }

    public static Product generateProductWithZeroWeight() {
        return new Product(
                UUID.randomUUID(),
                "Produto Leve",
                "Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(199.90),
                0L,
                ProductStatus.AVAILABLE,
                75
        );
    }

    public static Product generateProductWithNegativeAmount() {
        return new Product(
                UUID.randomUUID(),
                "Produto Esgotado",
                "Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(299.90),
                500L,
                ProductStatus.UNAVAILABLE,
                -5
        );
    }

    public static Product generateProductWithNullStatus() {
        return new Product(
                UUID.randomUUID(),
                "Produto Sem Status",
                "Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.",
                BigDecimal.valueOf(399.90),
                800L,
                null,
                20
        );
    }

    public static Product generateProductWithMaxImages() {
        Product product = generateProduct();
        IntStream.range(0, 5).forEach(i ->
                product.addImage(ImageUtil.generateImage(i))
        );
        return product;
    }

    public static Product generateProductWithInvalidImage() {
        Product product = generateProduct();
        product.addImage(ImageUtil.generateImageWithInvalidUrl());
        return product;
    }
}
