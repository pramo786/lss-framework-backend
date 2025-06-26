package com.lss.framework.dto.request;

import com.lss.framework.dto.response.FeatureDto;
import lombok.Data;

import java.util.List;

@Data
public class RoleCreateRequest {
    private String name;
    private Long id;
    private String displayName;
    private List<FeatureDto> perminssions;
}
