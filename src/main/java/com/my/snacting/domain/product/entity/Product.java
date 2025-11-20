package com.my.snacting.domain.product.entity;

import com.my.snacting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ElementCollection
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category")
    private List<String> categories;

    @Column(name = "store_location", nullable = false)
    private String storeLocation;

    @Column(name = "price_per_person", nullable = false)
    private int pricePerPerson;

    @Builder
    public Product(String productName, List<String> categories, String storeLocation, int pricePerPerson) {
        this.productName = productName;
        this.categories = categories;
        this.storeLocation = storeLocation;
        this.pricePerPerson = pricePerPerson;
    }
}