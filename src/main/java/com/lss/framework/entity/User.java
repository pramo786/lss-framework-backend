package com.lss.framework.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "TenantId")
    private Integer tenantId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "IsEmailConfirmed")
    private Boolean isEmailConfirmed;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "IsPhoneNumberConfirmed")
    private Boolean isPhoneNumberConfirmed;

    @Column(name = "Password")
    private String password;

    @Column(name = "ValidationKey")
    private String validationKey;

    @Column(name = "ShouldChangePasswordOnNextLogin")
    private Boolean shouldChangePasswordOnNextLogin;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "SignInTokenExpireTimeUtc")
    private LocalDateTime signInTokenExpireTimeUtc;

    @Column(name = "SignInToken", columnDefinition = "TEXT")
    private String signInToken;

    @Column(name = "AuthenticationSource")
    private String authenticationSource;

    @Column(name = "IsLockoutEnabled")
    private Boolean isLockoutEnabled;

    @Column(name = "LockoutEndDateUtc")
    private LocalDateTime lockoutEndDateUtc;

    @Column(name = "AccessFailedCount")
    private Integer accessFailedCount;

    @Column(name = "SecurityStamp")
    private String securityStamp;

    @Column(name = "ConcurrencyStamp")
    private String concurrencyStamp;

    @Column(name = "LastAccessFailedDateTime")
    private LocalDateTime lastAccessFailedDateTime;

    @Column(name = "NextResetPasswordDate")
    private LocalDateTime nextResetPasswordDate;

    @Column(name = "CreationTime")
    private LocalDateTime creationTime;

    @Column(name = "CreatorUserId")
    private Integer creatorUserId;

    @Column(name = "LastModificationTime")
    private LocalDateTime lastModificationTime;

    @Column(name = "LastModifierUserId")
    private Integer lastModifierUserId;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "DeleterUserId")
    private Integer deleterUserId;

    @Column(name = "DeletionTime")
    private LocalDateTime deletionTime;
}
