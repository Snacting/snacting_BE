package com.my.snacting.domain.order.dto.request;

import java.util.List;

public record OrderUpdateRequest(
        int headcount,
        int totalBudget,
        int budgetPerPerson,
        List<String> categories,
        String detailAddress,
        String date
) {
}