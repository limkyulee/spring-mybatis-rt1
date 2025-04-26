package com.kyuleelim.admincore.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kyuleelim.admincore.common.enums.CmmConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class CmmPageRequest {

    private Integer currentPage = CmmConstant.DEFAULT_CURRENT_PAGE;
    private Integer limit = CmmConstant.DEFAULT_LIMIT;

    @JsonInclude
    public Integer getRowStart() {
        return (Optional.ofNullable(currentPage).orElse(CmmConstant.DEFAULT_CURRENT_PAGE) - 1) * Optional.ofNullable(limit).orElse(CmmConstant.DEFAULT_LIMIT);
    }
}
