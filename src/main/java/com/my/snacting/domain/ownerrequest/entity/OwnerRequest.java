package com.my.snacting.domain.ownerrequest.entity;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner_requests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerRequest extends BaseEntity {

    @Id
    @Column(name = "owner_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "store_location", nullable = false)
    private String storeLocation;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price_per_person", nullable = false)
    private int pricePerPerson;

    @Builder
    public OwnerRequest(Order order, String storeLocation, String productName, int pricePerPerson) {
        this.order = order;
        this.storeLocation = storeLocation;
        this.productName = productName;
        this.pricePerPerson = pricePerPerson;
    }
}
