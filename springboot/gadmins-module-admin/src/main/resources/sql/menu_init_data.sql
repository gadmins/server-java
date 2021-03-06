truncate table `sys_admin_function`;
INSERT INTO `sys_admin_function` (`id`, `func_group`, `func_code`, `func_desc`, `title`, `btn_group`, `btn_icon`, `elink`, `front_url`, `menu_func_id`, `p_id`, `sort_number`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'admin', 'sys:welcome', '欢迎页', '欢迎页', NULL, NULL, 0, '/home/welcome', 1, NULL, 0, NULL, NULL, '2020-02-28 07:16:14', NULL, 1);

truncate table `sys_admin_menu`;
INSERT INTO `sys_admin_menu` (`id`,`m_code`, `txt`, `icon`, `type`, `func_id`, `sort_number`, `p_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'home', '首页', 'home', 'SYS_MENU', NULL, 0, NULL, NULL, NULL, '2020-02-28 07:14:47', NULL, 1),
	(2, 'welcome', '欢迎', 'home', 'MENU', 1, 0, 1, NULL, NULL, '2020-02-28 07:15:25', NULL, 1),
	(3, 'system', '系统管理', 'home', 'SYS_MENU', NULL, 1, NULL, NULL, NULL, '2020-02-13 17:36:56', NULL, 1),
	(4, 'basemgr', '基础管理', 'profile', 'NAV_MENU', NULL, 0, 3, NULL, NULL, '2020-02-14 14:16:53', NULL, 1),
	(5, 'settings', '基本设置', 'profile', 'NAV_MENU', NULL, 1, 3, NULL, NULL, '2020-02-14 14:17:40', NULL, 1),
	(6, 'devops', '研发管理', 'tool', 'NAV_MENU', NULL, 2, 3, NULL, NULL, '2020-02-14 14:17:40', NULL, 1);