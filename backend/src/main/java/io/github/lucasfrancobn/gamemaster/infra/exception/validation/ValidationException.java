package io.github.lucasfrancobn.gamemaster.infra.exception.validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.lucasfrancobn.gamemaster.infra.handler.CustomException;
import lombok.Getter;

@Getter
public class ValidationException extends CustomException {
    private final List<FieldError> errors = new ArrayList<>();
    
    public ValidationException(LocalDateTime timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void addError(String field, String message) {
        errors.removeIf(e -> e.getField().equals(field));
        errors.add(new FieldError(field, message));
    }
}
