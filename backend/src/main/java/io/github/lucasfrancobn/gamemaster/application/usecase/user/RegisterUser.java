package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.gateway.RoleRepository;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Role;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.entities.validation.user.EmailValidator;

public class RegisterUser {
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public RegisterUser(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public void register(String email, String encondedPassword, String authorityName) {
        if(!EmailValidator.isValid(email))
            throw new IllegalArgumentException("Invalid email");

        if(repository.existsUserByEmail(email))
            throw new IllegalArgumentException("Email already registered");

        Role role = roleRepository.findByAuthorityName(authorityName)
                .orElseThrow(() -> new IllegalArgumentException("Authority not found"));

        User user = new User(email, encondedPassword);
        user.getRoles().add(role);

        repository.save(user);
    }
}
