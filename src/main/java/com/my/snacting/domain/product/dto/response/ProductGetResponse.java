package com.my.snacting.domain.product.dto.response;

import java.util.List;

public record ProductGetResponse(
        Long productId,
        String productName,
        List<String> categories,
        String storeLocation,
        int pricePerPerson
) {
}