package io.github.lucasfrancobn.gamemaster.infra.config.security.filter;

import io.github.lucasfrancobn.gamemaster.application.usecase.user.GetUserByEmail;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.config.security.authentication.CustomAuthentication;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.UserEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtCustomAuthenticationFilter extends OncePerRequestFilter {
    private final GetUserByEmail getUserByEmail;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!deveConverter(authentication)) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = authentication.getName();
        User user = getUserByEmail.get(email);

        if(user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserEntity entity = UserMapper.toEntity(user);

        Authentication auth = new CustomAuthentication(entity);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private boolean deveConverter(Authentication authentication) {
        return authentication != null && (authentication instanceof JwtAuthenticationToken || authentication instanceof CustomAuthentication);
    }
}
