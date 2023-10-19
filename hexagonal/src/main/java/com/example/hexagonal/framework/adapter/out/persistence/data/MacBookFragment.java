package com.example.hexagonal.framework.adapter.out.persistence.data;

import lombok.Builder;

@Builder
public record MacBookFragment(
        String macbookName,
        Boolean chargeStatus
) {
}
