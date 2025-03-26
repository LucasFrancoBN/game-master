package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ProductRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public void save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);

        repositoryJpa.save(entity);
    }
}
