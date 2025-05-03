package com.kyuleelim.admincore.common.exception;

import com.kyuleelim.admincore.common.dto.response.CmmResponseEntity;
import com.kyuleelim.admincore.common.dto.response.CmmResult;
import com.kyuleelim.admincore.config.ExceptionInfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.Map;

/**
 * ===========================================
 * Project      : admin-core-api
 * File Name    : GlobalExceptionHandler.java
 * Author       : limkyulee
 * Created Date : 2025. 5. 3. 오후 7:23
 * Updated Date : 2025. 5. 3. 오후 7:23
 * Description  : global exception handler
 * ===========================================
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionInfoConfig exceptionInfoConfig;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody CmmResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws Exception {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[")
                    .append(fieldError.getField())
                    .append("](은)는 ")
                    .append(fieldError.getDefaultMessage())
                    .append(". 입력된 값: [")
                    .append(fieldError.getRejectedValue())
                    .append("]");
        }

        CmmResult resultDto = getResultDto(ex.getClass().getName());
        int statusCode = Integer.valueOf(resultDto.getStatus());
        CmmResult<Void> result = new CmmResult<Void>();
        result.setHttpStatusCode(statusCode);
        result.setCode(resultDto.getCode());
        result.setMessage(resultDto.getMessage());
        result.setDetailMessage(builder.toString());
        return new CmmResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }
//
//    @ExceptionHandler(BizException.class)
//    public ResponseEntity<CmmErrorResponse<Object>> handleBizException(BizException ex) {
//        ErrorCode errorCode = ex.getErrorCode();
//        return ResponseEntity
//                .status(errorCode.getHttpStatus().value())
//                .body(CmmErrorResponse.error(errorCode));
//    }
//
//    // INTERNAL_SERVER_ERROR
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CmmErrorResponse<Object>> handleException(Exception ex) {
//        return ResponseEntity
//                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus().value())
//                .body(CmmErrorResponse.error(ErrorCode.INTERNAL_SERVER_ERROR));
//    }

    private CmmResult getResultDto(String ymlKey) {
        Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo(ymlKey);
        if (exceptionInfo == null) {
            return exceptionInfoConfig.getUndefinedErrorResult();
        }
        CmmResult resultDesc = new CmmResult();

        resultDesc.setCode(exceptionInfo.get("code").toString());
        resultDesc.setMessage(exceptionInfo.get("desc").toString());
        resultDesc.setStatus(exceptionInfo.get("status").toString());

        return resultDesc;
    }

    private CmmResult getResultDto(String ymlKey, Object[] args) {
        Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo(ymlKey);
        if (exceptionInfo == null) {
            return exceptionInfoConfig.getUndefinedErrorResult();
        }

        CmmResult resultDesc = new CmmResult();

        resultDesc.setCode(exceptionInfo.get("code").toString());
        String desc = exceptionInfo.get("desc").toString();
        resultDesc.setMessage(MessageFormat.format(desc, args));
        resultDesc.setStatus(exceptionInfo.get("status").toString());

        return resultDesc;
    }
}
