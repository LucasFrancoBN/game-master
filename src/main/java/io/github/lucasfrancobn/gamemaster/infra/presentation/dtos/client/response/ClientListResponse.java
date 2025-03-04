package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.response;

public record ClientListResponse(
        String id,
        String clientId,
        String scope
) {
}
