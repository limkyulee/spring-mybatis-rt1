package com.kyuleelim.admincore.user.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String useYn;
}
