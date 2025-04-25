package com.kyuleelim.admincore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmmResponse<T> {
    private int httpStatusCode;
    private String code;
    private String message;
    private T body;

    public static <T> CmmResponse<T> ok(HttpStatus status, T body) {
        return CmmResponse.<T>builder()
                .httpStatusCode(status.value())
                .code(status.value() + "00000")
                .message(status.getReasonPhrase())
                .body(body)
                .build();
    }
}
