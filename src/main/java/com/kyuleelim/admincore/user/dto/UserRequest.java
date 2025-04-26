package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.domain.CmmPageRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest extends CmmPageRequest {
    @NotBlank(message="사용자 이름은 필수입니다.")
    private String username;

    @NotBlank(message="비밀번호는 필수입니다.")
    private String password;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message="이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "사용 여부는 필수입니다.")
    private String useYn;
}
