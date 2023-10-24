package com.example.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommonServiceException extends ResponseStatusException {

    public CommonServiceException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}