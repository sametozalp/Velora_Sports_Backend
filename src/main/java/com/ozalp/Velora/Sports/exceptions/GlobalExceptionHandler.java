package com.ozalp.Velora.Sports.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ozalp.Velora.Sports.exceptions.errors.AuthorizationException;
import com.ozalp.Velora.Sports.exceptions.errors.InvalidTokenException;
import com.ozalp.Velora.Sports.exceptions.errors.NotMatchedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    ResponseEntity<?> handle(AuthorizationException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getLocalizedMessage()));
    }

    @ExceptionHandler(NotMatchedException.class)
    ResponseEntity<?> handle(NotMatchedException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getLocalizedMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> handle(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getLocalizedMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<?> handle(InvalidTokenException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getLocalizedMessage()));
    }

    // invalid json
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJsonParseError(HttpMessageNotReadableException ex, WebRequest request) {
        String message = "Invalid data format";

        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause instanceof InvalidFormatException) {
            message = mostSpecificCause.getMessage();
        }

        return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
    }

    // 1. Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String message = "Invalid field";

        if (!validationErrors.isEmpty()) {
            message = validationErrors.get(0);
        }

        return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.info("Error: {}", ex.getMessage());
        return new ResponseEntity<>(Map.of("message", "An unknown error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
