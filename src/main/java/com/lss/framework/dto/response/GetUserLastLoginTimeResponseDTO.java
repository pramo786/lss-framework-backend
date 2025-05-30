package com.lss.framework.dto.response;

import com.lss.framework.contstants.LSSApplicationConstants;
import com.lss.framework.entity.LoginAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserLastLoginTimeResponseDTO {
    String lastLoginTime;

    public static GetUserLastLoginTimeResponseDTO convertLoginAudit(LoginAudit loginAudit) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LSSApplicationConstants.DATE_TIME_FORMATTER_DDMMYYYY_HHMM_AMPM);
        return GetUserLastLoginTimeResponseDTO
                .builder()
                .lastLoginTime(formatter.format(loginAudit.getLoginDate()))
                .build();
    }
}
