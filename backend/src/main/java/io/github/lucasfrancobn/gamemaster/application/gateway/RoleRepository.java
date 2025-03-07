package io.github.lucasfrancobn.gamemaster.application.gateway;

import io.github.lucasfrancobn.gamemaster.domain.entities.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByAuthorityName(String authorityName);
}
