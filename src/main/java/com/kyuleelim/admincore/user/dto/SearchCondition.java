package com.kyuleelim.admincore.user.dto;

import com.kyuleelim.admincore.common.dto.CmmPageRequest;
import lombok.Data;

@Data
public class SearchCondition extends CmmPageRequest {
    private String keyword;
    private String searchType;
}
