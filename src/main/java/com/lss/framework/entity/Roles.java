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
public class Roles {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long tenantId;
    @Column
    private String name;
    @Column
    private String displayName;
    @Column
    private Long creatorUserId;
    @Column
    private LocalDateTime creatorDateTime;
    @Column
    private LocalDateTime lastModificationTime;
    @Column
    private Long lastModifierUserId;
}