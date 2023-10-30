package com.example.hexagonal.framework.adapter.in.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectValidationException extends ResponseStatusException {

    public ObjectValidationException(String errorDetails) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, errorDetails);
    }
}