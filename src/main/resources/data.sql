
-- Control_File
insert into control_file (col_name, col_value) values ('APP_VERSION', '1.0.0');
insert into control_file  (col_name, col_value) values ('MAINTENANCE_MODE', 'o_f_f');
-- editions
insert into editions (name, display_name, creator_user_id, creator_date_time) values ('basic', 'basic edition', 1, '2025-05-30 11:45:41.512040');
insert into editions (name, display_name, creator_user_id, creator_date_time) values ('pro', 'pro edition', 1, '2025-05-30 11:45:41.512040');
-- fetures
insert into fetures (name, "value", edition_id, creator_user_id, creator_date_time) values ('max_users', '10', 1, 1, '2025-05-30 11:45:41.512040');
insert into fetures (name, "value", edition_id, creator_user_id, creator_date_time) values ('storage', '50GB', 2, 1, '2025-05-30 11:45:41.512040');
-- tenants
insert into tenants (tenancy_name, name, edition_id, is_active, creation_time) values ('tenant1', 'tenant one', 1, true, '2025-05-30 11:45:41.512040');
insert into tenants (tenancy_name, name, edition_id, is_active, creation_time) values ('tenant2', 'tenant two', 2, true, '2025-05-30 11:45:41.512040');
-- users
insert into users (tenant_id, name, surname, user_name, email_address, is_email_confirmed,
            phone_number, is_phone_number_confirmed, password, should_change_password_on_next_login, is_active,
            is_lockout_enabled, access_failed_count, creation_time)
            values (1, 'john', 'doe', 'johndoe', 'john@example.com', true, '1234567890', true,
            'password123', false, true, false, 0, '2025-05-30 11:45:41.512040');
insert into users (tenant_id, name, surname, user_name, email_address, is_email_confirmed,
            phone_number, is_phone_number_confirmed, password, should_change_password_on_next_login, is_active,
            is_lockout_enabled, access_failed_count, creation_time)
            values (2, 'jane', 'smith', 'janesmith', 'jane@example.com', true, '0987654321', true,
            'securepass', false, true, false, 0, '2025-05-30 11:45:41.512040');
-- roles
insert into roles (tenant_id, name, display_name, creator_user_id, creator_date_time) values (1, 'admin', 'administrator', 1, '2025-05-30 11:45:41.512040');
insert into roles (tenant_id, name, display_name, creator_user_id, creator_date_time) values (2, 'user', 'standard user', 2, '2025-05-30 11:45:41.512040');
-- permissions
insert into permissions (tenant_id, user_id, role_id, permission, feature, discriminator, creator_user_id, creator_date_time) values (1, 1, 1, 'USER_MANAGE', 'u_i', 'user', 1, '2025-05-30 11:45:41.512040');
insert into permissions (tenant_id, user_id, role_id, permission, feature, discriminator, creator_user_id, creator_date_time) values (2, 2, 2, 'VIEW_DASHBOARD', 'u_i', 'user', 2, '2025-05-30 11:45:41.512040');
-- login_attempts
insert into loginattempts (user_id, user_name, ip_address, attempted_at, is_successful, source) values (1, 'johndoe', '192.168.1.100', '2025-05-30 11:45:41.512040', true, 'web');
insert into loginattempts (user_id, user_name, ip_address, attempted_at, is_successful, source, failure_reason) values (2, 'janesmith', '192.168.1.101', '2025-05-30 11:45:41.512040', false, 'mobile', 'incorrect password');
-- country
insert into country (tenant_id, organization_unit_id, country, checked, creation_time) values (1, 1, 'india', true, '2025-05-30 11:45:41.512040');
insert into country (tenant_id, organization_unit_id, country, checked, creation_time) values (2, 2, 'u_s_a', false, '2025-05-30 11:45:41.512040');

-- ORGANIZATION_UNITS sample data
INSERT INTO organizationunits (id, tenant_id, name, is_active, creation_time, creator_user_id) VALUES
  (100, 1, 'HR Department', true, CURRENT_TIMESTAMP, 1),
  (101, 1, 'Finance Department', true, CURRENT_TIMESTAMP, 1);

-- USER_ORGANIZATION_UNITS sample mapping for user_id = 1
INSERT INTO userorganizationunits (id, organization_unit_id, user_id, is_deleted) VALUES  (1, 100, 1, false);
