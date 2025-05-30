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
public class UserOrganizationUnits {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long organizationUnitId;
    @Column
    private Integer userId;
    @Column
    private Boolean isDeleted;
    @Column
    private LocalDateTime deletionTime;
    @Column
    private Integer deletionUserId;
}