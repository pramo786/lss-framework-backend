package com.lss.framework.service;


import com.lss.framework.dto.request.AuthenticateRequest;
import com.lss.framework.dto.response.AuthenticateResponse;
import com.lss.framework.dto.response.ErrorDetail;
import com.lss.framework.dto.response.ErrorResponse;
import com.lss.framework.entity.User;
import com.lss.framework.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.*;

import io.jsonwebtoken.Jwts;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginAttemptsService loginAttemptsService;
    private final Key key;

    public ResponseEntity<?> handlePreValidation(AuthenticateRequest request) {

        if (request.getCaptchaValue() == null ||
                !request.getCaptchaValue().equals(request.getCaptchaResponse())) {
            return buildError("Invalid Captcha.");
        }

        List<User> userList = userRepository.findByUserName(request.getUserName());
        if (userList.isEmpty()) {
            return buildError("Invalid Username or Password.");
        }

        User user = userList.get(0);

        // if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        if (!request.getPassword().equals(user.getPassword())) {
            user.setAccessFailedCount(user.getAccessFailedCount() + 1);
            userRepository.save(user);
            return buildError("Credentials Are Incorrect Please Login Using Correct Credentials. You have " +
                    (3 - user.getAccessFailedCount()) +
                    " more attempt(s) before your account gets locked out.");
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            return buildError("Your account is inactive. Please contact the administrator.");
        }

        user.setAccessFailedCount(0);
        user = userRepository.save(user);
        loginAttemptsService.setLastLogin(user.getId());
        AuthenticateResponse response = new AuthenticateResponse();
        response.setId(user.getId().intValue());
        response.setUsername(user.getUserName());
        response.setToken("dummy-jwt-token");
        response.setShouldResetPassword(user.getShouldChangePasswordOnNextLogin());
        response.setReqId(request.getReqId());
        response.setDayLeftMessage("");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        List<User> userList = userRepository.findByUserName(request.getUserName());
        if (userList.isEmpty()) {
            return buildError("Invalid credentials");
        }

        User user = userList.get(0);
        if (!request.getPassword().equals(user.getPassword())) {
            return buildError("Invalid credentials");
        }

        String token = generateToken(user);
        AuthenticateResponse response = new AuthenticateResponse();
        response.setId(user.getId().intValue());
        response.setUsername(user.getUserName());
        response.setToken(token);
        response.setShouldResetPassword(user.getShouldChangePasswordOnNextLogin());
        response.setReqId(request.getReqId());
        return ResponseEntity.ok(response);
    }

    private String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUserName());
        claims.put("tenantId", user.getTenantId());
        claims.put("isAdmin", true);
        claims.put("permissions", List.of("VIEW", "EDIT"));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("LSS")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(key)
                .compact();
    }


    private ResponseEntity<ErrorResponse> buildError(String message) {
        ErrorResponse error = new ErrorResponse(
                null,
                false,
                new ErrorDetail(0, message, null),
                400,
                ZonedDateTime.now().toString(),
                UUID.randomUUID().toString()
        );
        return ResponseEntity.badRequest().body(error);
    }

    public Long extractUserId(String tokenHeader) {
        try {
            String token = tokenHeader.replace("Bearer ", "").trim();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Long.valueOf(claims.get("userId").toString());
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}


