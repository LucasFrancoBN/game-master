package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepositoryJpa extends JpaRepository<ImageEntity, String> {
}
