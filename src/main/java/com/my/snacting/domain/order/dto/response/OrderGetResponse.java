package com.my.snacting.domain.order.dto.response;

import java.util.List;

public record OrderGetResponse(
        Long orderId,
        String nickname,
        int headcount,
        int totalBudget,
        List<String> categories,
        String location,
        String detailAddress,
        int budgetPerPerson,
        String date
) {
}