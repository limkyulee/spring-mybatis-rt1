package com.kyuleelim.admincore.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.CONFLICT, "40900002", "사용자를 찾을 수 없습니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT, "40900001", "이미 존재하는 사용자입니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "40000001", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "50000000", "서버 오류가 발생했습니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "40000002", "찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "40000003", "이미 사용중인 아이디 입니다."),
    WRONG_PASSWORD(HttpStatus.CONFLICT, "40000004", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
