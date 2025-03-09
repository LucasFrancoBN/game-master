package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, String> {
}
