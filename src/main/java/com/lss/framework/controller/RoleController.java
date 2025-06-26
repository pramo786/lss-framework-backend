package com.lss.framework.controller;

import com.lss.framework.dto.request.CommonPaginationRequest;
import com.lss.framework.dto.request.IdRequest;
import com.lss.framework.dto.request.RoleCreateRequest;
import com.lss.framework.dto.response.PaginatedRoleResponse;
import com.lss.framework.dto.response.RoleForEditResponse;
import com.lss.framework.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/GetAllEditions")
    public ResponseEntity<PaginatedRoleResponse> getAllEditions(@RequestBody @Valid CommonPaginationRequest request,
                                                                @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(roleService.getAllEditions(request,token));
    }

    @PostMapping("/GetRoleForEdit")
    public ResponseEntity<RoleForEditResponse> getRoleForEdit(@RequestBody @Valid IdRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(roleService.getRoleForEdit(request.getId(),token));
    }

    @PostMapping("/CreateAndModifyRole")
    public ResponseEntity<String> createAndModifyRole(@RequestBody @Valid RoleCreateRequest request,
                                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        roleService.createOrModifyRole(request,token);
        return ResponseEntity.ok("Role created/updated successfully");
    }
}
