package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.dto.request.CmmPageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserListReqDto")
public class UserListReqDto extends CmmPageRequest {
    private String keyword;
    private String searchType;
    private String sidx;
    private String sord;
}
