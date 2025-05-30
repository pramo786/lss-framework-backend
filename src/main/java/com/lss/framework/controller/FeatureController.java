package com.lss.framework.controller;

import com.lss.framework.dto.response.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/Api/Feature")
@RequiredArgsConstructor
class FeatureController {

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
}
