package com.example.hexagonal.framework.adapter.in.web.validator;

import com.example.hexagonal.framework.adapter.in.web.exception.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ObjectValidator {

    private final Validator validator;

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (!errors.isEmpty()) {
            StringBuilder errorDetails = new StringBuilder();
            errorDetails.append("{");

            int i = 1;
            for(ConstraintViolation<T> c : errors) {
                errorDetails.append("'").append(c.getPropertyPath()).append("'").append(":");
                errorDetails.append("'").append(c.getMessage()).append("'");

                /* 마지막이 아니라면 콤마를 찍어줍니다. */
                if(errors.size() != i) {
                    errorDetails.append(",");
                }
                i++;
            }
            errorDetails.append("}");

            throw new ObjectValidationException(errorDetails.toString());
        }
    }
}