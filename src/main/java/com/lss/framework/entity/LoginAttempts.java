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
public class LoginAttempts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer userId;
    @Column
    private String userName;
    @Column
    private String ipAddress;
    @Column
    private LocalDateTime attemptedAt;
    @Column
    private Boolean isSuccessful;
    @Column
    private String source;
    @Column
    private String failureReason;
}