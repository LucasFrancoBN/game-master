package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByAuthorityName(String authorityName);
}
