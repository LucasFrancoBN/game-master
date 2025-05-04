package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.domain.entities.Role;

import java.util.UUID;

public class RoleUtil {
    public static Role generateRole() {
        return new Role(
                UUID.randomUUID(),
                "Admin role",
                "ADMIN",
                "Administrator role"
        );
    }

    public static Role generateAdminRole() {
        return new Role(
                UUID.randomUUID(),
                "Admin role",
                "ADMIN",
                "Administrator role"
        );
    }

    public static Role generateOperatorRole() {
        return new Role(
                UUID.randomUUID(),
                "Operator role",
                "OPERATOR",
                "Operator role"
        );
    }

    public static Role generateRoleWithoutId() {
        return new Role(
                "Operator role",
                "OPERATOR",
                "Operator role"
        );
    }

    public static Role generateRoleWithNullId() {
        return new Role(
                null,
                "Operator role",
                "OPERATOR",
                "Operator role"
        );
    }

    public static Role generateRoleWithEmptyAuthorityName() {
        return new Role(
                UUID.randomUUID(),
                "",
                "ROLE_EMPTY",
                "Invalid role"
        );
    }

    public static Role generateRoleWithEmptyAuthority() {
        return new Role(
                UUID.randomUUID(),
                "Empty Authority Role",
                "",
                "Invalid role"
        );
    }
}
