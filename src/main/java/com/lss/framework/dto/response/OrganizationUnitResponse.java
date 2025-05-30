package com.lss.framework.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUnitResponse {
    private Long id;
    private String name;
}
