package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;

public class GetMe {
    private final AuthService authService;

    public GetMe(AuthService authService) {
        this.authService = authService;
    }

    public User get() {
        return authService.getLoggedUser();
    }
}
