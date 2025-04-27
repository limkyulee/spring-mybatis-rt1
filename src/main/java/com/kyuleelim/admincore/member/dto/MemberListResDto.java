package com.kyuleelim.admincore.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResDto {
    private Long id;
    private String username;
    private String email;
}
