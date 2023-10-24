package com.example.mvc.dto;

import lombok.Builder;

@Builder
public record MacBookDto(
        String name,
        BatteryDto battery
) {
}
