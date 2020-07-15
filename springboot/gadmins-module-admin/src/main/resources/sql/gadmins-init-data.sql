truncate table `sys_admin_function`;
INSERT INTO `sys_admin_function` (`id`, `func_group`, `func_code`, `func_desc`, `title`, `btn_group`, `btn_icon`, `elink`, `front_url`, `menu_func_id`, `p_id`, `sort_number`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'admin', 'sys:welcome', '欢迎页', '欢迎页', NULL, NULL, 0, '/home/welcome', 1, NULL, 0, NULL, NULL, '2020-02-28 07:16:14', NULL, 1);

truncate table `sys_admin_menu`;
INSERT INTO `sys_admin_menu` (`id`,`m_code`, `txt`, `icon`, `type`, `func_id`, `sort_number`, `p_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'home', '首页', 'home', 'SYS_MENU', NULL, 0, NULL, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(2, 'welcome', '欢迎', 'home', 'MENU', 1, 0, 1, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(3, 'system', '系统管理', 'home', 'SYS_MENU', NULL, 1, NULL, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(4, 'basemgr', '基础管理', 'profile', 'NAV_MENU', NULL, 0, 3, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(5, 'settings', '基本设置', 'profile', 'NAV_MENU', NULL, 1, 3, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(6, 'devops', '研发管理', 'tool', 'NAV_MENU', NULL, 2, 3, NULL, NULL, '2020-02-28 07:16:14', NULL, 1);

truncate table `sys_admin_role`;
INSERT INTO `sys_admin_role` (`id`, `r_code`, `name`, `r_desc`, `super_admin`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'role:superadmin', '超级管理员', '超级管理员', 1, NULL, NULL, '2020-02-28 07:16:14', NULL, 1);

truncate table `sys_admin_account`;
INSERT INTO `sys_admin_account` (`id`, `name`, `password`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'devadmin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '2020-02-28 07:16:14', NULL, 1);

truncate table `sys_admin_rl_account_role`;
INSERT INTO `sys_admin_rl_account_role` (`id`, `account_id`, `role_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 1, 1, NULL, NULL, '2020-02-28 07:16:14', NULL, 1),
	(2, 2, 1, NULL, NULL, '2020-02-28 07:16:14', NULL, 1);