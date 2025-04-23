package com.kyuleelim.admincore.common.exception;

import com.kyuleelim.admincore.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {
    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
