package com.my.snacting.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserResponseCode {

    USER_CREATED("유저 등록 완료"),
    USER_UPDATED("유저 수정 완료"),
    USER_DELETED("유저 삭제 완료");

    private final String message;
}
