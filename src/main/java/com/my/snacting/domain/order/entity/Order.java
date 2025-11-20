package com.my.snacting.domain.order.entity;

import com.my.snacting.domain.user.entity.User;
import com.my.snacting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "order_categories", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "category")
    private List<String> categories;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "budget_per_person", nullable = false)
    private int budgetPerPerson;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

    @Builder
    public Order (User user, int headcount, int totalBudget, List<String> categories, String location, String detailAddress, int budgetPerPerson, String date) {
        this.user = user;
        this.headcount = headcount;
        this.totalBudget = totalBudget;
        this.categories = categories;
        this.location = location;
        this.detailAddress = detailAddress;
        this.budgetPerPerson = budgetPerPerson;
        this.date = date;

        this.deleted = false;
    }

    public void update(int headcount, int totalBudget, List<String> categories, String detailAddress, int budgetPerPerson, String date) {
        this.headcount = headcount;
        this.totalBudget = totalBudget;
        this.categories = categories;
        this.detailAddress = detailAddress;
        this.budgetPerPerson = budgetPerPerson;
        this.date = date;
    }
}
