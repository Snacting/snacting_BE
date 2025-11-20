package com.my.snacting.domain.user.dto.response;

public record UserCreateResponse(
        Long userId,
        String message,
        String accessToken
) {
}
