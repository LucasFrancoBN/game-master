package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Role;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.RoleEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.UserEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.response.UserResponse;

import java.util.stream.Collectors;

public class UserMapper {
    public static User toDomain(UserEntity entity) {
        User user = new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword()
        );

        entity.getRoles().forEach(role -> {
            user.getRoles().add(
                    new Role(
                            role.getId(),
                            role.getAuthorityName(),
                            role.getAuthority(),
                            role.getDescription()
                    )
            );
        });

        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        user.getRoles().forEach(role -> {
            entity.getRoles().add(
                    new RoleEntity(
                         role.getId(),
                         role.getAuthorityName(),
                         role.getAuthority(),
                         role.getDescription()
                    )
            );
        });

        return entity;
    }

    public static UserResponse toResponse(User domainEntity) {
        return new UserResponse(
                domainEntity.getId().toString(),
                domainEntity.getEmail(),
                domainEntity.getRoles().stream().map(Role::getAuthorityName).collect(Collectors.toSet())
        );
    }
}
