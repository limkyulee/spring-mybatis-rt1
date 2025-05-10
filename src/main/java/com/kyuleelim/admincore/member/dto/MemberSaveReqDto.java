package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberSaveReqDto")
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    @NotBlank
    @Size(max = 20)
    private String userNm;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 20)
    private String password;
    private Role roleNm = Role.USER;
}
