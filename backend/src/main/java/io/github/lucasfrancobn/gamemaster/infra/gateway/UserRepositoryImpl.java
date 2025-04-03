package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.UserEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.UserRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa repositoryJpa;


    @Override
    @Transactional
    public void save(User user) {
        log.trace("Saving user with email: {}", user.getEmail());
        UserEntity saved = repositoryJpa.save(UserMapper.toEntity(user));
        log.debug("User saved successfully with email: {}", saved.getEmail());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByEmail(String email) {
        boolean exists = repositoryJpa.existsByEmail(email);
        log.trace("Checking if user with email {} exists. Exists: {}", email, exists); 
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email {}", email);
        return repositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        log.debug("Getting user by id {}", id);
        return repositoryJpa.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public void delete(User user) {
        log.trace("Deleting user by id {}", user.getId());
        repositoryJpa.delete(UserMapper.toEntity(user));
        log.debug("User deleted successfully", user);
    }
}
