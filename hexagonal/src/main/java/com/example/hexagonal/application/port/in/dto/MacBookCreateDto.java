package com.example.hexagonal.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MacBookCreateDto(
        @NotBlank(message = "맥북의 이름은 필수 입력 입니다.")
        String name,
        @NotBlank(message = "맥북의 코드는 필수 입력 입니다.")
        String code
) {
}
