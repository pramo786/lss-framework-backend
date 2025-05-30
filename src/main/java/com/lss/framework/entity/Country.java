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
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer tenantId;
    @Column
    private Integer organizationUnitId;
    @Column
    private String country;
    @Column
    private Boolean checked;
    @Column
    private Integer checkerUserId;
    @Column
    private LocalDateTime checkerDateTime;
    @Column
    private LocalDateTime creationTime;
    @Column
    private Integer creatorUserId;
    @Column
    private LocalDateTime lastModificationTime;
    @Column
    private Integer lastModifierUserId;
}