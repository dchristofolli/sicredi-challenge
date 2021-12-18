package com.dchristofolli.sicredichallenge.exception;

import com.mongodb.DuplicateKeyException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Handler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ErrorModel.builder()
            .error(e.getClass().getName())
            .message("Invalid form")
            .status(HttpStatus.BAD_REQUEST)
            .formErrors(errors)
            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgendaNotFoundException.class)
    public ErrorModel apiExceptionValidator(AgendaNotFoundException e) {
        return ErrorModel.builder()
            .message(e.getMessage())
            .error(e.getClass().getName())
            .status(e.getStatus())
            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InactiveSessionException.class)
    public ErrorModel inactiveSessionExceptionValidator(InactiveSessionException e) {
        return ErrorModel.builder()
            .message(e.getMessage())
            .error(e.getClass().getName())
            .status(e.getStatus())
            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SessionNotFoundException.class)
    public ErrorModel sessionNotFoundExceptionValidator(InactiveSessionException e) {
        return ErrorModel.builder()
            .message(e.getMessage())
            .error(e.getClass().getName())
            .status(e.getStatus())
            .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorModel handleDuplicateKeyException(DuplicateKeyException e) {
        return ErrorModel.builder()
            .message("The value already exists in the database")
            .error(e.getClass().getName())
            .status(HttpStatus.BAD_REQUEST)
            .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FeignException.class)
    public ErrorModel feignNotFoundException(FeignException e) {
        return ErrorModel.builder()
            .message("Invalid CPF number")
            .error(e.getClass().getName())
            .status(HttpStatus.BAD_REQUEST)
            .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel handleException(Exception e) {
        return ErrorModel.builder()
            .message("Unexpected Error")
            .error(e.getClass().getName())
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
    }
}