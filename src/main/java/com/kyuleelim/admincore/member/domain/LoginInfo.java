package com.kyuleelim.admincore.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author limkyulee
 * @version 1.0, 5/4/25
 * @see Login Info Domain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private String token;
    private String id;
}
