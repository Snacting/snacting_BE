package com.my.snacting.domain.order.service;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.request.OrderUpdateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;
import com.my.snacting.domain.order.dto.response.OrderGetResponse;
import com.my.snacting.domain.order.dto.response.OrderUpdateResponse;
import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.order.mapper.OrderMapper;
import com.my.snacting.domain.order.repository.OrderRepository;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.domain.user.repository.UserRepository;
import com.my.snacting.global.exception.BusinessException;
import com.my.snacting.global.exception.errorCode.OrderErrorCode;
import com.my.snacting.global.exception.errorCode.OrderResponseCode;
import com.my.snacting.global.exception.errorCode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderCreateResponse createOrder(Long userId, OrderCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        Order order = OrderMapper.toEntity(request, user);

        orderRepository.save(order);

        return new OrderCreateResponse(order.getId(), OrderResponseCode.ORDER_CREATED.getMessage());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderGetResponse getOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        Order order = orderRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(OrderErrorCode.ORDER_NOT_FOUND));

        return new OrderGetResponse(
                order.getId(),
                user.getNickname(),
                order.getHeadcount(),
                order.getTotalBudget(),
                order.getCategories(),
                order.getLocation(),
                order.getDetailAddress(),
                order.getBudgetPerPerson(),
                order.getDate()
        );
    }

    @Override
    @Transactional
    public OrderUpdateResponse updateOrder(Long userId, OrderUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        Order order = orderRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(OrderErrorCode.ORDER_NOT_FOUND));

        order.update(
                request.headcount(),
                request.totalBudget(),
                request.categories(),
                request.detailAddress(),
                request.budgetPerPerson(),
                request.date()
        );

        return new OrderUpdateResponse(order.getId(), OrderResponseCode.ORDER_UPDATED.getMessage());
    }
}
