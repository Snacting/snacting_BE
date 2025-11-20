package com.my.snacting.domain.ownerrequest.repository;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.ownerrequest.entity.OwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRequestRepository extends JpaRepository<OwnerRequest, Long> {
    List<OwnerRequest> findByOrder(Order order);
}

