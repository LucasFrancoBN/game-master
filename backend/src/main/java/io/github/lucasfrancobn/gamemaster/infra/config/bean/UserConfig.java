package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.gateway.RoleRepository;
import io.github.lucasfrancobn.gamemaster.application.gateway.UserRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.user.*;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public RegisterUser registerUser(UserRepository userRepository, RoleRepository roleRepository) {
        return new RegisterUser(userRepository, roleRepository);
    }

    @Bean
    public UpdatePassword updatePassword(UserRepository userRepository, AuthService authService) {
        return new UpdatePassword(userRepository, authService);
    }

    @Bean
    public GetMe getMe(AuthService authService) {
        return new GetMe(authService);
    }

    @Bean
    public DeleteUser deactivateUser(UserRepository userRepository, AuthService authService) {
        return new DeleteUser(userRepository, authService);
    }

    @Bean
    public GetUserByEmail getUserByEmail(UserRepository userRepository) {
        return new GetUserByEmail(userRepository);
    }
}
