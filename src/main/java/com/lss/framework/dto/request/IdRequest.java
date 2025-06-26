package com.lss.framework.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdRequest {
    @NotNull(message = "Id is mandatory")
    private Long id;
}
