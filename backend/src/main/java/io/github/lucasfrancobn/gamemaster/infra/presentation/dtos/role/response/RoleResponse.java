package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.role.response;

public record RoleResponse(
        String id,
        String authorityName,
        String authority,
        String description
) {
}
