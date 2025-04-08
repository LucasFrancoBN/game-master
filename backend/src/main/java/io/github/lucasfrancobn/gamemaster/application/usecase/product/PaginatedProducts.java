package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.util.List;

public class PaginatedProducts {
    private final ProductRepository repository;

    public PaginatedProducts(ProductRepository repository) {
        this.repository = repository;
    }

    public PaginatedResult<Product> getProducts(Pagination pagination, String name, List<ProductStatus> status) {
        ProductFilter filter = new ProductFilter(name, status);
        return repository.findAll(pagination, filter);
    }
}
