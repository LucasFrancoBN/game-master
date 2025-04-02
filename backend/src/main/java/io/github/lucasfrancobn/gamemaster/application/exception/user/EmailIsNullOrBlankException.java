package io.github.lucasfrancobn.gamemaster.application.exception.user;

public class EmailIsNullOrBlankException extends RuntimeException {
    public EmailIsNullOrBlankException(String message) {
        super(message);
    }
}
