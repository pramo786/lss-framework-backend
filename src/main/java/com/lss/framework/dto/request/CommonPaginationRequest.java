package com.lss.framework.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonPaginationRequest {
    private int maxResultCount;
    private int skipCount;
    private String sorting;
    private String dropdownFilter;
}
