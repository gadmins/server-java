# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.47)
# Database: gadmins
# Generation Time: 2020-03-05 08:50:31 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_admin_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_account`;

CREATE TABLE `sys_admin_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统账号表';



# Dump of table sys_admin_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_dict`;

CREATE TABLE `sys_admin_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `d_code` varchar(255) NOT NULL DEFAULT '' COMMENT '编码',
  `title` varchar(255) DEFAULT NULL COMMENT '字典标题',
  `index_value` int(11) DEFAULT NULL COMMENT '索引值',
  `d_value` varchar(255) DEFAULT NULL COMMENT '值',
  `p_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统字典表';



# Dump of table sys_admin_function
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_function`;

CREATE TABLE `sys_admin_function` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `func_group` varchar(255) NOT NULL DEFAULT 'admin' COMMENT '功能组',
  `func_code` varchar(255) NOT NULL COMMENT '功能编码',
  `func_desc` varchar(255) DEFAULT NULL COMMENT '功能描述',
  `title` varchar(255) DEFAULT NULL COMMENT '功能标题',
  `btn_group` enum('TOOLBAR','OP') DEFAULT NULL COMMENT '按钮组',
  `btn_icon` varchar(100) DEFAULT NULL COMMENT '按钮图标',
  `elink` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是外链',
  `front_url` varchar(255) DEFAULT NULL COMMENT '前端路由',
  `p_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `sort_number` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统功能表';



# Dump of table sys_admin_function_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_function_config`;

CREATE TABLE `sys_admin_function_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `func_id` int(11) unsigned NOT NULL COMMENT '功能ID',
  `api_url` varchar(255) DEFAULT NULL COMMENT '功能接口api',
  `api_method` enum('GET','POST','PUT','DELETE') DEFAULT 'GET' COMMENT '请求方法',
  `common_schema` longtext COMMENT '通用schema',
  `search_schema` longtext COMMENT '查询schema',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`,`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统功能配置表-前端展示';



# Dump of table sys_admin_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_log`;

CREATE TABLE `sys_admin_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ip` varchar(30) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `log` text NOT NULL COMMENT '日志内容',
  `tag` varchar(255) DEFAULT NULL COMMENT '标记',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';



# Dump of table sys_admin_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_menu`;

CREATE TABLE `sys_admin_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `m_code` varchar(255) NOT NULL DEFAULT '' COMMENT '编码',
  `txt` varchar(255) DEFAULT NULL COMMENT '菜单文本',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `type` enum('SYS_MENU','NAV_MENU','MENU') NOT NULL DEFAULT 'MENU' COMMENT '菜单类型',
  `func_id` int(11) DEFAULT NULL COMMENT '功能ID',
  `sort_number` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `p_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';



# Dump of table sys_admin_rl_account_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_rl_account_role`;

CREATE TABLE `sys_admin_rl_account_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role` (`account_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统账号角色关联表';



# Dump of table sys_admin_rl_function_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_rl_function_role`;

CREATE TABLE `sys_admin_rl_function_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `func_id` int(11) NOT NULL COMMENT '功能ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `func_role` (`func_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色功能关联表';



# Dump of table sys_admin_rl_menu_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_rl_menu_role`;

CREATE TABLE `sys_admin_rl_menu_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_role` (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色菜单关联表';



# Dump of table sys_admin_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_role`;

CREATE TABLE `sys_admin_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `r_code` varchar(255) NOT NULL DEFAULT '' COMMENT '角色编码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名',
  `r_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `super_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否超管',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;




INSERT INTO `sys_admin_function` (`id`, `func_group`, `func_code`, `func_desc`, `title`, `btn_group`, `btn_icon`, `elink`, `front_url`, `p_id`, `sort_number`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'admin', 'sys:welcome', '欢迎页', '欢迎页', NULL, NULL, 0, '/home/welcome', NULL, 0, NULL, NULL, '2020-02-28 07:16:14', '2020-02-28 07:22:52', 1);


INSERT INTO `sys_admin_menu` (`id`,`m_code`, `txt`, `icon`, `type`, `func_id`, `sort_number`, `p_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'home', '首页', 'home', 'SYS_MENU', NULL, 0, NULL, NULL, NULL, '2020-02-28 07:14:47', '2020-02-28 07:14:47', 1),
	(2, 'welcome', '欢迎', 'home', 'MENU', 1, 0, 1, NULL, NULL, '2020-02-28 07:15:25', '2020-02-28 07:20:20', 1),
	(3, 'system', '系统管理', 'home', 'SYS_MENU', NULL, 1, NULL, NULL, NULL, '2020-02-13 17:36:56', '2020-02-28 06:58:39', 1),
	(4, 'basemgr', '基础管理', 'profile', 'NAV_MENU', NULL, 0, 3, NULL, NULL, '2020-02-14 14:16:53', '2020-02-26 02:34:08', 1),
	(5, 'mysetting', '我的设置', 'profile', 'NAV_MENU', NULL, 1, 3, NULL, NULL, '2020-02-14 14:17:40', '2020-02-16 13:16:39', 1);

INSERT INTO `sys_admin_role` (`id`, `r_code`, `name`, `r_desc`, `super_admin`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'admin', '超级管理员', '超级管理员', 1, NULL, NULL, '2020-03-04 09:44:00', '2020-03-04 09:47:40', 1);

INSERT INTO `sys_admin_account` (`id`, `name`, `password`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '2020-02-16 15:20:39', '2020-02-25 06:03:27', 1);

INSERT INTO `sys_admin_rl_account_role` (`id`, `account_id`, `role_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1, 1, 1, NULL, NULL, '2020-03-04 09:44:12', '2020-03-04 09:48:28', 1);



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
