package com.kyuleelim.admincore.common.dto.response;

import com.kyuleelim.admincore.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CmmErrorResponse<T> {
    private Integer status;
    private String code;
    private String message;

    public static <T> CmmErrorResponse<T> error(ErrorCode errorCode) {
        return CmmErrorResponse.<T>builder()
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.getCode() + "00000")
                .message(errorCode.getMessage())
                .build();
    }
}
