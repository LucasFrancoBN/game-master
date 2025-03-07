package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.RoleRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Role;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.RoleRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleRepositoryJpa repositoryJpa;

    @Override
    public Optional<Role> findByAuthorityName(String authorityName) {
        return repositoryJpa.findByAuthorityName(authorityName)
                .map(RoleMapper::toDomain);
    }
}
