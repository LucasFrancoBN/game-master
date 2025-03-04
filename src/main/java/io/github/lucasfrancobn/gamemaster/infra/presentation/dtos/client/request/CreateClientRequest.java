package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(
        @NotBlank(message = "Client id cannot be null")
        String clientId,
        @NotBlank(message = "Client Secret cannot be null")
        String clientSecret,
        @NotBlank(message = "Redirect URI cannot be null")
        String redirectUri,
        @NotBlank(message = "Scope cannot be null")
        String scope
) {
}
