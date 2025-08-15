package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.util.List;

import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;

public class PaginationUtil {
    public static Pagination generatePagination() {
        return new Pagination(0, 25);
    }

    public static PaginatedResult<Product> generatePaginatedResult(String name, ProductStatus status) {
        Product product = generateProduct();
        product.setName(name);
        product.setStatus(status);

        return new PaginatedResult<>(
                List.of(product),
                0,
                25,
                1
        );
    }
}
