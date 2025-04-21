package io.github.lucasfrancobn.gamemaster.domain.services;

import io.github.lucasfrancobn.gamemaster.domain.entities.User;

public interface AuthService {
    User getLoggedUser();

    boolean isLoggedUserAnAdmin();
}
