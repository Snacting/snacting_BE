package com.my.snacting.domain.productlike.repository;

import com.my.snacting.domain.product.entity.Product;
import com.my.snacting.domain.productlike.entity.ProductLike;
import com.my.snacting.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    Optional<ProductLike> findByUserAndProduct(User user, Product product);
    List<ProductLike> findByUser(User user);
    boolean existsByUserAndProduct(User user, Product product);
}

