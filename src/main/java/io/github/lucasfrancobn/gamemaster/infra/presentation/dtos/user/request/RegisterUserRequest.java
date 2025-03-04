package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "E-mail cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must contain a minimum of 8 characters.")
        String password,
        @NotBlank(message = "User needs a role to be registered")
        String authorityName
) {
}
