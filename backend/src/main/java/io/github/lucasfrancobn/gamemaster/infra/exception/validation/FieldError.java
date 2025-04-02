package io.github.lucasfrancobn.gamemaster.infra.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldError {
    private final String field;
    private final String message;
}
