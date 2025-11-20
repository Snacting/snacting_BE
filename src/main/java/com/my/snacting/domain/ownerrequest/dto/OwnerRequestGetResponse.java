package com.my.snacting.domain.ownerrequest.dto;

public record OwnerRequestGetResponse(
        Long ownerRequestId,
        Long orderId,
        String storeLocation,
        String productName,
        int pricePerPerson
) {
}

