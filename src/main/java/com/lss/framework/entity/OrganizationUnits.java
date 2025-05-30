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
public class OrganizationUnits {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer tenantId;
    @Column
    private String name;
    @Column
    private Boolean isActive;
    @Column
    private LocalDateTime creationTime;
    @Column
    private Integer creatorUserId;
    @Column
    private LocalDateTime lastModificationTime;
    @Column
    private Integer lastModifierUserId;
}