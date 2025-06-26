package com.lss.framework.repository;

import com.lss.framework.entity.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Page<Roles> findByTenantId(Long tenantId, Pageable pageable);
    Page<Roles> findByTenantIdIsNull(Pageable pageable);
}
