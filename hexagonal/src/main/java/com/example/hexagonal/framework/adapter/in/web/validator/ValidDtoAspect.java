package com.example.hexagonal.framework.adapter.in.web.validator;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidDtoAspect {

    private final ObjectValidator validator;

    @Around("@annotation(ValidDto) && args(dto)")
    public Object validation(ProceedingJoinPoint joinPoint, Object dto) throws Throwable {
        // 메소드의 DTO 파라미터를 가져옵니다.
        if (null != dto) validator.validate(dto);

        // 메소드 실행
        return joinPoint.proceed();
    }
}
