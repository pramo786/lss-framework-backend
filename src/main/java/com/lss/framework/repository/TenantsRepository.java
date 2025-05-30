package com.lss.framework.repository;

import com.lss.framework.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantsRepository extends JpaRepository<Tenants, Long> {
}
