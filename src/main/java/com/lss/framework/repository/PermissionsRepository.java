package com.lss.framework.repository;

import com.lss.framework.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    List<Permissions> findByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
}
