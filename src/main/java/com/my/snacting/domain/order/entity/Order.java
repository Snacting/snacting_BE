package com.my.snacting.domain.order.entity;

import com.my.snacting.domain.user.entity.User;
import com.my.snacting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "orders")
@Getter
@SQLRestriction("deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "headcount", nullable = false)
    private int headcount;

    @Column(name = "total_budget", nullable = false)
    private int totalBudget;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "date", nullable = false)
    private String date;

    @Builder
    public Order (User user, int headcount, int totalBudget, String category, String date) {
        this.user = user;
        this.headcount = headcount;
        this.totalBudget = totalBudget;
        this.category = category;
        this.date = date;
    }
}
