package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email can be valid")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
