package com.lss.framework.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAudit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long tenantId;
    @Column
    private Long userId;
    @Column
    private LocalDateTime loginDate;
    @Column
    private String loginTime;
    @Column
    private String loginIp;
    @Column
    private LocalDateTime logoutDate;
    @Column
    private String logoutTime;
}