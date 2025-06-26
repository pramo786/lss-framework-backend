package com.lss.framework.repository;

import com.lss.framework.entity.Roles;
import com.lss.framework.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    UserRoles findByUserIdAndTenantId(Long userId, Long tenantId);
    UserRoles findByUserId(Long userId);
}
