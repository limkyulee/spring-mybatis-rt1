package com.kyuleelim.admincore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CmmResponse<T> {
    private int httpStatusCode;
    private String code;
    private String message;
    private T body;

    public static <T> CmmResponse<T> success(T body) {
        return CmmResponse.<T>builder()
                .httpStatusCode(200)
                .code("20000000")
                .message("정상 처리 되었습니다.")
                .body(body)
                .build();
    }
}
