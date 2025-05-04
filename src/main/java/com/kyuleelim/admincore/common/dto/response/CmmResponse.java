package com.kyuleelim.admincore.common.dto.response;

import com.kyuleelim.admincore.common.exception.BizException;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
public class CmmResponse<T> extends CmmResult {
    private T body;

    public CmmResponse() {}
    public CmmResponse(T body){
        this.body = body;
    }

    public void setBody(T body) {
        if(HttpMethod.GET.toString().equals(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getMethod()) && body != null){
            throw new BizException("data_not_found");
        }

        this.body = body;
    }
}
