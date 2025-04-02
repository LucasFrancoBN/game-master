package io.github.lucasfrancobn.gamemaster.application.exception.user;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
