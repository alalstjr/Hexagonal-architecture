package com.example.hexagonal.framework.adapter.in.web.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Method;
import java.util.Set;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidDtoAspect {

    private final ObjectValidator validator;
    @Around("@annotation(ValidDto)")
    public Object getAccountInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드의 파라미터를 가져옵니다.
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            boolean annotationPresent = arg.getClass().isAnnotationPresent(RequestBody.class);
            if (arg != null && arg.getClass().isAnnotationPresent(RequestBody.class)) {
                // @RequestBody 어노테이션이 적용된 파라미터인 경우 검증 수행
                validator.validate(arg);
            }
        }

        // 메소드 실행
        return joinPoint.proceed();
    }
}
