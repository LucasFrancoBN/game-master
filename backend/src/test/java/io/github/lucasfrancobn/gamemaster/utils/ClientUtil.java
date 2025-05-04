package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

import java.util.UUID;

public class ClientUtil {
    public static Client generateClient() {
        return new Client(
                UUID.randomUUID(),
                "client-id",
                "client-secret",
                "http://localhost:8080/authorize",
                "read write"
        );
    }

    public static Client generateClientWithNullId() {
        return new Client(
                null,
                "client-id",
                "client-secret",
                "http://localhost:8080/authorize",
                "read write"
        );
    }

    public static Client generateClientWithBlankClientId() {
        return new Client(
                UUID.randomUUID(),
                "",
                "client-secret",
                "http://localhost:8080/authorize",
                "read write"
        );
    }

    public static Client generateClientWithBlankClientSecret() {
        return new Client(
                UUID.randomUUID(),
                "client-id",
                "",
                "http://localhost:8080/authorize",
                "read write"
        );
    }

    public static Client generateClientWithInvalidRedirectUri() {
        return new Client(
                UUID.randomUUID(),
                "client-id",
                "client-secret",
                "invalid-url",
                "read write"
        );
    }

    public static Client generateClientWithBlankScope() {
        return new Client(
                UUID.randomUUID(),
                "client-id",
                "client-secret",
                "http://localhost:8080/authorize",
                ""
        );
    }
}
