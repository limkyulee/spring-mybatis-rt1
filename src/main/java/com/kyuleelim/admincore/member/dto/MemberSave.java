package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberSave")
public class MemberSave {
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 20)
    private String password;
    private Role role = Role.USER;
}
