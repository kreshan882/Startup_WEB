-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: testk
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `testk`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `testk` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `testk`;

--
-- Table structure for table `dma_cast`
--

DROP TABLE IF EXISTS `dma_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dma_cast` (
  `CAST_ID` varchar(2) NOT NULL,
  `CAST_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`CAST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dma_cast`
--

LOCK TABLES `dma_cast` WRITE;
/*!40000 ALTER TABLE `dma_cast` DISABLE KEYS */;
INSERT INTO `dma_cast` VALUES ('01','Kalar'),('02','Maraver'),('03','Agamudiyar'),('04','Other Cast');
/*!40000 ALTER TABLE `dma_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dma_member`
--

DROP TABLE IF EXISTS `dma_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dma_member` (
  `MEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MEM_NAME` varchar(45) DEFAULT NULL,
  `MEM_NIC` varchar(15) DEFAULT NULL,
  `MEM_DOB` date DEFAULT NULL,
  `MEM_PHONE` varchar(15) DEFAULT NULL,
  `MEM_MOBILE` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `QUALIFICATION` varchar(45) DEFAULT NULL,
  `PERM_ADD` varchar(200) DEFAULT NULL,
  `TEMP_ADD` varchar(200) DEFAULT NULL,
  `MEM_BORN_PLACE` varchar(45) DEFAULT NULL,
  `MEM_CAST` varchar(2) DEFAULT NULL,
  `MEM_SUB_CAST` varchar(45) DEFAULT NULL,
  `MEM_TYPE_ISLIFE` varchar(1) DEFAULT NULL,
  `MEM_EXP_DATE` date DEFAULT NULL,
  `NUM_OF_BRO` int(11) DEFAULT NULL,
  `NUM_OF_SIS` int(11) DEFAULT NULL,
  `JOB_TITLE` varchar(45) DEFAULT NULL,
  `JOB_ADD` varchar(200) DEFAULT NULL,
  `JOB_PHONE` varchar(15) DEFAULT NULL,
  `FAT_NAME` varchar(45) DEFAULT NULL,
  `FAT_ADD` varchar(200) DEFAULT NULL,
  `FAT_CAST` varchar(45) DEFAULT NULL,
  `MOT_NAME` varchar(45) DEFAULT NULL,
  `MOT_ADD` varchar(200) DEFAULT NULL,
  `MOT_CAST` varchar(45) DEFAULT NULL,
  `GRAN_FAT_NAME` varchar(45) DEFAULT NULL,
  `GRAN_FAT_ADD` varchar(200) DEFAULT NULL,
  `GRAN_FAT_CAST` varchar(45) DEFAULT NULL,
  `GRAN_MOT_NAME` varchar(45) DEFAULT NULL,
  `GRAN_MOT_ADD` varchar(200) DEFAULT NULL,
  `GRAN_MOT_CAST` varchar(45) DEFAULT NULL,
  `IS_MARRIED` varchar(1) DEFAULT NULL,
  `WI_NAME` varchar(45) DEFAULT NULL,
  `NUM_OF_SUN` int(11) DEFAULT NULL,
  `NUM_OF_DOT` int(11) DEFAULT NULL,
  `WI_DOB` date DEFAULT NULL,
  `WI_ADD` varchar(200) DEFAULT NULL,
  `WI_EMAIL` varchar(45) DEFAULT NULL,
  `WI_MOBILE` varchar(45) DEFAULT NULL,
  `WI_BORN_PLACE` varchar(45) DEFAULT NULL,
  `WI_CAST` varchar(45) DEFAULT NULL,
  `WI_FAT_NAME` varchar(45) DEFAULT NULL,
  `WI_FAT_BORN_PLACE` varchar(45) DEFAULT NULL,
  `WI_FAT_CAST` varchar(45) DEFAULT NULL,
  `WI_MOT_NAME` varchar(45) DEFAULT NULL,
  `WI_MOT_BORN_PLACE` varchar(45) DEFAULT NULL,
  `WI_MOT_CAST` varchar(45) DEFAULT NULL,
  `WI_GRAN_FAT_NAME` varchar(45) DEFAULT NULL,
  `WI_GRAN_FAT_BORN_PLACE` varchar(45) DEFAULT NULL,
  `WI_GRAN_FAT_CAST` varchar(45) DEFAULT NULL,
  `WI_GRAN_MOT_NAME` varchar(45) DEFAULT NULL,
  `WI_GRAN_MOT_BORN_PLACE` varchar(45) DEFAULT NULL,
  `WI_GRAN_MOT_CAST` varchar(45) DEFAULT NULL,
  `IMG_MEMBER` varchar(45) DEFAULT NULL,
  `IMG_FAMILY` varchar(45) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dma_member`
--

LOCK TABLES `dma_member` WRITE;
/*!40000 ALTER TABLE `dma_member` DISABLE KEYS */;
INSERT INTO `dma_member` VALUES (4,'kreshan','880201573V','1988-01-20','0777418581','0444','kreshan882@gmail.com','master','matale,gsgjdjsfgdf,dfrfg,frgfrddddddd','colombo','matale','01','daver','0','2020-09-22',1,0,'sw engineer','inter','0114141414','raja','matale','01','than','matale','01','nava','matale','03','magas','','02','1','krish',1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-09-22 07:22:11'),(5,'ffjhf','880201573V','2019-09-26','0777418581','0888787878','kreshan882@gmail.com','master','matale','123','kandy','01','daver','0','2019-09-26',2,2,'swengineer','inter','01114747474','raja','matale','01','than','matale','02','nava','matale','03','magas','','02','0','',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-09-27 05:42:53'),(6,'kreshan','888888848V','2019-10-10','0777418581','0888787878','kk@dd.com','master','123','colombo','matale','01','mar','0','2019-10-17',3,2,'sw engineer','inter','01114747474','raja','matale','01','than','','01','nava','','01','magas','','01','0',', , , , , ',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-10-01 07:26:12'),(7,'mugulan','888989898V','2003-10-01','2222','2222','kkk@gmail.com','dddd','222 colombo','rrrrr','matale','01','sssss','0','2021-10-14',2,4,'buss','colo','0888','dd','ddd','01','fff','fff','02','','','01','','','01','0',', , , , , ',2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-10-07 13:12:24'),(8,'mugulan','888989555V','2019-10-15','2222','2222','kkk@gmail.com','dddd','222 colombo','rrrrr','matale','01','sssss','0','2019-10-24',3,1,'buss','colo','0888','dd','ddd','fff','fff','fff','fff','','','fff','','','fffff','0','',0,0,'2019-10-15','','','','','-1','','','-1','','','-1','','','-1','','','-1',NULL,NULL,2,'2019-10-15 14:37:03'),(9,'dhanshan','914444444V','2002-10-02','07774747474','0777417474','','AL','Matale','colombo-11,kotahena','matale','02','dever','1','2019-10-16',1,0,'Accountan','malasiya','07774747475745','rajendran','matale','maraver','than','matale','maraver','navaratnam','','kalar','','','maraver','0','',0,0,'2019-10-16','','','','','','','','','','','','','','','','','','PRO_M00009.png','FAM_M00009.png',1,'2019-10-15 19:03:36'),(10,'mugulan','888989555V','2019-10-08','07774747474','0777417474','','','222 colombo','','swswdw','02','','0','2019-10-16',0,1,'','','','','','maraver','','','maraver','','','kalar','','','maraver','0','',0,0,'2019-10-16','','','','','','','','','','','','','','','','','','PRO_M00010.png',NULL,1,'2019-10-15 19:19:27'),(11,'ddd','888989898V','2019-10-16','2222','2222','kkk@gmail.com','dddd','222 colombo','rrrrr','matale','04','sssss','0','2019-10-16',1,3,'buss','colo','0888','dd','ddd','maraver','than','matale','maraver','navaratnam','dd','kalar','dd','ddd','maraver','0','',0,0,'2019-10-16','','','','','','','','','','','','','','','','','',NULL,NULL,1,'2019-10-16 16:47:41'),(12,'KreshanRajendran','880201574V','1988-01-20','0665625885','0777418581','kreshan882@gmail.com','MSC','222, kendagolla road, matale','kirulapone','Matale','02','deaver','1','2019-10-18',1,0,'Senior software Engineer','interblocks','0112525245','rajendran','matale','deaver','thanga','matale','deaver','navarathnam','matale','deaver','magashawary','matale','deaver','1','kirish',1,0,'1991-10-16','mawanella','krishdl@gmail.com','0777474747','kandy','agamudiyar','moorthy','mawanal','agamudiyar','bawani','','deaver','','','','','','','PRO_M00012.png','FAM_M00012.png',1,'2019-10-17 19:00:21');
/*!40000 ALTER TABLE `dma_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_modules`
--

DROP TABLE IF EXISTS `mt_modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_modules` (
  `MODULE_ID` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_modules`
--

LOCK TABLES `mt_modules` WRITE;
/*!40000 ALTER TABLE `mt_modules` DISABLE KEYS */;
INSERT INTO `mt_modules` VALUES ('01','User Management'),('02','Member Management'),('03','Report Management');
/*!40000 ALTER TABLE `mt_modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_section`
--

DROP TABLE IF EXISTS `mt_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_section` (
  `SECTION_ID` varchar(4) NOT NULL,
  `SECTION_NAME` varchar(45) DEFAULT NULL,
  `SECTION_URL` varchar(45) DEFAULT NULL,
  `MODULE_ID` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SECTION_ID`),
  KEY `fk_module_id_idx` (`MODULE_ID`),
  CONSTRAINT `fk_module_id` FOREIGN KEY (`MODULE_ID`) REFERENCES `mt_modules` (`MODULE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_section`
--

LOCK TABLES `mt_section` WRITE;
/*!40000 ALTER TABLE `mt_section` DISABLE KEYS */;
INSERT INTO `mt_section` VALUES ('0101','User Managements','usrMng','01'),('0102','User Profile Managemant','usrprofileMng','01'),('0201','Add Member','addMember','02'),('0202','Edit and View Member','editViewMember','02'),('0301','Total Member Report','totMemReport','03'),('0302','Total Member Summary','totMemSummary','03');
/*!40000 ALTER TABLE `mt_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_section_task`
--

DROP TABLE IF EXISTS `mt_section_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_section_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SECTION_ID` varchar(4) DEFAULT NULL,
  `TASK_ID` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_section_id_idx` (`SECTION_ID`),
  KEY `fk_task_id_idx` (`TASK_ID`),
  CONSTRAINT `fk_section_id` FOREIGN KEY (`SECTION_ID`) REFERENCES `mt_section` (`SECTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_id` FOREIGN KEY (`TASK_ID`) REFERENCES `mt_tasks` (`TASK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_section_task`
--

LOCK TABLES `mt_section_task` WRITE;
/*!40000 ALTER TABLE `mt_section_task` DISABLE KEYS */;
INSERT INTO `mt_section_task` VALUES (1,'0101','01'),(2,'0101','02'),(3,'0101','03'),(4,'0101','04'),(5,'0102','01'),(6,'0102','02'),(7,'0102','03'),(8,'0102','04'),(9,'0201','02'),(10,'0202','01'),(11,'0202','03'),(12,'0202','04'),(13,'0202','05'),(14,'0301','01'),(15,'0301','05'),(16,'0302','01');
/*!40000 ALTER TABLE `mt_section_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mt_tasks`
--

DROP TABLE IF EXISTS `mt_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_tasks` (
  `TASK_ID` varchar(2) NOT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_tasks`
--

LOCK TABLES `mt_tasks` WRITE;
/*!40000 ALTER TABLE `mt_tasks` DISABLE KEYS */;
INSERT INTO `mt_tasks` VALUES ('01','View'),('02','Add'),('03','Update'),('04','Delete'),('05','Download'),('06','PwReset');
/*!40000 ALTER TABLE `mt_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_user`
--

DROP TABLE IF EXISTS `web_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_user` (
  `USERNAME` varchar(30) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `PROFILE_ID` int(11) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `MOBILE` varchar(15) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user`
--

LOCK TABLES `web_user` WRITE;
/*!40000 ALTER TABLE `web_user` DISABLE KEYS */;
INSERT INTO `web_user` VALUES ('admin','kreshan r','5f4dcc3b5aa765d61d8327deb882cf99',1,'kk@gmail.com','0777418581',1,'2019-09-17 00:00:18'),('kre','kre','5f4dcc3b5aa765d61d8327deb882cf99',1,'dd@gmail.com','0777474747',1,'2019-09-18 04:55:32'),('kreshan','kreshan','5f4dcc3b5aa765d61d8327deb882cf99',5,'kreshan@gmail.com','+94777656565',1,'2019-09-18 13:32:40');
/*!40000 ALTER TABLE `web_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_user_profile`
--

DROP TABLE IF EXISTS `web_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_user_profile` (
  `PROFILE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PROFILE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user_profile`
--

LOCK TABLES `web_user_profile` WRITE;
/*!40000 ALTER TABLE `web_user_profile` DISABLE KEYS */;
INSERT INTO `web_user_profile` VALUES (1,'AdminProfile',1,'2019-09-18 07:22:38'),(5,'SysUserProfile',1,'2019-09-18 07:00:00');
/*!40000 ALTER TABLE `web_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_user_profile_privilage`
--

DROP TABLE IF EXISTS `web_user_profile_privilage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_user_profile_privilage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `PROFILE_ID` int(11) DEFAULT NULL,
  `MODULE_ID` varchar(2) DEFAULT NULL,
  `SECTION_ID` varchar(4) DEFAULT NULL,
  `TASK_ID` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user_profile_privilage`
--

LOCK TABLES `web_user_profile_privilage` WRITE;
/*!40000 ALTER TABLE `web_user_profile_privilage` DISABLE KEYS */;
INSERT INTO `web_user_profile_privilage` VALUES (1,1,'01','0101','01'),(2,1,'01','0101','02'),(3,1,'01','0101','03'),(4,1,'01','0101','04'),(5,1,'01','0102','01'),(6,1,'01','0102','02'),(7,1,'01','0102','03'),(8,1,'01','0102','04'),(11,1,'03','0301','01'),(12,1,'03','0301','05'),(13,5,'03','0301','01'),(14,5,'03','0301','05'),(15,1,'02','0202','01'),(16,1,'02','0202','03'),(17,1,'02','0202','04'),(18,1,'02','0202','05'),(19,1,'02','0201','02'),(20,5,'02','0202','01'),(21,5,'02','0202','03'),(22,5,'02','0202','04'),(23,5,'02','0202','05'),(24,5,'02','0201','02'),(25,1,'03','0302','01'),(26,5,'03','0302','01');
/*!40000 ALTER TABLE `web_user_profile_privilage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-19  9:39:10
