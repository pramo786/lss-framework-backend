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
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Integer tenantId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "is_email_confirmed")
    private Boolean isEmailConfirmed;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_phone_number_confirmed")
    private Boolean isPhoneNumberConfirmed;

    @Column(name = "password")
    private String password;

    @Column(name = "validation_key")
    private String validationKey;

    @Column(name = "should_change_password_on_next_login")
    private Boolean shouldChangePasswordOnNextLogin;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "sign_in_token_expire_time_utc")
    private LocalDateTime signInTokenExpireTimeUtc;

    @Column(name = "sign_in_token")
    private String signInToken;

    @Column(name = "authentication_source")
    private String authenticationSource;

    @Column(name = "is_lockout_enabled")
    private Boolean isLockoutEnabled;

    @Column(name = "lockout_end_date_utc")
    private LocalDateTime lockoutEndDateUtc;

    @Column(name = "access_failed_count")
    private Integer accessFailedCount;

    @Column(name = "security_stamp")
    private String securityStamp;

    @Column(name = "concurrency_stamp")
    private String concurrencyStamp;

    @Column(name = "last_access_failed_date_time")
    private LocalDateTime lastAccessFailedDateTime;

    @Column(name = "next_reset_password_date")
    private LocalDateTime nextResetPasswordDate;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "creator_user_id")
    private Integer creatorUserId;

    @Column(name = "last_modification_time")
    private LocalDateTime lastModificationTime;

    @Column(name = "last_modifier_user_id")
    private Integer lastModifierUserId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleter_user_id")
    private Integer deleterUserId;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;
}
