package com.lss.framework.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditionDto {
    private Long id;
    private String name;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModificationTime;
}
