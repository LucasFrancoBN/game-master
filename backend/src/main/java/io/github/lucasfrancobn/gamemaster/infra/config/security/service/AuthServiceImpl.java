package io.github.lucasfrancobn.gamemaster.infra.config.security.service;

import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.domain.services.AuthService;
import io.github.lucasfrancobn.gamemaster.infra.config.security.authentication.CustomAuthentication;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {
    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if( auth instanceof CustomAuthentication customAuthentication) {
            return UserMapper.toDomain(customAuthentication.getUser());
        }

        return null;
    }

    @Override
    public boolean isLoggedUserAnAdmin() {
        User loggedUser = this.getLoggedUser();
        return loggedUser != null && loggedUser.getRoles().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
    }
}
