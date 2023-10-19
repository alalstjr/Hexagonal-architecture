package com.example.hexagonal.application.port.in.dto;

import lombok.Builder;

@Builder
public record MacBookCreateDto(
        String name,
        String code
) {
}
