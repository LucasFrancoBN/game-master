package io.github.lucasfrancobn.gamemaster.application.exception.user;

public class UserNotFounException extends RuntimeException {
    public UserNotFounException(String message) {
        super(message);
    }
}
