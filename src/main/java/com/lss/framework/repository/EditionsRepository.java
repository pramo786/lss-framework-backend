package com.lss.framework.repository;

import com.lss.framework.dto.response.EditionDto;
import com.lss.framework.entity.Editions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditionsRepository extends JpaRepository<Editions, Long> {
    List<Editions> findByIdIn(List<Long> editionId);
    List<Editions> findByCreatorUserId(Long userId);
}
