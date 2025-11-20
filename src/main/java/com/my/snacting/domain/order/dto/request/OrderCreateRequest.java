package com.my.snacting.domain.order.dto.request;

public record OrderCreateRequest(
        int headcount,
        int totalBudget,
        String category,
        String date
) {
}
