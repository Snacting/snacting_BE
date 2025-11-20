package com.my.snacting.domain.order.service;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.request.OrderUpdateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;
import com.my.snacting.domain.order.dto.response.OrderGetResponse;
import com.my.snacting.domain.order.dto.response.OrderUpdateResponse;

public interface OrderService {
    OrderCreateResponse createOrder(Long userId, OrderCreateRequest request);
    OrderGetResponse getOrder(Long userId);
    OrderUpdateResponse updateOrder(Long userId, OrderUpdateRequest request);
}
