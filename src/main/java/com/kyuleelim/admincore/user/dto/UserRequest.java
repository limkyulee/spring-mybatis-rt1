package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.domain.CreateGroup;
import com.kyuleelim.admincore.common.domain.DetailGroup;
import com.kyuleelim.admincore.common.domain.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserRequest")
public class UserRequest {
    @NotNull(groups = {UpdateGroup.class, DetailGroup.class})
    private Long userId;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String userNm;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String password;
    @Email(groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String useYn;
}
