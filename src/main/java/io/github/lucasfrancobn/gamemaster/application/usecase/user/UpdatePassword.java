package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;

public class UpdatePassword {
    private static final String UNABLE_TO_UPDATE_PASSWORD = "Unable to update password.";
    private final UserRepository repository;
    private final AuthService authService;

    public UpdatePassword(UserRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public void update(String email, String newPassword) {
        User loggedUser = authService.getLoggedUser();

        if(!loggedUser.getEmail().equals(email))
            throw new IllegalArgumentException(UNABLE_TO_UPDATE_PASSWORD);

        User user = repository.getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(UNABLE_TO_UPDATE_PASSWORD));
        user.setPassword(newPassword);

        repository.save(user);
    }
}
