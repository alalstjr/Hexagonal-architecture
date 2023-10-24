package com.example.mvc.dto;

import lombok.Builder;

@Builder
public record BatteryDto(
        Boolean chargeStatus
) {
}
