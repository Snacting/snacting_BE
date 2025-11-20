package com.my.snacting.domain.ownerrequest.controller;

import com.my.snacting.domain.ownerrequest.dto.OwnerRequestGetResponse;
import com.my.snacting.domain.ownerrequest.service.OwnerRequestService;
import com.my.snacting.global.auth.CurrentUserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner-requests")
@RequiredArgsConstructor
@Tag(name = "Owner Request API", description = "사장 제안 관련 API")
public class OwnerRequestController {

    private final OwnerRequestService ownerRequestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저 예산 내 사장 제안 조회", description = "현재 로그인한 유저의 주문서의 일인 가격 내에 있는 사장 제안을 조회합니다.")
    public List<OwnerRequestGetResponse> getOwnerRequestsByUserBudget(
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        return ownerRequestService.getOwnerRequestsByUserBudget(userId);
    }

    @GetMapping("/{ownerRequestId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "사장 제안 단건 조회", description = "사장 제안 ID로 특정 사장 제안을 조회합니다.")
    public OwnerRequestGetResponse getOwnerRequestById(
            @PathVariable Long ownerRequestId
    ) {
        return ownerRequestService.getOwnerRequestById(ownerRequestId);
    }

    @PostMapping("/{ownerRequestId}/like")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "사장 제안 좋아요 토글", description = "사장 제안에 좋아요를 추가하거나 취소합니다.")
    public void toggleOwnerRequestLike(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @PathVariable Long ownerRequestId
    ) {
        ownerRequestService.toggleOwnerRequestLike(userId, ownerRequestId);
    }

    @GetMapping("/liked")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "좋아요한 사장 제안 조회", description = "현재 로그인한 유저가 좋아요한 사장 제안 목록을 조회합니다.")
    public List<OwnerRequestGetResponse> getLikedOwnerRequests(
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        return ownerRequestService.getLikedOwnerRequests(userId);
    }
}
