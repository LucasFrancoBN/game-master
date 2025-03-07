package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.user.*;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request.RegisterUserRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request.UpdatePasswordRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.response.UserResponse;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gamemaster/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final DeleteUser deleteUser;
    private final GetMe getMe;
    private final RegisterUser registerUser;
    private final UpdatePassword updatePassword;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        log.info("Starting register of user: {}", request);
        registerUser.register(request.email(), passwordEncoder.encode(request.password()), request.authorityName());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        log.info("Starting Update password for user: {}", request.email());
        updatePassword.update(request.email(), passwordEncoder.encode(request.password()));

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserResponse> getMe() {
        log.info("Starting get me");
        User user = getMe.get();

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        log.info("Starting delete user");
        deleteUser.delete();

        return ResponseEntity.noContent().build();
    }
}
