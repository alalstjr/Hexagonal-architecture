package com.example.hexagonal.domain;

import lombok.Builder;

@Builder
public record Battery(String code, Boolean chargeStatus) {
}
