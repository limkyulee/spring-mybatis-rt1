package com.kyuleelim.admincore.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberLogin")
@NoArgsConstructor
@AllArgsConstructor
public class MemberLogin {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
