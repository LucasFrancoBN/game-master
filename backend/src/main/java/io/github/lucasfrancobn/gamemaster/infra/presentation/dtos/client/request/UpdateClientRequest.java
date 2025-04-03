package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientRequest(
        @NotBlank(message = "Client secret não pode ser nulo")
        String clientSecret,
        @NotBlank(message = "Scope não pode ser nulo")
        String scope
) {
}
