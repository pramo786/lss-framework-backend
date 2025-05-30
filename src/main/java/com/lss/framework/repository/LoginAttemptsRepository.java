package com.lss.framework.repository;

import com.lss.framework.entity.LoginAttempts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptsRepository extends JpaRepository<LoginAttempts, Long> {
}
