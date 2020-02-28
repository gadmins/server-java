# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.47)
# Database: gadmins
# Generation Time: 2020-02-27 05:19:34 +0000
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
  `func_code` varchar(255) NOT NULL DEFAULT '' COMMENT '功能编码',
  `func_desc` varchar(255) DEFAULT NULL COMMENT '功能描述',
  `title` varchar(255) DEFAULT '' COMMENT '功能标题',
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
  `func_id` int(11) NOT NULL COMMENT '功能ID',
  `api_url` varchar(255) DEFAULT NULL COMMENT '功能接口api',
  `api_method` enum('GET','POST','PUT','DELETE') DEFAULT 'GET' COMMENT '请求方法',
  `common_schema` longtext COMMENT '通用schema',
  `search_schema` longtext COMMENT '查询schema',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统功能配置表-前端展示';



# Dump of table sys_admin_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_log`;

CREATE TABLE `sys_admin_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
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
  `sort_number` int(11) DEFAULT NULL COMMENT '排序',
  `func_id` int(11) DEFAULT NULL COMMENT '功能ID',
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
  UNIQUE KEY `fun_role` (`func_id`,`role_id`)
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



# Dump of table sys_admin_table_meta_content
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_table_meta_content`;

CREATE TABLE `sys_admin_table_meta_content` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `data_id` int(11) NOT NULL COMMENT '数据ID',
  `table_meta_id` int(11) NOT NULL COMMENT '元信息ID',
  `feild_value` varchar(255) NOT NULL DEFAULT '' COMMENT '值',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统元信息扩展数据表';



# Dump of table sys_admin_table_meta_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_table_meta_info`;

CREATE TABLE `sys_admin_table_meta_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `table_name` varchar(255) NOT NULL DEFAULT '' COMMENT '表名',
  `filed_name` varchar(255) NOT NULL DEFAULT '' COMMENT '字段名',
  `value_type` enum('string','number','bool') NOT NULL DEFAULT 'string' COMMENT '值类型',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统元信息扩展表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
