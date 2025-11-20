package com.my.snacting.domain.ownerrequest.service;

import com.my.snacting.domain.ownerrequest.dto.OwnerRequestGetResponse;

import java.util.List;

public interface OwnerRequestService {
    List<OwnerRequestGetResponse> getOwnerRequestsByUserBudget(Long userId);
    OwnerRequestGetResponse getOwnerRequestById(Long ownerRequestId);
    void toggleOwnerRequestLike(Long userId, Long ownerRequestId);
    List<OwnerRequestGetResponse> getLikedOwnerRequests(Long userId);
}
