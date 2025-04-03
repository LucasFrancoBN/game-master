package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(
        @NotBlank(message = "Email não pode ser vazio.")
        @Email(message = "E-mail precisa ser válido.")
        String email,
        @NotBlank(message = "Password não pode ser vazio.")
        String password
) {
}
