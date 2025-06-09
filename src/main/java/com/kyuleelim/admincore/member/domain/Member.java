package com.kyuleelim.admincore.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Member")
public class Member {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
