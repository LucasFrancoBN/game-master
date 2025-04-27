package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.validation.user.EmailValidator;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String password;
    private final Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(UUID id, String email, String password) {
        if (id == null || id.toString().isBlank()) {
            throw new DomainValidationException("Id não pode estar vazio");
        }
        this.id = id;

        setEmail(email);
        setPassword(password);
    }

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if (!EmailValidator.isValid(email)) {
            throw new DomainValidationException("E-mail inválido.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
