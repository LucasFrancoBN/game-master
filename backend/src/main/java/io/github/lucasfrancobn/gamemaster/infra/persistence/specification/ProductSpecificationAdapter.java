package io.github.lucasfrancobn.gamemaster.infra.persistence.specification;

import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecificationAdapter {
    public static Specification<ProductEntity> fromFilter(final ProductFilter filter) {
        return Specification
                .where(hasNameLike(filter.name()))
                .and(hasAnyStatus(filter.statusList()));
    }

    private static Specification<ProductEntity> hasNameLike(final String name) {
        return (root, query, cb) ->
                name == null ? cb.conjunction() : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<ProductEntity> hasAnyStatus(final List<ProductStatus> status) {
        return (root, query, cb) ->
                status == null || status.isEmpty() ? cb.conjunction() : root.get("status").in(status);
    }
}
