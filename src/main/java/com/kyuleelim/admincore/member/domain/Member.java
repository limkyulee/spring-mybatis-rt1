package com.kyuleelim.admincore.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long memberId;
    private String userNm;
    private String email;
    private String password;
    private Role roleNm;
}
