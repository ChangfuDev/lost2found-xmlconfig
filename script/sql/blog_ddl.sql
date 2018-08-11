-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 blog 的数据库结构
DROP DATABASE IF EXISTS `blog`;
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog`;

-- 导出  表 blog.article 结构
DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `en_title` varchar(50) NOT NULL,
  `author_name` varchar(20) NOT NULL,
  `written_date` date NOT NULL,
  `type_id` varchar(50) NOT NULL,
  `physical_path` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='存储每篇文章的信息';

-- 数据导出被取消选择。
-- 导出  表 blog.type 结构
DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `id` varchar(50) NOT NULL,
  `type_name` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储文章的分类信息';

-- 数据导出被取消选择。
-- 导出  表 blog.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储用户的信息';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
