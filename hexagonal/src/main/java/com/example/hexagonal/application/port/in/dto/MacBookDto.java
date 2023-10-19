package com.example.hexagonal.application.port.in.dto;

import lombok.Builder;

@Builder
public record MacBookDto(
        String name,
        BatteryDto battery
) {
}
