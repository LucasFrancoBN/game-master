package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ProductRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
