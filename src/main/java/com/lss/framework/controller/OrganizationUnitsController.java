package com.lss.framework.controller;


import com.lss.framework.dto.request.GetUserOrganizationUnitRequest;
import com.lss.framework.dto.response.OrganizationUnitResponse;
import com.lss.framework.service.OrganizationUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/OrganizationUnits")
@RequiredArgsConstructor
class OrganizationUnitsController {
    private final OrganizationUnitService organizationUnitService;


    @PostMapping("/GetUserOrganizationUnit")
    public ResponseEntity<OrganizationUnitResponse> getUserOrganizationUnit(@RequestBody GetUserOrganizationUnitRequest request) {
        return organizationUnitService.getUserOrganizationUnit(request.getUserId());
    }
}