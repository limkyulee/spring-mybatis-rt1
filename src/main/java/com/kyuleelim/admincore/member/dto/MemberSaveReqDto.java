package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    @NotBlank
    @Max(20)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Max(20)
    private String password;
    private Role role = Role.USER;
}
