package com.my.snacting.domain.order.mapper;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {
    public static Order toEntity(OrderCreateRequest request, User user) {
        return Order.builder()
                .user(user)
                .headcount(request.headcount())
                .totalBudget(request.totalBudget())
                .budgetPerPerson(request.budgetPerPerson())
                .categories(request.categories())
                .location(user.getSchool())
                .detailAddress(request.detailAddress())
                .date(request.date())
                .build();
    }
}
