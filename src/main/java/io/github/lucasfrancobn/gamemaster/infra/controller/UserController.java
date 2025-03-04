package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.user.*;
import io.github.lucasfrancobn.gamemaster.domain.entities.User;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request.RegisterUserRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.request.UpdatePasswordRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.user.response.UserResponse;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gamemaster/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final DeleteUser deleteUser;
    private final GetMe getMe;
    private final RegisterUser registerUser;
    private final UpdatePassword updatePassword;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        registerUser.register(request.email(), passwordEncoder.encode(request.password()), request.authorityName());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        updatePassword.update(request.email(), passwordEncoder.encode(request.password()));

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserResponse> getMe() {
        User user = getMe.get();

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        deleteUser.delete();

        return ResponseEntity.noContent().build();
    }
}
