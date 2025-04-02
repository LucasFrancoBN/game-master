package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "E-mail não pode ser vazio.")
        String email,
        @NotBlank(message = "Senha não pode ser vazio.")
        @Size(min = 8, message = "Senha precisa conter no mínimo 8 caracteres.")
        String password,
        @NotBlank(message = "Usuário precisa de uma autoridade registrada.")
        String authorityName
) {
}
