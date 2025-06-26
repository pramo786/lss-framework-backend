package com.lss.framework.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SimpleRoleDto {
    private Long id;
    private String name;
    private LocalDateTime creationTime;
    private LocalDateTime lastModificationTime;
}