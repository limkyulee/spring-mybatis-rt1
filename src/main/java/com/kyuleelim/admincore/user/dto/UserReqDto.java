package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.domain.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserReqDto")
public class UserReqDto {
    @NotBlank(groups = UpdateGroup.class)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String useYn;
}
