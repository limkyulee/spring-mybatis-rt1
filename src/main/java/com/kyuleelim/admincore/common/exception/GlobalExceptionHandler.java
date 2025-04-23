package com.kyuleelim.admincore.common.exception;

import com.kyuleelim.admincore.common.dto.CmmErrorResponse;
import com.kyuleelim.admincore.common.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // BizException
    @ExceptionHandler(BizException.class)
    public ResponseEntity<CmmErrorResponse<Object>> handleBizException(BizException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus().value())
                .body(CmmErrorResponse.error(errorCode));
    }

    // INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CmmErrorResponse<Object>> handleException(Exception ex) {
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus().value())
                .body(CmmErrorResponse.error(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}