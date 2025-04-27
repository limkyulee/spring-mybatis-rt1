package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.dto.request.CmmPageRequest;
import lombok.Data;

@Data
public class UserListReqDto extends CmmPageRequest {
    private String keyword;
    private String searchType;
}
