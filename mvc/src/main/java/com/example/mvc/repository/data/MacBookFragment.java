package com.example.mvc.repository.data;

import lombok.Builder;

@Builder
public record MacBookFragment(
        String macbookName,
        Boolean chargeStatus
) {
}
