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
public class Permissions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long tenantId;
    @Column
    private Long userId;
    @Column
    private Long roleId;
    @Column
    private String permission;
    @Column
    private String feature;
    @Column
    private String discriminator;
    @Column
    private Long creatorUserId;
    @Column
    private LocalDateTime creatorDateTime;
}