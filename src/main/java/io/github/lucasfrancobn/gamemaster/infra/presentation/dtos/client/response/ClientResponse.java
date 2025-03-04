package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.response;

public record ClientResponse(
        String id,
        String clientId,
        String redirectUri,
        String scope
) {
}
