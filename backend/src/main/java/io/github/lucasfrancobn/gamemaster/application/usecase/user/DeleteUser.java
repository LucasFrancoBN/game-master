package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.exception.user.UserNotFounException;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;

public class DeleteUser {
    private static  final String UNABLE_DEACTIVATE_USER = "Não foi possível excluir usuário.";
    private final UserRepository repository;
    private final AuthService authService;

    public DeleteUser(UserRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public void delete() {
        User loggedUser = authService.getLoggedUser();

        User user = repository.findById(loggedUser.getId())
                .orElseThrow(() -> new UserNotFounException(UNABLE_DEACTIVATE_USER));

        repository.delete(user);
    }
}
