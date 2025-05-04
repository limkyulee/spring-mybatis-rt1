package com.kyuleelim.admincore.common.exception;

import com.kyuleelim.admincore.common.dto.response.CmmResult;
import java.io.Serial;
import java.util.List;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private String ymlKey;
    private List<String> messageArgs;
    private CmmResult customResult;
    private Throwable throwable;

    /** YAML 메시지 기반 생성자 */
    public BizException(String ymlKey) {
        this.ymlKey = ymlKey;
    }

    /** YAML 메시지 + 치환값 */
    public BizException(String ymlKey, List<String> messageArgs) {
        this.ymlKey = ymlKey;
        this.messageArgs = messageArgs;
    }

    /** YAML 메시지 + cause */
    public BizException(String ymlKey, Throwable throwable) {
        this.throwable = throwable;
        this.ymlKey = ymlKey;
    }

    /** YAML 메시지 + 치환값 + cause */
    public BizException(String ymlKey, List<String> messageArgs, Throwable throwable) {
        this.throwable = throwable;
        this.ymlKey = ymlKey;
        this.messageArgs = messageArgs;
    }

    /** 직접 생성한 결과 DTO */
    public BizException(CmmResult customResult) {
        this.customResult = customResult;
    }

    /** 직접 코드/메시지 지정 */
    public BizException(String code, String message, String status) {
        this.customResult = new CmmResult();
        customResult.setCode(code);
        customResult.setMessage(message);
        customResult.setStatus(status);
    }

    /** 직접 지정 + cause 포함 */
    public BizException(CmmResult customResult, Throwable throwable) {
        this.throwable = throwable;
        this.customResult = customResult;
    }
}
