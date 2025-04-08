package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ProductRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.infra.persistence.specification.ProductSpecificationAdapter.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public void save(Product product) {
        log.trace("Saving product with data: {}", product);
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = repositoryJpa.save(entity);
        log.debug("Product saved successfully. Data: {}", saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResult<Product> findAll(Pagination pagination, ProductFilter filter) {
        log.debug("Finding all paginated products with pagination, name, and status: {} | {} | {}", pagination, filter.name(), filter.statusList());

        PageRequest pageRequest = PageRequest.of(pagination.pageNumber(), pagination.pageSize());
        Specification<ProductEntity> spec = fromFilter(filter);
        Page<Product> productPage = repositoryJpa.findAll(spec, pageRequest).map(ProductMapper::toDomain);

        return new PaginatedResult<>(
                productPage.getContent(),
                pagination.pageNumber(),
                pagination.pageSize(),
                productPage.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(UUID id) {
        log.debug("Finding product with id: {}", id);
        return repositoryJpa.findById(id).map(ProductMapper::toDomain);
    }
}
