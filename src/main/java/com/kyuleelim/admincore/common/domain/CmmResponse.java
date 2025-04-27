package com.kyuleelim.admincore.common.domain;

import com.kyuleelim.admincore.common.enums.ErrorCode;
import com.kyuleelim.admincore.common.exception.BizException;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CmmResponse<T> extends CmmResult {
    private T body;

    public CmmResponse() {}
    public CmmResponse(T body){
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        if(HttpMethod.GET.toString().equals(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getMethod()) && body != null){
            throw new BizException(ErrorCode.DATA_NOT_FOUND);
        }
        this.body = body;
    }
}
