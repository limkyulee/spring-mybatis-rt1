package com.kyuleelim.admincore.common.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CmmResponseEntity<T> extends ResponseEntity<CmmResponse<T>> {
    public CmmResponseEntity(CmmResponse<T> body, HttpStatusCode status) {
        super(body, status);
        body.setHttpStatusCode(status.value());
    }

    public CmmResponseEntity(HttpStatus unauthorized) {
        super(unauthorized);
    }
}
