package com.lss.framework.service;

import com.lss.framework.dto.request.CommonPaginationRequest;
import com.lss.framework.dto.request.RoleCreateRequest;
import com.lss.framework.dto.response.FeatureDto;
import com.lss.framework.dto.response.PaginatedRoleResponse;
import com.lss.framework.dto.response.RoleForEditResponse;
import com.lss.framework.dto.response.SimpleRoleDto;
import com.lss.framework.entity.Permissions;
import com.lss.framework.entity.Roles;
import com.lss.framework.entity.User;
import com.lss.framework.entity.UserRoles;
import com.lss.framework.repository.PermissionsRepository;
import com.lss.framework.repository.RolesRepository;
import com.lss.framework.repository.UserRolesRepository;
import com.lss.framework.repository.UsersRepository;
import com.lss.framework.util.mapper.FeatureMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RolesRepository rolesRepository;
    private final AuthService authService;
    private final PermissionsRepository permissionsRepository;
    private final UsersRepository usersRepository;
    private final UserRolesRepository userRolesRepository;

    public PaginatedRoleResponse getAllEditions(CommonPaginationRequest request, String token) {
        Sort sort = Sort.by(Sort.Direction.ASC, request.getSorting() == null || request.getSorting().isEmpty() ? "id" : request.getSorting());
        Pageable pageable = PageRequest.of(request.getSkipCount(), request.getMaxResultCount(), sort);
        Long tenantId = authService.extractTenantId(token);
        Page<Roles> page = tenantId != null
                ? rolesRepository.findByTenantId(tenantId, pageable)
                : rolesRepository.findByTenantIdIsNull(pageable);

        List<SimpleRoleDto> items = page.getContent().stream().map(role -> SimpleRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .creationTime(role.getCreatorDateTime())
                .lastModificationTime(role.getLastModificationTime())
                .build()).collect(Collectors.toList());

        return PaginatedRoleResponse.builder()
                .totalCount(page.getTotalElements())
                .items(items)
                .build();
    }


    public RoleForEditResponse getRoleForEdit(Long id, String token) {
        Long userId = authService.extractUserId(token);
        Roles role = rolesRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        List<Permissions> permissions = permissionsRepository.findByRoleId(id);

        List<FeatureDto> features = FeatureMapper.buildFeatureTree(permissions);

        String makerName = "";
        if (role.getCreatorUserId() != null) {
            Optional<User> maker = usersRepository.findById(role.getCreatorUserId());
            makerName = maker.map(User::getUserName).orElse("");
        }

        log.info("Role {} fetched with {} permissions for userId: {}", id, permissions.size(), userId);

        return RoleForEditResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .displayName(role.getDisplayName())
                .makerUserName(makerName)
                .makerDateTime(role.getCreatorDateTime())
                .lastModifiedUserName("")
                .lastModifiedDateTime(role.getLastModificationTime())
                .checkerName("")
                .checkerDateTime(null)
                .perminssions(features)
                .build();
    }

    public Long getRoleId(String token) {
        Long tenantId = authService.extractTenantId(token);
        Long userId = authService.extractUserId(token);
        if (Objects.nonNull(tenantId)) {
            return userRolesRepository.findByUserIdAndTenantId(userId, tenantId).getRoleId();
        } else {
            return userRolesRepository.findByUserId(userId).getRoleId();
        }
    }

    @Transactional
    public void createOrModifyRole(RoleCreateRequest request,String token) {
        Long tenantId = authService.extractTenantId(token);
        Long userId = authService.extractUserId(token);
        Roles role;
        boolean isUpdate = request.getId() != null;
        if (isUpdate) {
            role = rolesRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Role not found for update"));
            role.setName(request.getName());
            role.setDisplayName(request.getName());
            role.setLastModifierUserId(userId);
            role.setLastModificationTime(LocalDateTime.now());
        } else {
            role = Roles.builder()
                    .tenantId(tenantId)
                    .name(request.getName())
                    .displayName(request.getName())
                    .creatorUserId(userId)
                    .creatorDateTime(LocalDateTime.now())
                    .build();
        }

        role = rolesRepository.save(role);

        // Clean up existing permissions if update
        if (isUpdate) {
            permissionsRepository.deleteByRoleId(role.getId());
        }

        Set<String> seenPermissions = new HashSet<>();
        List<Permissions> permissions = new ArrayList<>();
        for (FeatureDto feature : request.getPerminssions()) {
            extractPermissions(feature, role.getId(), tenantId, userId, permissions, seenPermissions);
        }
        permissionsRepository.saveAll(permissions);
        log.info("Saved {} unique permissions for roleId: {}", permissions.size(), role.getId());
    }

    private void extractPermissions(FeatureDto feature, Long roleId, Long tenantId, Long userId, List<Permissions> list, Set<String> seen) {
        if (feature.getKey().startsWith("Permissions.")) {
            if (seen.add(feature.getKey())) {
                list.add(Permissions.builder()
                        .tenantId(tenantId)
                        .roleId(roleId)
                        .userId(userId)
                        .permission(feature.getKey())
                        .feature(resolveFeaturePath(feature.getKey()))
                        .discriminator(getDiscriminator(userId, tenantId))
                        .creatorUserId(userId)
                        .creatorDateTime(LocalDateTime.now())
                        .build());
            }
        }

        if (feature.getPermissions() != null) {
            for (FeatureDto child : feature.getPermissions()) {
                extractPermissions(child, roleId, tenantId, userId, list, seen);
            }
        }

        if (feature.getChildren() != null) {
            for (FeatureDto child : feature.getChildren()) {
                extractPermissions(child, roleId, tenantId, userId, list, seen);
            }
        }
    }

    private String resolveFeaturePath(String permissionKey) {
        String permissionSuffix = permissionKey.replace("Permissions.", "");
        String[] parts = permissionSuffix.split("\\.");

        if (parts.length == 1) {
            return "App." + parts[0];
        }
        if (permissionSuffix.equals("OrganziationUnit.New") || permissionSuffix.equals("OrganziationUnit.Edit")) {
            return "App.Administration.OrganizationUnit";
        }

        return switch (parts[0]) {
            case "Edition" -> "App.Edition";
            case "Tenants" -> "App.Tenant";
            case "Roles" -> "App.Administration.Roles.Create";
            case "Users" -> "App.Administration.Users.Create";
            case "Country" -> "App.Master.Country";
            case "Dashboard" -> "App.Dashboard";
            default -> "";
        };
    }

    private String getDiscriminator(Long userId, Long tenantId) {
        if (userId != null) {
            return tenantId == null ? "HostPermissionSetting" : "TenantPermissionSetting";
        }
        return "RolePermissionSetting";
    }
}