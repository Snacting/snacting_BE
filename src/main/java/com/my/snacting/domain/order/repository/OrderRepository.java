package com.my.snacting.domain.order.repository;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}