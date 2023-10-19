package com.example.hexagonal.application.port.in.dto;

import lombok.Builder;

@Builder
public record BatteryDto(
        Boolean chargeStatus
) {
}
