package com.lss.framework.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Object result;
    private boolean success;
    private ErrorDetail error;
    private int statusCode;
    private String timestamp;
    private String correlationId;
}