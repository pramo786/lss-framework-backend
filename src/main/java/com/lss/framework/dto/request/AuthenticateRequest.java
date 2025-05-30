package com.lss.framework.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequest {
    private String tenancy;
    private String userName;
    private String password;
    private String captchaResponse;
    private String captchaValue;
    private String validationKey;
    private String reqId;
}