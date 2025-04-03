package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.UserNotFounException;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;

public class UpdatePassword {
    private static final String UNABLE_TO_UPDATE_PASSWORD = "Não foi possível altera a senha.";
    private final UserRepository repository;
    private final AuthService authService;

    public UpdatePassword(UserRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public void update(String email, String newPassword) {
        User loggedUser = authService.getLoggedUser();

        if(!loggedUser.getEmail().equals(email))
            throw new AccessDeniedException(UNABLE_TO_UPDATE_PASSWORD);

        User user = repository.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFounException(UNABLE_TO_UPDATE_PASSWORD));
        user.setPassword(newPassword);

        repository.save(user);
    }
}
