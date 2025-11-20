package com.my.snacting.domain.order.service;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;

public interface OrderService {
    OrderCreateResponse createOrder(Long userId, OrderCreateRequest request);
}
