package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {
    @Query("SELECT i FROM ProductEntity p JOIN p.images i")
    List<ImageEntity> findAllImages();

}
