package com.lss.framework.dto.response;

import lombok.*;
import org.springframework.core.io.Resource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaDetails {
    private String captcha;
    private String value;

}
