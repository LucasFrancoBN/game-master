package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.entities.validation.user.EmailValidator;

import java.util.Optional;

public class GetUserByEmail {
    private final UserRepository repository;

    public GetUserByEmail(UserRepository repository) {
        this.repository = repository;
    }

    public User get(String email) {
        if(!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

        return repository.getUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
