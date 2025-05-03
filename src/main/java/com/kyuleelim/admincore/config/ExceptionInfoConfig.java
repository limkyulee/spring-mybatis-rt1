package com.kyuleelim.admincore.config;

import com.kyuleelim.admincore.common.dto.response.CmmResult;
import lombok.Getter;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.text.MessageFormat;
import java.util.Map;

/**
 * ===========================================
 * Project      : admin-core-api
 * File Name    : ExceptionInfoConfig.java
 * Author       : limkyulee
 * Created Date : 2025. 5. 3. 오후 7:31
 * Updated Date : 2025. 5. 3. 오후 7:31
 * Description  : exception.yml 파일을 기반으로 성공/예외 응답 메시지 관리
 * ===========================================
 */
@Getter
public class ExceptionInfoConfig {
    private Map<String, Object> exceptionInfo = null;
    private CmmResult successResultDto = null;
    private CmmResult undefinedErrorResult = null;

    @SuppressWarnings("unchecked")
    public ExceptionInfoConfig() {
        String filePath = "exception/exception.yml";

        YamlMapFactoryBean yaml = new YamlMapFactoryBean();
        yaml.setResources(new ClassPathResource(filePath));
        this.exceptionInfo = yaml.getObject();
        Map<String, Object> successInfos = (Map<String, Object>) exceptionInfo.get("success");
        Map<String, Object> successInfo = (Map<String, Object>) successInfos.get("200ok");

        successResultDto = new CmmResult();

        successResultDto.setCode(successInfo.get("code").toString());
        successResultDto.setMessage(successInfo.get("message").toString());
        successResultDto.setStatus(successInfo.get("status").toString());


        Map<String, Object> exceptionInfos = (Map<String, Object>) exceptionInfo.get("exception");
        Map<String, Object> exceptionInfo = (Map<String, Object>) exceptionInfos.get("notdefine");

        undefinedErrorResult = new CmmResult();

        undefinedErrorResult.setCode(exceptionInfo.get("code").toString());
        undefinedErrorResult.setMessage(exceptionInfo.get("message").toString());
        undefinedErrorResult.setStatus(exceptionInfo.get("status").toString());
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSuccessInfo(String ymlKey) {
        Map<String, Object> allSuccessInfo = (Map<String, Object>) exceptionInfo.get("success");
        return (Map<String, Object>) allSuccessInfo.get(ymlKey);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getExceptionInfo(String ymlKey) {
        Map<String, Object> allExceptions = (Map<String, Object>) exceptionInfo.get("exception");
        return (Map<String, Object>) allExceptions.get(ymlKey);
    }

    public CmmResult getSuccessInfoResult(String ymlKey) {
        Map<String, Object> exceptionInfo = getSuccessInfo(ymlKey);

        CmmResult result = new CmmResult();
        result.setCode(String.valueOf(exceptionInfo.get("code")));
        result.setMessage((String) exceptionInfo.get("desc"));
        result.setStatus(String.valueOf(exceptionInfo.get("status")));

        return result;

    }

    @SuppressWarnings("unchecked")
    public CmmResult getExceptionInfoResult(String ymlKey) {
        Map<String, Object> exceptionInfos = (Map<String, Object>) exceptionInfo.get("exception");
        Map<String, Object> exceptionInfo = (Map<String, Object>) exceptionInfos.get(ymlKey);

        CmmResult result = new CmmResult();
        result.setCode(String.valueOf(exceptionInfo.get("code")));
        result.setMessage(String.valueOf(exceptionInfo.get("desc")));
        result.setStatus(String.valueOf(exceptionInfo.get("status")));

        return result;
    }

    public CmmResult getResultDto(String ymlKey) {
        Map<String, Object> exceptionInfo = getExceptionInfo(ymlKey);
        if (exceptionInfo == null) {
            return getUndefinedErrorResult();
        }
        CmmResult resultDesc = new CmmResult();

        resultDesc.setCode(String.valueOf(exceptionInfo.get("code")));
        resultDesc.setMessage(String.valueOf(exceptionInfo.get("desc")));
        resultDesc.setStatus(String.valueOf(exceptionInfo.get("status")));

        return resultDesc;
    }

    public CmmResult getResultDto(String ymlKey, Object[] args) {
        CmmResult resultDesc = getResultDto(ymlKey);

        String message = MessageFormat.format(resultDesc.getMessage(), args);
        resultDesc.setMessage(message);
        return resultDesc;
    }
}
