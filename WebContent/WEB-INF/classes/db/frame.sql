/*
SQLyog Community Edition- MySQL GUI v5.21
Host - 5.5.22 : Database - frame
*********************************************************************
Server version : 5.5.22
*/

SET NAMES utf8;

SET SQL_MODE='';

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `sys_domain` */

DROP TABLE IF EXISTS `sys_domain`;

CREATE TABLE `sys_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_code` varchar(255) NOT NULL,
  `domain_name` varchar(255) NOT NULL,
  `domain_short_name` varchar(255) NOT NULL,
  `domain_title_key` varchar(255) NOT NULL,
  `domain_icon` varchar(255) DEFAULT NULL,
  `domain_small_icon` varchar(255) DEFAULT NULL,
  `sort_index` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_domain` */

insert  into `sys_domain`(`id`,`domain_code`,`domain_name`,`domain_short_name`,`domain_title_key`,`domain_icon`,`domain_small_icon`,`sort_index`,`status`,`remarks`) values (1,'SYS','系统管理','系统','SYSTEM',NULL,NULL,1,'0',NULL),(2,'DAEMS','专用设备图形化控制管理系统','图形化系统','DAEMS',NULL,NULL,2,'0',NULL);

/*Table structure for table `sys_module` */

DROP TABLE IF EXISTS `sys_module`;

CREATE TABLE `sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_id` int(11) NOT NULL,
  `parent_module_id` int(11) NOT NULL,
  `module_code` varchar(255) NOT NULL,
  `module_name` varchar(255) NOT NULL,
  `module_icon` varchar(255) DEFAULT NULL,
  `module_url` varchar(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'module' COMMENT '{module,action}',
  `sort_index` int(11) DEFAULT NULL,
  `remarks` text,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `domainIdIndex` (`domain_id`,`parent_module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_module` */

insert  into `sys_module`(`id`,`domain_id`,`parent_module_id`,`module_code`,`module_name`,`module_icon`,`module_url`,`type`,`sort_index`,`remarks`,`status`) values (1,1,0,'DOMAIN_MANAGE','域管理',NULL,'admin/domains','module',1,NULL,NULL),(2,1,0,'USER_MANAGE','用户管理','','admin/users','module',3,'',NULL),(3,1,0,'ROLE_MANAGE','角色管理','','admin/roles','module',2,'角色管理',NULL),(4,2,0,'MAIN_PAGE','首页','','','module',NULL,'',NULL),(5,2,0,'SYS_MANAGER','系统管理','','','module',NULL,'',NULL);

/*Table structure for table `sys_property` */

DROP TABLE IF EXISTS `sys_property`;

CREATE TABLE `sys_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `property_catalog` varchar(255) NOT NULL,
  `property_code` varchar(255) NOT NULL,
  `property_name` varchar(255) NOT NULL,
  `property_value` text,
  `status` varchar(20) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_property` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_id` int(11) NOT NULL DEFAULT '0',
  `role_code` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_type` varchar(50) DEFAULT NULL,
  `default_flag` varchar(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `roleTypeIndex` (`role_type`,`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`domain_id`,`role_code`,`role_name`,`role_type`,`default_flag`,`status`,`remarks`) values (1,1,'ADMIN','admin',NULL,'default',NULL,'管理员角色'),(2,2,'admin','admin',NULL,'',NULL,'管理员角色');

/*Table structure for table `sys_role_module_relate` */

DROP TABLE IF EXISTS `sys_role_module_relate`;

CREATE TABLE `sys_role_module_relate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `roleIdIndex` (`role_id`,`module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_module_relate` */

insert  into `sys_role_module_relate`(`id`,`role_id`,`module_id`,`status`,`remarks`) values (1,1,1,NULL,NULL),(2,1,2,NULL,NULL),(3,1,3,NULL,NULL);

/*Table structure for table `sys_role_relate` */

DROP TABLE IF EXISTS `sys_role_relate`;

CREATE TABLE `sys_role_relate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_group_id` int(11) NOT NULL,
  `related_sys_role_id` int(11) NOT NULL,
  `related_sys_role_code` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `sysRoleGroupIdIndex` (`sys_role_group_id`) USING BTREE,
  KEY `relatedSysRoleIdIndex` (`related_sys_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_relate` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_account` varchar(255) NOT NULL,
  `password` text,
  `user_type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `userAccountIndex` (`user_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`user_account`,`password`,`user_type`,`status`,`remarks`) values (1,'zhucz','zhucz','ee11cbb19052e40b07aac0ca060c23ee',NULL,NULL,NULL),(2,'user','user','ee11cbb19052e40b07aac0ca060c23ee',NULL,NULL,'普通用户');

/*Table structure for table `sys_user_role_relate` */

DROP TABLE IF EXISTS `sys_user_role_relate`;

CREATE TABLE `sys_user_role_relate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `expired_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`id`),
  KEY `roleIdIndex` (`role_id`) USING BTREE,
  KEY `userIdIndex` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role_relate` */

insert  into `sys_user_role_relate`(`id`,`user_id`,`role_id`,`expired_date`,`status`,`remarks`) values (1,1,1,NULL,NULL,NULL),(2,2,2,NULL,NULL,NULL);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
