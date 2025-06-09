package com.kyuleelim.admincore.member.domain;

import lombok.Data;

/**
 * @author limkyulee
 * @version 1.0, 5/4/25
 * @see Login Info Domain
 */
@Data
public class LoginInfo {
    private String token;
    private String id;
}
