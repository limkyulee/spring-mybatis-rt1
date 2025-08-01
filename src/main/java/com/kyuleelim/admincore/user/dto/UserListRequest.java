package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.dto.request.CmmPageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("UserListRequest")
@Data
public class UserListRequest extends CmmPageRequest {
    private String keyword;
    private String searchType;
    private String sidx;
    private String sord;
    private String pageYn = "Y";

    /* 와일드 카드 보안을 위한 escape 처리 */
    public String getEscapedKeyword() {
        if (keyword == null) {
            return null;
        }

        return keyword.replace("\\", "\\\\")
            .replace("%", "\\%")
            .replace("_", "\\_");
    }
}
