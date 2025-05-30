package com.lss.framework.service;

import com.lss.framework.dto.response.GetUserLastLoginTimeResponseDTO;
import com.lss.framework.entity.LoginAudit;
import com.lss.framework.repository.LoginAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginAttemptsService {
    private final LoginAuditRepository loginAuditRepository;

    public GetUserLastLoginTimeResponseDTO getLastLogin(Long userId) {
        return loginAuditRepository.findLastLoginByUserId(userId)
                .map(GetUserLastLoginTimeResponseDTO::convertLoginAudit)
                .orElseThrow(() -> new NoSuchElementException("No login record found for userId: " + userId));
    }

    public void setLastLogin(Long userId) {
        LoginAudit loginAudit =LoginAudit.builder()
                .userId(userId)
                .loginTime(LocalDateTime.now().toString())
                .loginDate(LocalDateTime.now())
                .build();
        loginAuditRepository.save(loginAudit);
    }
}
