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
public class UserAuditDtl {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer tenantId;
    @Column
    private Integer userId;
    @Column
    private LocalDateTime auditDate;
    @Column
    private String type;
    @Column
    private String subType;
    @Column
    private String reason;
    @Column
    private Integer updatedBy;
}