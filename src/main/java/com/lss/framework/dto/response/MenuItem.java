package com.lss.framework.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private String label;
    private String icon;
    private List<String> routerLink;
    private List<String> permission;
    private List<MenuItem> items;
}
