package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientRequest(
        @NotBlank(message = "Client secret cannot be null")
        String clientSecret,
        @NotBlank(message = "Scope cannot be null")
        String scope
) {
}
