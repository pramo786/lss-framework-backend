package com.lss.framework.service;

import com.lss.framework.dto.response.FeatureDto;
import com.lss.framework.entity.Permissions;
import com.lss.framework.repository.FeturesRepository;
import com.lss.framework.repository.PermissionsRepository;
import com.lss.framework.repository.RolesRepository;
import com.lss.framework.util.mapper.FeatureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeatureService {

    private final RoleService roleService;
    private final PermissionsRepository permissionsRepository;


    public List<FeatureDto> getFeaturesByModule(String module, String token) {
        Long roleId = roleService.getRoleId(token);
        if (null == roleId) {
            return null;
        }
        List<Permissions> permissions = permissionsRepository.findByRoleId(roleId);
        return FeatureMapper.buildFeatureTree(permissions);
    }
}
