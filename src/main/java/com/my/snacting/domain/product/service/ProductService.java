package com.my.snacting.domain.product.service;

import com.my.snacting.domain.product.dto.response.ProductGetResponse;

import java.util.List;

public interface ProductService {
    List<ProductGetResponse> getAllProducts(List<String> categories);
    List<ProductGetResponse> getProductsByUserBudget(Long userId, List<String> categories);
    ProductGetResponse getProductById(Long productId);

    void toggleProductLike(Long userId, Long productId);
    List<ProductGetResponse> getLikedProducts(Long userId);
}