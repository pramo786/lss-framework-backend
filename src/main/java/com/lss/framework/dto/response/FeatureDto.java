package com.lss.framework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDto {
    private String key;
    private String label;
    private String data;
    private String icon;
    private String routerLink;
    private Boolean defaultValue;
    private List<FeatureDto> permissions;
    private List<FeatureDto> children;
}