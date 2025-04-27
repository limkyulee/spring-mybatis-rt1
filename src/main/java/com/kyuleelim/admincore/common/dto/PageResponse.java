package com.kyuleelim.admincore.common.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> data;
    private int totalCount;
    private int currentPage;
    private int limit;
}
