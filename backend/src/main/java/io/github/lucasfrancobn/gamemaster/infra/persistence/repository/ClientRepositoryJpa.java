package io.github.lucasfrancobn.gamemaster.infra.persistence.repository;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, UUID> {
    boolean existsByClientId(String clientId);

    Optional<ClientEntity> findByClientId(String clientId);
}
