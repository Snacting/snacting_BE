package com.my.snacting.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode{

    USER_NOT_FOUND("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_NICKNAME_REQUIRED("유저 닉네임은 필수입니다.", HttpStatus.BAD_REQUEST),
    USER_SCHOOL_REQUIRED("유저 학교는 필수입니다.", HttpStatus.BAD_REQUEST);


    private final String message;
    private final HttpStatus status;
}
