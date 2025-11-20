package com.my.snacting.domain.order.controller;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;
import com.my.snacting.domain.order.service.OrderService;
import com.my.snacting.global.auth.CurrentUserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "유저 주문서 관련 API")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "주문서 생성")
    public OrderCreateResponse createFeed(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @Valid @RequestBody OrderCreateRequest request
            ) {
        return orderService.createOrder(userId, request);
    }
}
