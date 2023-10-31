package com.example.hexagonal.framework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersistenceException extends ResponseStatusException {

    public PersistenceException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
