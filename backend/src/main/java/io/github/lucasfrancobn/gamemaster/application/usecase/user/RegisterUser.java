package io.github.lucasfrancobn.gamemaster.application.usecase.user;

import io.github.lucasfrancobn.gamemaster.application.exception.role.RoleNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.EmailAlreadyRegisteredException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.EmailIsNullOrBlankException;
import io.github.lucasfrancobn.gamemaster.application.gateway.RoleRepository;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Role;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;

public class RegisterUser {
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public RegisterUser(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public void register(String email, String encondedPassword, String authorityName) {
        if(email == null || email.isBlank())
            throw new EmailIsNullOrBlankException("E-mail não pode ser vazio");

        if(repository.existsUserByEmail(email))
            throw new EmailAlreadyRegisteredException("Email já está cadastrado.");

        Role role = roleRepository.findByAuthorityName(authorityName)
                .orElseThrow(() -> new RoleNotFoundException("Autoridade não encontrada."));

        User user = new User(email, encondedPassword);
        user.getRoles().add(role);

        repository.save(user);
    }
}
