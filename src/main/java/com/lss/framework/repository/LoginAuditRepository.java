package com.lss.framework.repository;

import com.lss.framework.entity.LoginAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
     Optional<LoginAudit> findFirstByUserIdOrderByLoginDateDescLoginTimeDesc(Long userId);
}
