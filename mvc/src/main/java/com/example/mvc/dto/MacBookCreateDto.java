package com.example.mvc.dto;

import lombok.Builder;

@Builder
public record MacBookCreateDto(
        String name,
        String code
) {
}
