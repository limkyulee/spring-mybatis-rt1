package com.kyuleelim.admincore.member.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Member")
public class Member {
    private Long memberId;
    private String userNm;
    private String email;
    private String password;
    private Role roleNm;
}
