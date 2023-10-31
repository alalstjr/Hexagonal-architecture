package com.example.hexagonal.framework.adapter.in.web.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleCustomException(Exception throwable, WebRequest request) {
        log.error("Error {} => {}", throwable.hashCode(), throwable.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = status.getReasonPhrase();
        if (throwable instanceof ResponseStatusException exception) {
            status = (HttpStatus) exception.getStatusCode();
            message = exception.getReason();
        }
        assert message != null;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setType(URI.create(request.getContextPath()));
        problemDetail.setTitle("Error " + throwable.hashCode() + " => " + status.getReasonPhrase());
        return problemDetail;
    }
}
