package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.domain.CreateGroup;
import com.kyuleelim.admincore.common.domain.DeleteGroup;
import com.kyuleelim.admincore.common.domain.DetailGroup;
import com.kyuleelim.admincore.common.domain.UpdateGroup;
import com.kyuleelim.admincore.common.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserRequest")
public class UserRequest {
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class, DeleteGroup.class, DetailGroup.class})
    private String uuid;
    @NotBlank(groups = {UpdateGroup.class})
    private String userId;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String userNm;
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private UserStatus userStatus;
    @NotBlank(groups = {CreateGroup.class})
    private String password;
    @Email(groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    private String email;
}
