# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.45)
# Database: gadmins
# Generation Time: 2020-02-17 06:04:11 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_admin_accout
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_accout`;

CREATE TABLE `sys_admin_accout` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

LOCK TABLES `sys_admin_accout` WRITE;
/*!40000 ALTER TABLE `sys_admin_accout` DISABLE KEYS */;

INSERT INTO `sys_admin_accout` (`id`, `name`, `password`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1,'admin','123456',NULL,NULL,'2020-02-16 15:20:39','2020-02-16 17:20:01',1),
	(2,'e','we',NULL,NULL,'2020-02-16 17:05:13','2020-02-16 17:20:03',1);

/*!40000 ALTER TABLE `sys_admin_accout` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_admin_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_dict`;

CREATE TABLE `sys_admin_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `d_code` varchar(255) NOT NULL DEFAULT '' COMMENT '编码',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '字典标题',
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
  `fun_group` varchar(255) NOT NULL DEFAULT 'admin' COMMENT '功能组',
  `fun_code` varchar(255) NOT NULL DEFAULT '' COMMENT '功能编码',
  `title` varchar(255) DEFAULT '' COMMENT '功能标题',
  `fun_desc` varchar(255) DEFAULT NULL COMMENT '功能描述',
  `front_url` varchar(255) DEFAULT NULL COMMENT '前端路由',
  `p_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统功能表';

LOCK TABLES `sys_admin_function` WRITE;
/*!40000 ALTER TABLE `sys_admin_function` DISABLE KEYS */;

