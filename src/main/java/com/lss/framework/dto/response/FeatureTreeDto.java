package com.lss.framework.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FeatureTreeDto {
    private String key;
    private String label;
    private String data;
    private String icon;
    private String routerLink;
    private Boolean defaultValue;
    private List<FeatureTreeDto> permissions;
    private List<FeatureTreeDto> children;
}