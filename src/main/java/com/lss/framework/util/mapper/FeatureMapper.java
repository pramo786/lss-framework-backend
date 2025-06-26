package com.lss.framework.util.mapper;

import com.lss.framework.dto.response.FeatureDto;
import com.lss.framework.entity.Permissions;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class FeatureMapper {

    public static List<FeatureDto> buildFeatureTree(List<Permissions> permissionList) {
        FeatureDto virtualRoot = FeatureDto.builder()
                .key("App")
                .children(new ArrayList<>())
                .permissions(new ArrayList<>())
                .build();

        Map<String, FeatureDto> flatMap = new HashMap<>();
        flatMap.put("App", virtualRoot);

        for (Permissions permission : permissionList) {
            if (permission.getFeature() == null || permission.getPermission() == null) continue;

            String[] segments = permission.getFeature().split("\\.");
            FeatureDto currentParent = virtualRoot;
            StringBuilder currentPath = new StringBuilder();

            // Build feature nodes for each segment
            for (int i = 0; i < segments.length; i++) {
                currentPath.append(i > 0 ? "." : "").append(segments[i]);
                String pathKey = currentPath.toString();

                FeatureDto node = flatMap.get(pathKey);
                if (node == null) {
                    node = createFeatureNode(segments[i], pathKey, i == segments.length - 1);
                    flatMap.put(pathKey, node);
                    currentParent.getChildren().add(node);
                }
                currentParent = node;
            }

            // Add permission under the last feature node
            currentParent.getPermissions().add(createPermissionNode(permission));
        }

        return virtualRoot.getChildren();
    }

    private static FeatureDto createFeatureNode(String segment, String pathKey, boolean isLeaf) {
        return FeatureDto.builder()
                .key(pathKey)
                .label(segment)
                .data(segment)
                .icon("")
                .routerLink(isLeaf ? resolveRouterLink(pathKey) : null)
                .defaultValue(true)
                .permissions(new ArrayList<>())
                .children(new ArrayList<>())
                .build();
    }

    private static FeatureDto createPermissionNode(Permissions permission) {
        String[] parts = permission.getPermission().split("\\.");
        String label = parts[parts.length - 1];

        return FeatureDto.builder()
                .key(permission.getPermission())
                .label(label)
                .data(label)
                .icon("")
                .routerLink(null)
                .defaultValue(true)
                .permissions(new ArrayList<>())
                .children(new ArrayList<>())
                .build();
    }

    private static String resolveRouterLink(String key) {
        String lowerKey = key.toLowerCase();
        if (lowerKey.contains("dashboard")) return "/dashboard";
        if (lowerKey.contains("edition")) return "main/edition";
        if (lowerKey.contains("tenant")) return "main/tenants";
        if (lowerKey.contains("roles")) return "/main/roles";
        if (lowerKey.contains("users")) return "/main/users";
        if (lowerKey.contains("organizationunit")) return "/main/organizationunit";
        return null;
    }
}