INSERT INTO `sys_admin_function` (`id`, `fun_group`, `fun_code`, `title`, `fun_desc`, `front_url`, `p_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1,'admin','sys.user.list','','查询账户','/system/accout',NULL,NULL,NULL,'2020-02-14 17:18:27','2020-02-16 16:14:15',1),
	(2,'admin','sys.user.add','新增','新增用户','/system/accout/add',1,NULL,NULL,'2020-02-14 17:20:15','2020-02-16 16:14:28',1),
	(3,'admin','sys.ss','',NULL,'/system/test',NULL,NULL,NULL,'2020-02-15 18:00:04','2020-02-16 13:19:19',1),
	(4,'admin','sys.ss','',NULL,'/sysuser',NULL,NULL,NULL,'2020-02-15 20:14:34','2020-02-16 12:21:34',1),
	(5,'admin','','',NULL,'/system/role',NULL,NULL,NULL,'2020-02-16 12:19:16','2020-02-16 16:11:20',1),
	(6,'admin','','',NULL,'/system/menu',NULL,NULL,NULL,'2020-02-16 12:19:45','2020-02-16 13:19:24',1),
	(7,'admin','','',NULL,'/system/function',NULL,NULL,NULL,'2020-02-16 12:20:06','2020-02-16 13:19:26',1),
	(8,'admin','','',NULL,'/system/dict',NULL,NULL,NULL,'2020-02-16 12:20:19','2020-02-16 13:19:27',1),
	(9,'admin','','',NULL,'/system/tablemeta',NULL,NULL,NULL,'2020-02-16 12:20:36','2020-02-16 13:19:29',1),
	(10,'admin','','',NULL,'/system/usercenter',NULL,NULL,NULL,'2020-02-16 12:21:41','2020-02-16 16:11:53',1),
	(11,'admin','','',NULL,'/system/profile',NULL,NULL,NULL,'2020-02-16 12:22:01','2020-02-16 16:12:04',1);

/*!40000 ALTER TABLE `sys_admin_function` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_admin_function_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_function_config`;

CREATE TABLE `sys_admin_function_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fun_id` int(11) NOT NULL COMMENT '功能ID',
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

LOCK TABLES `sys_admin_function_config` WRITE;
/*!40000 ALTER TABLE `sys_admin_function_config` DISABLE KEYS */;

INSERT INTO `sys_admin_function_config` (`id`, `fun_id`, `api_url`, `api_method`, `common_schema`, `search_schema`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(2,1,'/admin/user','GET','{\"test\":2}',NULL,NULL,NULL,'2020-02-15 11:57:39','2020-02-15 12:47:43',1);

/*!40000 ALTER TABLE `sys_admin_function_config` ENABLE KEYS */;
UNLOCK TABLES;


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
  `fun_id` int(11) DEFAULT NULL COMMENT '功能ID',
  `p_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

LOCK TABLES `sys_admin_menu` WRITE;
/*!40000 ALTER TABLE `sys_admin_menu` DISABLE KEYS */;

INSERT INTO `sys_admin_menu` (`id`, `m_code`, `txt`, `icon`, `type`, `sort_number`, `fun_id`, `p_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1,'system','系统管理','home','SYS_MENU',0,1,NULL,NULL,NULL,'2020-02-13 17:36:56','2020-02-16 13:15:53',1),
	(2,'basemgr','基础管理','database','NAV_MENU',0,NULL,1,NULL,NULL,'2020-02-14 14:16:53','2020-02-16 13:16:20',1),
	(3,'mysetting','我的设置','profile','NAV_MENU',1,NULL,1,NULL,NULL,'2020-02-14 14:17:40','2020-02-16 13:16:39',1),
	(4,'accout','账户管理','user','MENU',0,1,2,NULL,NULL,'2020-02-14 14:18:38','2020-02-16 13:16:57',1),
	(5,'role','角色管理','home','MENU',1,5,2,NULL,NULL,'2020-02-14 14:19:03','2020-02-16 13:17:11',1),
	(6,'menu','菜单管理','home','MENU',2,6,2,NULL,NULL,'2020-02-14 14:19:15','2020-02-16 13:17:16',1),
	(7,'function','功能点管理','home','MENU',3,7,2,NULL,NULL,'2020-02-14 14:19:30','2020-02-16 13:17:19',1),
	(8,'dict','字典管理','home','MENU',4,8,2,NULL,NULL,'2020-02-14 14:19:46','2020-02-16 13:17:22',1),
	(9,'tablemeta','表扩展管理','home','MENU',5,9,2,NULL,NULL,'2020-02-14 14:23:37','2020-02-16 13:17:26',1),
	(10,'usercenter','个人中心','home','MENU',0,10,3,NULL,NULL,'2020-02-14 14:20:17','2020-02-16 12:21:47',1),
	(11,'usersetting','个人设置','home','MENU',1,11,3,NULL,NULL,'2020-02-14 14:20:49','2020-02-16 12:22:20',1),
	(100,'appuser','用户中心','home','SYS_MENU',1,4,NULL,NULL,NULL,'2020-02-14 17:29:54','2020-02-16 12:14:05',1);

/*!40000 ALTER TABLE `sys_admin_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_admin_rl_function_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_rl_function_role`;

CREATE TABLE `sys_admin_rl_function_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fun_id` int(11) NOT NULL COMMENT '功能ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fun_role` (`fun_id`,`role_id`)
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



# Dump of table sys_admin_rl_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_admin_rl_user_role`;

CREATE TABLE `sys_admin_rl_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户角色关联表';

LOCK TABLES `sys_admin_rl_user_role` WRITE;
/*!40000 ALTER TABLE `sys_admin_rl_user_role` DISABLE KEYS */;

INSERT INTO `sys_admin_rl_user_role` (`id`, `user_id`, `role_id`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1,1,1,NULL,NULL,'2020-02-16 17:20:14','2020-02-16 17:20:16',1),
	(2,2,2,NULL,NULL,'2020-02-16 17:20:22','2020-02-16 17:22:17',1);

/*!40000 ALTER TABLE `sys_admin_rl_user_role` ENABLE KEYS */;
UNLOCK TABLES;


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

LOCK TABLES `sys_admin_role` WRITE;
/*!40000 ALTER TABLE `sys_admin_role` DISABLE KEYS */;

INSERT INTO `sys_admin_role` (`id`, `r_code`, `name`, `r_desc`, `super_admin`, `created_by`, `updated_by`, `created_at`, `updated_at`, `enable`)
VALUES
	(1,'superadmin','超级管理员','超管',1,NULL,NULL,'2020-02-16 13:57:28','2020-02-16 17:22:08',1),
	(2,'normal','基本用户','基本用户',0,NULL,NULL,'2020-02-16 17:20:40','2020-02-16 17:22:11',1),
	(3,'vip','会员','会员',0,NULL,NULL,'2020-02-17 09:46:19','2020-02-17 09:46:19',1);

/*!40000 ALTER TABLE `sys_admin_role` ENABLE KEYS */;
UNLOCK TABLES;


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
