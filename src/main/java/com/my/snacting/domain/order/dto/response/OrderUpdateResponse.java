package com.my.snacting.domain.order.dto.response;

public record OrderUpdateResponse(
        Long orderId,
        String message
) {
}