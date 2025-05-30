package com.lss.framework.service;


import com.lss.framework.dto.response.OrganizationUnitResponse;
import com.lss.framework.entity.OrganizationUnits;
import com.lss.framework.entity.UserOrganizationUnits;
import com.lss.framework.repository.OrganizationUnitsRepository;
import com.lss.framework.repository.UserOrganizationUnitsRepository;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Builder
@Service
@RequiredArgsConstructor
public class OrganizationUnitService {

    private final UserOrganizationUnitsRepository userOrgUnitRepo;
    private final OrganizationUnitsRepository orgUnitRepo;

    public ResponseEntity<OrganizationUnitResponse> getUserOrganizationUnit(Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(new OrganizationUnitResponse(null, null));
        }

        Optional<UserOrganizationUnits> mapping = userOrgUnitRepo.findByUserIdAndIsDeletedFalse(userId);
        if (mapping.isEmpty()) {
            return ResponseEntity.ok(new OrganizationUnitResponse(null, null));
        }

        Optional<OrganizationUnits> unit = orgUnitRepo.findById(mapping.get().getOrganizationUnitId());
        if (unit.isEmpty()) {
            return ResponseEntity.ok(new OrganizationUnitResponse(null, null));
        }

        return ResponseEntity.ok(new OrganizationUnitResponse(unit.get().getId(), unit.get().getName()));
    }
}
