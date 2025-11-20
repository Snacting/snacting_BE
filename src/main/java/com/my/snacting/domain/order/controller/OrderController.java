package com.my.snacting.domain.order.controller;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;
import com.my.snacting.domain.order.service.OrderService;
import com.my.snacting.global.auth.CurrentUserId;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreateResponse createFeed(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @Valid @RequestBody OrderCreateRequest request
            ) {
        return orderService.createOrder(userId, request);
    }
}
