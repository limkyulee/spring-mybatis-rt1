package com.kyuleelim.admincore.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberLoginReqDto")
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginReqDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
