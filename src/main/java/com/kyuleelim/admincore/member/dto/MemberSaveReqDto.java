package com.kyuleelim.admincore.member.dto;

import com.kyuleelim.admincore.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    private String username;
    private String email;
    private String password;
    private Role role = Role.USER;
}
