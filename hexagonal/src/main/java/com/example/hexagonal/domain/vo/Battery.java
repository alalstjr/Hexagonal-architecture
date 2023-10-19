package com.example.hexagonal.domain.vo;

import lombok.Builder;

@Builder
public record Battery(String code, Boolean chargeStatus) {
}
