package com.my.snacting.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OwnerRequestErrorCode implements ErrorCode {

    OWNER_REQUEST_NOT_FOUND("사장 제안을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;
}
