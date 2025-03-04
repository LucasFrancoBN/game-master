package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request;

public record UpdateClientRequest(
        String clientSecret,
        String scope
) {
}
