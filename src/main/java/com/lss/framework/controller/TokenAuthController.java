package com.lss.framework.controller;


import com.lss.framework.dto.request.AuthenticateRequest;
import com.lss.framework.dto.response.CaptchaDetails;
import com.lss.framework.service.AuthService;
import com.lss.framework.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TokenAuth")
@RequiredArgsConstructor
public class TokenAuthController {

    private final CaptchaService captchaService;
    private final AuthService authService;
    @GetMapping(value = "/GetCaptcha")
    public ResponseEntity<CaptchaDetails> getCaptcha() {
        CaptchaDetails captchaDetails = captchaService.generateCaptcha();
        return ResponseEntity.ok(captchaDetails);
    }

    @PostMapping(value = "/prevalidateauthenticate")
    public ResponseEntity<?> preValidateAuthenticate(@RequestBody AuthenticateRequest request) {
        return authService.handlePreValidation(request);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request) {
        return authService.authenticate(request);
    }
}
