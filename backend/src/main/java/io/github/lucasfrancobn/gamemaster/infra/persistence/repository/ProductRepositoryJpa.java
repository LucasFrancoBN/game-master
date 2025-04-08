package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {
}
