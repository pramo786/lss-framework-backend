package com.lss.framework.service;

import com.lss.framework.dto.request.CommonPaginationRequest;
import com.lss.framework.dto.response.EditionDto;
import com.lss.framework.dto.response.EditionResponse;
import com.lss.framework.entity.Tenants;
import com.lss.framework.repository.EditionsRepository;
import com.lss.framework.repository.FeturesRepository;
import com.lss.framework.repository.TenantsRepository;
import com.lss.framework.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditionService {

    private final FeturesRepository featuresRepository;
    private final UsersRepository usersRepository;

    private final TenantsRepository tenantsRepository;
    private final EditionsRepository editionsRepository;
    private final AuthService authService;

    public EditionResponse getAllEditions(String token, CommonPaginationRequest request) {
        Long userId = authService.extractUserId(token);
        Long tenantId = authService.extractTenantId(token);

        List<EditionDto> editions;
        if (tenantId != null) {
            Optional<Tenants> tenant = tenantsRepository.findById(tenantId);
            if (tenant.isPresent() && tenant.get().getEditionId() != null) {
                editions = editionsRepository.findByIdIn(List.of(tenant.get().getEditionId())).stream()
                        .map(e -> EditionDto.builder()
                                .id(e.getId())
                                .name(e.getDisplayName())
                                .creationDateTime(e.getCreatorDateTime())
                                .lastModificationTime(e.getLastModificationTime())
                                .build())
                        .toList();
            } else {
                editions = List.of();
            }
        } else {
            editions = editionsRepository.findByCreatorUserId(userId).stream()
                    .map(e -> EditionDto.builder()
                            .id(e.getId())
                            .name(e.getDisplayName())
                            .creationDateTime(e.getCreatorDateTime())
                            .lastModificationTime(e.getLastModificationTime())
                            .build())
                    .toList();
        }

        return EditionResponse.builder()
                .totalCount(editions.size())
                .items(editions)
                .build();
    }
}