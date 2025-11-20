package com.my.snacting.domain.order.service;

import com.my.snacting.domain.order.dto.request.OrderCreateRequest;
import com.my.snacting.domain.order.dto.response.OrderCreateResponse;
import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.order.mapper.OrderMapper;
import com.my.snacting.domain.order.repository.OrderRepository;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.domain.user.repository.UserRepository;
import com.my.snacting.global.exception.BusinessException;
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
}
