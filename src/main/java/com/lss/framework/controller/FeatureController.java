package com.lss.framework.controller;

import com.lss.framework.dto.request.ModuleRequest;
import com.lss.framework.dto.response.FeatureDto;
import com.lss.framework.dto.response.MenuItem;
import com.lss.framework.dto.response.ModuleFeatureResponse;
import com.lss.framework.service.AuthService;
import com.lss.framework.service.FeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/Api/Feature")
@RequiredArgsConstructor
@Slf4j
public class FeatureController {


    private final AuthService authService;
    private final FeatureService featureService;


    @PostMapping("/GetUserMenu")
    public ResponseEntity<Map<String, List<MenuItem>>> getUserMenu() {
        List<MenuItem> menuItems = List.of(
                MenuItem.builder()
                        .label("Dashboard")
                        .icon("")
                        .routerLink(List.of("/dashboard"))
                        .permission(List.of("Permissions.Dashboard"))
                        .items(List.of())
                        .build(),
                MenuItem.builder()
                        .label("Edition")
                        .icon("")
                        .routerLink(List.of("main/edition"))
                        .permission(List.of("Permissions.Edition.New", "Permissions.Edition.Edit"))
                        .items(List.of())
                        .build(),
                MenuItem.builder()
                        .label("Tenant")
                        .icon("")
                        .routerLink(List.of("main/tenants"))
                        .permission(List.of("Permissions.Tenants.New", "Permissions.Tenants.Edit"))
                        .items(List.of())
                        .build(),
                MenuItem.builder()
                        .label("Administration")
                        .icon("")
                        .routerLink(List.of())
                        .permission(List.of())
                        .items(List.of(
                                MenuItem.builder()
                                        .label("Roles")
                                        .icon("")
                                        .routerLink(List.of())
                                        .permission(List.of())
                                        .items(List.of(
                                                MenuItem.builder()
                                                        .label("Create")
                                                        .icon("")
                                                        .routerLink(List.of("/main/roles"))
                                                        .permission(List.of("Permissions.Roles.New", "Permissions.Roles.Edit"))
                                                        .items(List.of())
                                                        .build()
                                        ))
                                        .build(),
                                MenuItem.builder()
                                        .label("Users")
                                        .icon("")
                                        .routerLink(List.of())
                                        .permission(List.of())
                                        .items(List.of(
                                                MenuItem.builder()
                                                        .label("Create")
                                                        .icon("")
                                                        .routerLink(List.of("/main/users"))
                                                        .permission(List.of("Permissions.Users.New", "Permissions.Users.Edit"))
                                                        .items(List.of())
                                                        .build()
                                        ))
                                        .build()
                        ))
                        .build()
        );

        Map<String, List<MenuItem>> response = new HashMap<>();
        response.put("menuItems", menuItems);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/GetRolesFeatures")
    public ResponseEntity<ModuleFeatureResponse> getRolesFeatures(@RequestBody @Valid ModuleRequest request,
                                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(new ModuleFeatureResponse(featureService.getFeaturesByModule(request.getModule(), token)));
    }
}
