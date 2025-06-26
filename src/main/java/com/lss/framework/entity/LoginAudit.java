package com.lss.framework.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loginaudit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Tenant_Id")
    private Integer tenantId;

    @Column(name = "User_Id", nullable = false)
    private Long userId;

    @Column(name = "Login_Date")
    private LocalDateTime loginDate;

    @Column(name = "Login_Time")
    private String loginTime;

    @Column(name = "Login_Ip", length = 45)
    private String loginIp;

    @Column(name = "LogOut_Date")
    private LocalDateTime logoutDate;

    @Column(name = "LogOut_Time", nullable = false, length = 6)
    private String logoutTime;
}
