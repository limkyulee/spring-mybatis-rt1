package com.kyuleelim.admincore.common.exception;

import com.kyuleelim.admincore.common.dto.response.CmmResponse;
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

import java.util.Map;

/**
 * ===========================================
 * Project      : admin-core-api
 * File Name    : GlobalExceptionHandler.java
 * Author       : limkyulee
 * Created Date : 2025. 5. 3. 오후 7:23
 * Updated Date : 2025. 5. 3. 오후 7:23
 * Description  : Global Exception Handler
 * ===========================================
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionInfoConfig exceptionInfoConfig;

    /**
     * @Method Name handleMethodArgumentNotValidException
     * @Description @Validated Exception Handler
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody CmmResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws Exception {
        BindingResult bindingResult = ex.getBindingResult();
        // 최종 에러 response
        CmmResponse<Void> cmmResponse = new CmmResponse<>();

        // 에러 메세지 셋팅
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
             builder.append("[")
                    .append(fieldError.getField())
                    .append("](은)는 ")
                    .append(fieldError.getDefaultMessage())
                    .append(". 입력 값 : [")
                    .append(fieldError.getRejectedValue())
                    .append("]");
        }

        // yaml 에 맞는 예외 정의 정보 조회.
        CmmResult exceptionYamlInfo = getExceptionYamlInfo(ex.getClass().getName());
        int statusCode = Integer.parseInt(exceptionYamlInfo.getStatus());

        // 에러 response 셋팅.
        cmmResponse.setHttpStatusCode(statusCode);
        cmmResponse.setCode(exceptionYamlInfo.getCode());
        cmmResponse.setMessage(exceptionYamlInfo.getMessage());
        cmmResponse.setDetailMessage(builder.toString());

        return new CmmResponseEntity<>(cmmResponse, HttpStatus.valueOf(statusCode));
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

    private CmmResult getExceptionYamlInfo(String ymlKey) {
        Map<String, Object> exceptionYamlInfo = exceptionInfoConfig.getExceptionYamlInfo(ymlKey);

        if (exceptionYamlInfo == null) {
            return exceptionInfoConfig.getUndefinedErrorResult();
        }

        return exceptionInfoConfig.getExceptionInfoResult(ymlKey);
    }

//    private CmmResult getResultDto(String ymlKey, Object[] args) {
//        Map<String, Object> exceptionYamlInfo = exceptionInfoConfig.getExceptionYamlInfo(ymlKey);
//
//        if (exceptionYamlInfo == null) {
//            return exceptionInfoConfig.getUndefinedErrorResult();
//        }
//
//        CmmResult resultDesc = new CmmResult();
//
//        resultDesc.setCode(exceptionInfo.get("code").toString());
//        String desc = exceptionInfo.get("desc").toString();
//        resultDesc.setMessage(MessageFormat.format(desc, args));
//        resultDesc.setStatus(exceptionInfo.get("status").toString());
//
//        return resultDesc;
//    }
}
