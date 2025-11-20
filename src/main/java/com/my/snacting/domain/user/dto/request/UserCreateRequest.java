package com.my.snacting.domain.user.dto.request;

public record UserCreateRequest(
        String nickname,
        String school
) {
}
