package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.domain.entities.User;

import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.RoleUtil.*;

public class UserUtil {
    public static User generateUser() {
        return new User(
                UUID.randomUUID(),
                "usuario.valido@exemplo.com",
                "SenhaSegura@123"
        );
    }

    public static User generateUserWithoutId() {
        return new User(
                "sem.id@exemplo.com",
                "SenhaProvisoria"
        );
    }

    public static User generateUserWithNullId() {
        return new User(
                null,
                "sem.id@exemplo.com",
                "SenhaProvisoria"
        );
    }

    public static User generateUserWithInvalidEmail() {
        return new User(
                UUID.randomUUID(),
                "email-invalido",
                "senha123"
        );
    }

    public static User generateUserWithEmptyPassword() {
        return new User(
                UUID.randomUUID(),
                "senha.vazia@exemplo.com",
                ""
        );
    }

    public static User generateAdminUser() {
        User user = generateUser();
        user.getRoles().add(generateAdminRole());
        user.getRoles().add(generateOperatorRole());
        return user;
    }
}
