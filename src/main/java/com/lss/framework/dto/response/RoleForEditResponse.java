package com.lss.framework.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleForEditResponse {
    private Long id;
    private String name;
    private String displayName;
    private List<FeatureDto> perminssions;
    private String makerUserName;
    private LocalDateTime makerDateTime;
    private String lastModifiedUserName;
    private LocalDateTime lastModifiedDateTime;
    private String checkerName;
    private LocalDateTime checkerDateTime;
}
