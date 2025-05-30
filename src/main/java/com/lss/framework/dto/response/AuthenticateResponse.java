package com.lss.framework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateResponse {
    private Integer id;
    private Integer tenantId;
    private String username;
    private String organizationUnit;
    private String token;
    private String dayLeftMessage;
    private Boolean shouldResetPassword;
    private String reqId;
}