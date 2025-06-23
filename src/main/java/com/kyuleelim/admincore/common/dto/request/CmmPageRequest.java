package com.kyuleelim.admincore.common.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kyuleelim.admincore.common.enums.CmmConstant;
import lombok.Data;

import java.util.Optional;

@Data
public class CmmPageRequest {

    private Integer currentPage = CmmConstant.DEFAULT_CURRENT_PAGE;
    private Integer limit = CmmConstant.DEFAULT_LIMIT;

    @JsonInclude
    public Integer getOffset() {
        return (Optional.ofNullable(currentPage).orElse(CmmConstant.DEFAULT_CURRENT_PAGE) - 1) * Optional.ofNullable(limit).orElse(CmmConstant.DEFAULT_LIMIT);
    }
}
