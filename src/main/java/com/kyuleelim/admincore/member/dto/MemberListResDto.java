package com.kyuleelim.admincore.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("MemberListResDto")
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResDto {
    private Long id;
    private String username;
    private String email;
}
