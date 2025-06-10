package com.kyuleelim.admincore.user.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {
    private Long userId;
    private String userNm;
    private String password;
    private String email;
    private String useYn;
}
