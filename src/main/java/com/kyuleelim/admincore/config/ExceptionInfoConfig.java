package com.kyuleelim.admincore.config;

import com.kyuleelim.admincore.common.dto.response.CmmResult;
import lombok.Getter;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
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
    // exception.yml 내의 전체 데이터를 저장한 Map
    private Map<String, Object> exceptionYamlMap = null;
    // 200 OK 용 응답 DTO
    private CmmResult successResult = null;
    // 정의되지 않은 예외용 기본 응답 DTO
    private CmmResult undefinedErrorResult = null;

    /**
     * 생성자에서 YAML 파일을 읽어 필요한 초기화 작업을 수행.
     */
    @SuppressWarnings("unchecked")
    public ExceptionInfoConfig() {
        String filePath = "exception/exception.yml";

        // YAML 파서 생성 및 파일 지정.
        YamlMapFactoryBean yaml = new YamlMapFactoryBean();
        yaml.setResources(new ClassPathResource(filePath));
        // 전체 YAML 데이터 Map 형태로 저장.
        exceptionYamlMap = yaml.getObject();

        // exceptionYamlMap 이 Null 이 아니어야함을 보장.
        assert exceptionYamlMap != null;

        // 성공 응답 정보 셋팅.
        Map<String, Object> successSectionMap = (Map<String, Object>) exceptionYamlMap.get("success");
        Map<String, Object> success200OKMap = (Map<String, Object>) successSectionMap.get("200ok");

        successResult = new CmmResult();
        successResult.setCode(toStringOrEmpty(success200OKMap.get("code"), "success.200ok.code"));
        successResult.setMessage(toStringOrEmpty(success200OKMap.get("message"), "success.200ok.message"));
        successResult.setStatus(toStringOrEmpty(success200OKMap.get("status"), "success.200ok.status"));

        // 정의되지 않은 예외 응답 정보 셋팅.
        Map<String, Object> exceptionSectionMap = (Map<String, Object>) exceptionYamlMap.get("exception");
        Map<String, Object> undefinedExceptionMap = (Map<String, Object>) exceptionSectionMap.get("undefined");

        undefinedErrorResult = new CmmResult();
        undefinedErrorResult.setCode(toStringOrEmpty(undefinedExceptionMap.get("code"), "exception.undefined.code"));
        undefinedErrorResult.setMessage(toStringOrEmpty(undefinedExceptionMap.get("message"), "exception.undefined.message"));
        undefinedErrorResult.setStatus(toStringOrEmpty(undefinedExceptionMap.get("status"), "exception.undefined.status"));
    }

    // ymlKey 로 특정 성공 정보 조회.
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSuccessYamlInfo(String ymlKey) {
        Map<String, Object> successSectionMap = (Map<String, Object>) exceptionYamlMap.get("success");
        return (Map<String, Object>) successSectionMap.get(ymlKey);
    }

    // ymlKey 로 특정 예외 정보 조회.
    @SuppressWarnings("unchecked")
    public Map<String, Object> getExceptionYamlInfo(String ymlKey) {
        Map<String, Object> exceptionSectionMap = (Map<String, Object>) exceptionYamlMap.get("exception");
        return (Map<String, Object>) exceptionSectionMap.get(ymlKey);
    }

    // ymlKey 에 따른 성공 응답을 CmmResult 로 반환
    public CmmResult getSuccessInfoResult(String ymlKey) {
        Map<String, Object> successYamlInfo = getSuccessYamlInfo(ymlKey);

        CmmResult result = new CmmResult();
        result.setCode(toStringOrEmpty(successYamlInfo.get("code"), "success." + ymlKey + ".code"));
        result.setMessage(toStringOrEmpty(successYamlInfo.get("message"), "success." + ymlKey + ".message"));
        result.setStatus(toStringOrEmpty(successYamlInfo.get("status"), "success." + ymlKey + ".status"));

        return result;
    }

    // ymlKey 에 따른 예외 응답을 CmmResult 반환
    public CmmResult getExceptionInfoResult(String ymlKey) {
        Map<String, Object> exceptionYamlInfo = getExceptionYamlInfo(ymlKey);

        CmmResult result = new CmmResult();
        result.setCode(toStringOrEmpty(exceptionYamlInfo.get("code"), "exception." + ymlKey + ".code"));
        result.setMessage(toStringOrEmpty(exceptionYamlInfo.get("message"), "exception." + ymlKey + ".message"));
        result.setStatus(toStringOrEmpty(exceptionYamlInfo.get("status"), "exception." + ymlKey + ".status"));

        return result;
    }

    private String toStringOrEmpty(Object value, String keyPath) {
        if (value == null) {
            System.err.println("[WARN] YAML 항목 누락 또는 null: " + keyPath + " → \"\"(빈 문자열) 처리됨");
            return "";
        }
        return value.toString();
    }
}
