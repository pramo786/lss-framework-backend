package com.lss.framework.controller;

import com.lss.framework.repository.LoginAuditRepository;
import com.lss.framework.service.AuthService;
import com.lss.framework.service.LoginAttemptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/LoginAttempts")
@RequiredArgsConstructor
class LoginAttemptsController {


    private final AuthService authService;
    private final LoginAttemptsService loginAttemptsService;

    @PostMapping("/GetUserLastLoginTime")
    public ResponseEntity<?> getLastLogin(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(loginAttemptsService.getLastLogin(authService.extractUserId(token)));
    }
}

