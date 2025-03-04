package io.github.lucasfrancobn.gamemaster.infra.config.security.authentication;

import io.github.lucasfrancobn.gamemaster.application.usecase.user.GetUserByEmail;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.UserEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final GetUserByEmail getUserByEmail;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = getUserByEmail.get(email);

        if (user == null)
            throw new UsernameNotFoundException("E-mail or password incorrect");

        UserEntity userEntity = UserMapper.toEntity(user);

        if(!passwordEncoder.matches(password, userEntity.getPassword()))
            throw new UsernameNotFoundException("E-mail or password incorrect");

        return new CustomAuthentication(userEntity);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
