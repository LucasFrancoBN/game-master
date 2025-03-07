package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.response;

import java.util.Set;

public record UserResponse(
        String id,
        String email,
        Set<String> roles
) {
}
