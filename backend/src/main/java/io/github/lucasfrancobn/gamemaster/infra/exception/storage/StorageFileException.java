package io.github.lucasfrancobn.gamemaster.infra.exception.storage;

public class StorageFileException extends RuntimeException{
    public StorageFileException(String message) {
        super(message);
    }
}
