package io.github.lucasfrancobn.gamemaster.application.gateway;

import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

public interface ProductRepository {
    void save(Product product);

    PaginatedResult<Product> findAll(Pagination pagination, ProductFilter filter);
}
