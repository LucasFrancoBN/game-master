package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.UserRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa repositoryJpa;


    @Override
    public void save(User user) {
        repositoryJpa.save(UserMapper.toEntity(user));
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return repositoryJpa.existsByEmail(email);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return repositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repositoryJpa.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public void delete(User user) {
        repositoryJpa.delete(UserMapper.toEntity(user));
    }
}
