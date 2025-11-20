package com.my.snacting.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderResponseCode {
    ORDER_CREATED("주문서 등록 완료"),
    ORDER_UPDATED("주문서 수정 완료"),
    ORDER_DELETED("주문서 삭제 완료");

    private final String message;
}
