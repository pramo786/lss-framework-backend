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
public class Fetures {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String value;
    @Column
    private Long editionId;
    @Column
    private Long creatorUserId;
    @Column
    private LocalDateTime creatorDateTime;
}