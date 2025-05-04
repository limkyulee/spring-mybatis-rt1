package com.kyuleelim.admincore.user.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String useYn;
}
