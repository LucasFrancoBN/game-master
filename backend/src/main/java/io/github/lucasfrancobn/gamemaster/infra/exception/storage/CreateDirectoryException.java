package io.github.lucasfrancobn.gamemaster.infra.exception.storage;

public class CreateDirectoryException extends RuntimeException {
    public CreateDirectoryException(String message) {
        super(message);
    }
}
