package com.lss.framework.repository;

import com.lss.framework.entity.RecentPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentPasswordsRepository extends JpaRepository<RecentPasswords, Long> {
}
