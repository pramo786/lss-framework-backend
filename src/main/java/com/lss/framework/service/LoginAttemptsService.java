package com.lss.framework.service;

import com.lss.framework.dto.response.GetUserLastLoginTimeResponseDTO;
import com.lss.framework.entity.LoginAudit;
import com.lss.framework.repository.LoginAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginAttemptsService {
    private final LoginAuditRepository loginAuditRepository;

    public GetUserLastLoginTimeResponseDTO getLastLogin(Long userId) {
        return loginAuditRepository.findFirstByUserIdOrderByLoginDateDescLoginTimeDesc(userId)
                .map(GetUserLastLoginTimeResponseDTO::convertLoginAudit)
                .orElseThrow(() -> new NoSuchElementException("No login record found for userId: " + userId));
    }

    public void setLastLogin(Long userId) {
        LoginAudit loginAudit =LoginAudit.builder()
                .userId(userId)
                .loginTime(LocalTime.now().toString().substring(0,5))
                .loginDate(LocalDateTime.now())
                .build();
        loginAuditRepository.save(loginAudit);
    }
}
