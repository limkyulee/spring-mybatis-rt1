package com.kyuleelim.admincore.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberLogin")
public class MemberLogin {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
