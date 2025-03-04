package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Role;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.RoleEntity;

public class RoleMapper {
    public static Role toDomain(RoleEntity entity) {
        return new Role(
                entity.getId(),
                entity.getAuthorityName(),
                entity.getAuthority(),
                entity.getDescription()
        );
    }

    public static RoleEntity toEntity(Role domain) {
        return new RoleEntity(
                domain.getId(),
                domain.getAuthorityName(),
                domain.getAuthority(),
                domain.getDescription()
        );
    }
}
