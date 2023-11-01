package com.example.hexagonal.framework.adapter.in.web.exception;

import com.example.hexagonal.HexagonalApplication;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleCustomException(Exception throwable, WebRequest request) {
        // Error 의 원인과 Code 로 위치를 출력
        log.error("Error [{}] => {}", throwable.hashCode(), throwable.getMessage());

        // Error 발생 원인 코드를 출력
        // 작성한 stackTrace 코드로 filter 패키지 위치 조건으로 추출
        Package pkg = HexagonalApplication.class.getPackage();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        List<StackTraceElement> stackTraceFilter = Arrays.stream(stackTrace).filter(s -> s.getClassName().contains(pkg.getName())).toList();
        stackTraceFilter.forEach(t -> log.error("Trace [" + throwable.hashCode() + "] {}", t));

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = status.getReasonPhrase();
        if (throwable instanceof ResponseStatusException exception) {
            status = (HttpStatus) exception.getStatusCode();
            message = exception.getReason();
        }
        assert message != null;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setType(URI.create(request.getContextPath()));
        problemDetail.setTitle("Error [" + throwable.hashCode() + "] => " + status.getReasonPhrase());
        return problemDetail;
    }
}
