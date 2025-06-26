package com.lss.framework.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedRoleResponse {
    private long totalCount;
    private List<SimpleRoleDto> items;
}