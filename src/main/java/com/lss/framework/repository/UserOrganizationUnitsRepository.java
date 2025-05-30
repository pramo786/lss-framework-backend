package com.lss.framework.repository;

import com.lss.framework.entity.UserOrganizationUnits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrganizationUnitsRepository extends JpaRepository<UserOrganizationUnits, Long> {
    Optional<UserOrganizationUnits> findByUserIdAndIsDeletedFalse(Long userId);
}
