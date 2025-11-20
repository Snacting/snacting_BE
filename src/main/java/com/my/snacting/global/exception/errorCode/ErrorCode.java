package com.my.snacting.global.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getMessage();

    HttpStatus getStatus();
}
