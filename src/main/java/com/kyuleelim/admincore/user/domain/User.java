package com.kyuleelim.admincore.user.domain;

import com.kyuleelim.admincore.common.enums.UserStatus;
import java.util.UUID;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {
    private String uuid;
    private String userId;
    private String userNm;
    private UserStatus userStatus;
    private String password;
    private String email;
    private String createdAd;
}
