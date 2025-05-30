CREATE TABLE control_file (
  id INT PRIMARY KEY AUTO_INCREMENT,
  col_name NVARCHAR(255) NULL,
  col_value NVARCHAR(255) NULL
);

CREATE TABLE country (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  organization_unit_id INT NULL,
  country NVARCHAR(255) NULL,
  checked BIT NULL,
  checker_user_id INT NULL,
  checker_date_time DATETIME NULL,
  creation_time DATETIME NULL,
  creator_user_id INT NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL
);

CREATE TABLE editions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name NVARCHAR(255) NULL,
  display_name NVARCHAR(255) NULL,
  creator_user_id INT NULL,
  creator_date_time DATETIME NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL
);

CREATE TABLE fetures (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name NVARCHAR(255) NULL,
  "value" NVARCHAR(255) NULL,
  edition_id INT NULL,
  creator_user_id INT NULL,
  creator_date_time DATETIME NULL
);

CREATE TABLE loginattempts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NULL,
  user_name NVARCHAR(255) NULL,
  ip_address NVARCHAR(255) NULL,
  attempted_at DATETIME NULL,
  is_successful BIT NULL,
  source NVARCHAR(255) NULL,
  failure_reason NVARCHAR(255) NULL
);

CREATE TABLE loginaudit (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  user_id INT NULL,
  login_date DATETIME NULL,
  login_time NVARCHAR(255) NULL,
  login_ip NVARCHAR(255) NULL,
  log_out_date DATETIME NULL,
  log_out_time NVARCHAR(255) NULL
);

CREATE TABLE organizationunits (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  name NVARCHAR(255) NULL,
  is_active BIT NULL,
  creation_time DATETIME NULL,
  creator_user_id INT NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL
);

CREATE TABLE permissions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  user_id INT NULL,
  role_id INT NULL,
  permission NVARCHAR(255) NULL,
  feature NVARCHAR(255) NULL,
  discriminator NVARCHAR(255) NULL,
  creator_user_id INT NULL,
  creator_date_time DATETIME NULL
);

CREATE TABLE recentpasswords (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  user_id INT NULL,
  password NVARCHAR(255) NULL,
  creation_time DATETIME NULL
);

CREATE TABLE roles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  name NVARCHAR(255) NULL,
  display_name NVARCHAR(255) NULL,
  creator_user_id INT NULL,
  creator_date_time DATETIME NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL
);

CREATE TABLE tenants (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenancy_name NVARCHAR(255) NULL,
  name NVARCHAR(255) NULL,
  edition_id INT NULL,
  is_active BIT NULL,
  creation_time DATETIME NULL,
  creator_user_id INT NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL,
  is_deleted BIT NULL,
  deleter_user_id INT NULL,
  deletion_time DATETIME NULL
);

CREATE TABLE userauditdtl (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  user_id INT NULL,
  audit_date DATETIME NULL,
  "type" NVARCHAR(255) NULL,
  sub_type NVARCHAR(255) NULL,
  reason NVARCHAR(255) NULL,
  updated_by INT NULL
);

CREATE TABLE userorganizationunits (
  id INT PRIMARY KEY AUTO_INCREMENT,
  organization_unit_id INT NULL,
  user_id INT NULL,
  is_deleted BIT NULL,
  deletion_time DATETIME NULL,
  deletion_user_id INT NULL
);

CREATE TABLE userroles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  user_id INT NULL,
  role_id INT NULL,
  creator_user_id INT NULL,
  creator_date_time DATETIME NULL
);

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenant_id INT NULL,
  name NVARCHAR(255) NULL,
  surname NVARCHAR(255) NULL,
  user_name NVARCHAR(255) NULL,
  email_address NVARCHAR(255) NULL,
  is_email_confirmed BIT NULL,
  phone_number NVARCHAR(255) NULL,
  is_phone_number_confirmed BIT NULL,
  password NVARCHAR(255) NULL,
  validation_key NVARCHAR(255) NULL,
  should_change_password_on_next_login BIT NULL,
  is_active BIT NULL,
  sign_in_token_expire_time_utc DATETIME NULL,
  sign_in_token NVARCHAR(255) NULL,
  authentication_source NVARCHAR(255) NULL,
  is_lockout_enabled BIT NULL,
  lockout_end_date_utc DATETIME NULL,
  access_failed_count INT NULL,
  security_stamp NVARCHAR(255) NULL,
  concurrency_stamp NVARCHAR(255) NULL,
  last_access_failed_date_time DATETIME NULL,
  next_reset_password_date DATETIME NULL,
  creation_time DATETIME NULL,
  creator_user_id INT NULL,
  last_modification_time DATETIME NULL,
  last_modifier_user_id INT NULL,
  is_deleted BIT NULL,
  deleter_user_id INT NULL,
  deletion_time DATETIME NULL
);

