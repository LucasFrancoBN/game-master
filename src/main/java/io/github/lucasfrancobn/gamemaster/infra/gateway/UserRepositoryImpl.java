package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.UserRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa repositoryJpa;


    @Override
    @Transactional
    public void save(User user) {
        repositoryJpa.save(UserMapper.toEntity(user));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByEmail(String email) {
        return repositoryJpa.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return repositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return repositoryJpa.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public void delete(User user) {
        repositoryJpa.delete(UserMapper.toEntity(user));
    }
}
