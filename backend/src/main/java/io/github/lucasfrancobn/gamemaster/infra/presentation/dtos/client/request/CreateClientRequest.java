package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(
        @NotBlank(message = "Client id não pode ser nulo")
        String clientId,
        @NotBlank(message = "Client Secret não pode ser nulo")
        String clientSecret,
        @NotBlank(message = "Redirect URI não pode ser nulo")
        String redirectUri,
        @NotBlank(message = "Scope não pode ser nulo")
        String scope
) {
}
