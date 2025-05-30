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
public class Tenants {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String tenancyName;
    @Column
    private String name;
    @Column
    private Integer editionId;
    @Column
    private Boolean isActive;
    @Column
    private LocalDateTime creationTime;
    @Column
    private Long creatorUserId;
    @Column
    private LocalDateTime lastModificationTime;
    @Column
    private Long lastModifierUserId;
    @Column
    private Boolean isDeleted;
    @Column
    private Long deleterUserId;
    @Column
    private LocalDateTime deletionTime;
}