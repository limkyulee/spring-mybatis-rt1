package com.kyuleelim.admincore.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    private Long id;

    private String username;
    private String email;
    private String password;

    @Builder.Default
    private Role role = Role.USER;
}
