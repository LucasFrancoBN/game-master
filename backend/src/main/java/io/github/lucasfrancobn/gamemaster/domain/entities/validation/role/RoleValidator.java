package io.github.lucasfrancobn.gamemaster.domain.entities.validation.role;

import io.github.lucasfrancobn.gamemaster.domain.entities.Role;

public class RoleValidator {
    public static void validate(final Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        if(role.getAuthority() == null || role.getAuthority().isBlank()) {
            throw new IllegalArgumentException("Authority cannot be null or empty");
        }

        if(role.getAuthorityName() == null || role.getAuthorityName().isBlank()) {
            throw new IllegalArgumentException("Authority name cannot be null or empty");        }
    }
}
