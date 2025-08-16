package io.github.lucasfrancobn.gamemaster.infra.handler;

import java.time.LocalDateTime;

import io.github.lucasfrancobn.gamemaster.application.exception.product.InvalidProductImageCountException;
import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientAlreadyExistsException;
import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientIdIsNullException;
import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.role.RoleNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.AccessDeniedException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.EmailAlreadyRegisteredException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.EmailIsNullOrBlankException;
import io.github.lucasfrancobn.gamemaster.application.exception.user.UserNotFounException;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.infra.exception.product.ReadImageException;
import io.github.lucasfrancobn.gamemaster.infra.exception.storage.CreateDirectoryException;
import io.github.lucasfrancobn.gamemaster.infra.exception.storage.StorageFileException;
import io.github.lucasfrancobn.gamemaster.infra.exception.validation.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<CustomException> handleDomainValidationException(
            DomainValidationException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        CustomException exception = new CustomException(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomException> handleIllegalArgumentException(
            DomainValidationException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        CustomException exception = new CustomException(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<CustomException> handleClientAlreadyExistsException(
        ClientAlreadyExistsException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ClientIdIsNullException.class)
    public ResponseEntity<CustomException> handleClientIdIsNullException(
        ClientIdIsNullException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<CustomException> handleClientNotFoundException(
        ClientNotFoundException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<CustomException> handleRoleNotFoundException(
        RoleNotFoundException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomException> handleAccessDeniedException(
        AccessDeniedException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<CustomException> handleEmailAlreadyRegisteredException(
        EmailAlreadyRegisteredException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(EmailIsNullOrBlankException.class)
    public ResponseEntity<CustomException> handleEmailIsNullOrBlankException(
        EmailIsNullOrBlankException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(UserNotFounException.class)
    public ResponseEntity<CustomException> handleUserNotFounException(
        UserNotFounException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(ReadImageException.class)
    public ResponseEntity<CustomException> handleReadImageException(
        ReadImageException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }
    
    @ExceptionHandler(CreateDirectoryException.class)
    public ResponseEntity<CustomException> handleCreateDirectoryException(
        CreateDirectoryException ex,
        HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        CustomException exception = new CustomException(
            LocalDateTime.now(),
            status.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }


    @ExceptionHandler(StorageFileException.class)
    public ResponseEntity<CustomException> handleStorageFileException(
            StorageFileException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        CustomException exception = new CustomException(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomException> handleProductNotFoundException(
            ProductNotFoundException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomException exception = new CustomException(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(InvalidProductImageCountException.class)
    public ResponseEntity<CustomException> handleInvalidProductImageCountException(
            InvalidProductImageCountException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        CustomException exception = new CustomException(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomException> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationException exception = new ValidationException(
                LocalDateTime.now(),
                status.value(),
                "Dado(s) inválido(s)",
                request.getRequestURI()
        );

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            exception.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(exception);
    }
}
