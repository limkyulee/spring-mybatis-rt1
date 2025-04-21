package com.kyuleelim.admincore.user.dto;

import lombok.Data;

@Data
public class SearchCondition {
    private String keyword;
    private String searchType;

    private Integer currentPage = 1;
    private Integer limit = 10;

    public int getOffset(){
        return (currentPage - 1) * limit;
    }
}
