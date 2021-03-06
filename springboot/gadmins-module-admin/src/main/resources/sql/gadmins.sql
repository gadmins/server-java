# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.47)
# Database: gadmins
# Generation Time: 2020-07-09 01:31:11 +0000
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
  `vaild_error_times` int(11) DEFAULT NULL COMMENT '验证错误次数',
  `lock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账户锁定',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `menu_func_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联菜单功能ID，可能就是自己',
  `virtual_menu` tinyint(1) NOT NULL DEFAULT '0' COMMENT '虚拟菜单',
  `sort_number` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `data_schema` longtext COMMENT '通用schema',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`,`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统功能配置表-前端展示';



# Dump of table sys_admin_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_log`;

CREATE TABLE `sys_admin_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `log` text NOT NULL COMMENT '日志内容',
  `uri` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `tag` varchar(255) DEFAULT NULL COMMENT '标记',
  `ip` varchar(30) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';



# Dump of table sys_dataway_api
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dataway_api`;

CREATE TABLE `sys_dataway_api` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` int(11) NOT NULL COMMENT '分组ID',
  `api_method` varchar(12) NOT NULL COMMENT '请求方法',
  `api_path` varchar(512) NOT NULL COMMENT '请求路径',
  `api_comment` varchar(255) NOT NULL COMMENT '注释',
  `api_script` mediumtext NOT NULL COMMENT '脚本内容',
  `api_req_schema` mediumtext NOT NULL COMMENT '请求结构',
  `api_resp_schema` mediumtext NOT NULL COMMENT '响应结构',
  `script_type` varchar(12) NOT NULL COMMENT '脚本类型',
  `status` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态：0 草稿 1 发布',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='dataway接口表';



# Dump of table sys_dataway_function
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dataway_function`;

CREATE TABLE `sys_dataway_function` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `api_id` int(11) unsigned NOT NULL COMMENT 'api id',
  `menu` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否关联菜单',
  `f_code` varchar(255) NOT NULL COMMENT '功能编码',
  `f_name` varchar(255) NOT NULL COMMENT '功能名称',
  `p_f_code` varchar(255) DEFAULT NULL COMMENT '父级功能编码',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='dataway功能表';



# Dump of table sys_dataway_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dataway_group`;

CREATE TABLE `sys_dataway_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_type` varchar(50) NOT NULL COMMENT '分组',
  `url_prefix` varchar(100) NOT NULL COMMENT '分组URL前缀',
  `desc` varchar(255) NOT NULL COMMENT '组描述',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='dataway分组表';



# Dump of table sys_dataway_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dataway_menu`;

CREATE TABLE `sys_dataway_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` int(11) NOT NULL COMMENT 'group id',
  `p_code` varchar(255) NOT NULL COMMENT '菜单父级编码',
  `m_code` varchar(255) NOT NULL COMMENT '菜单编码',
  `title` varchar(255) NOT NULL COMMENT '菜单标题',
  `link` varchar(255) NOT NULL COMMENT '菜单链接',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='dataway菜单表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
