package io.github.lucasfrancobn.gamemaster.application.gateway;

import io.github.lucasfrancobn.gamemaster.domain.entities.User;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);

    void delete(User user);

    boolean existsUserByEmail(String email);

    Optional<User> findById(UUID id);

    Optional<User> getUserByEmail(String email);
}
