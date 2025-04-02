package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.exception.user.EmailIsNullOrBlankException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.UserNotFounException;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;

public class GetUserByEmail {
    private final UserRepository repository;

    public GetUserByEmail(UserRepository repository) {
        this.repository = repository;
    }

    public User get(String email) {
        if(email == null || email.isBlank()) {
            throw new EmailIsNullOrBlankException("E-mail não pode estarvazio");
        }

        return repository.getUserByEmail(email).orElseThrow(() -> new UserNotFounException("Usuário não encontrado"));
    }
}
