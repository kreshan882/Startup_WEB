-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: testk
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
INSERT INTO `dma_cast` VALUES ('01','Daver'),('02','Maraver'),('03','Agamudiyar');
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
  `FAT_CAST` varchar(2) DEFAULT NULL,
  `MOT_NAME` varchar(45) DEFAULT NULL,
  `MOT_ADD` varchar(200) DEFAULT NULL,
  `MOT_CAST` varchar(2) DEFAULT NULL,
  `GRAN_FAT_NAME` varchar(45) DEFAULT NULL,
  `GRAN_FAT_ADD` varchar(200) DEFAULT NULL,
  `GRAN_FAT_CAST` varchar(2) DEFAULT NULL,
  `GRAN_MOT_NAME` varchar(45) DEFAULT NULL,
  `GRAN_MOT_ADD` varchar(200) DEFAULT NULL,
  `GRAN_MOT_CAST` varchar(45) DEFAULT NULL,
  `IS_MARRIED` varchar(1) DEFAULT NULL,
  `WIFR_NAME` varchar(45) DEFAULT NULL,
  `NUM_OF_SUN` int(11) DEFAULT NULL,
  `NUM_OF_DOT` int(11) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dma_member`
--

LOCK TABLES `dma_member` WRITE;
/*!40000 ALTER TABLE `dma_member` DISABLE KEYS */;
INSERT INTO `dma_member` VALUES (1,'dhar',NULL,'2012-12-12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2010-10-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-09-18 17:42:34'),(2,'dhar2',NULL,'2012-12-12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','2010-10-10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2019-09-18 17:42:34'),(3,'k','1','2012-12-12',NULL,NULL,'qq','qq','qq','qq','qq','01','ff','0','2012-12-12',1,1,'gg','mat','07773','rrr','123,vol','01','th','12','01','fff','fff','01','mmm','123d','s','1','kkk',1,0,1,'2019-09-21 19:46:21'),(4,'kreshan','880201573V','1988-01-20','0777418581','0444','kreshan882@gmail.com','master','matale','colombo','matale','01','daver','0','2020-09-22',1,0,'sw engineer','inter','0114141414','raja','matale','01','than','matale','01','nava','matale','01','magas','','01','1','krish',1,2,1,'2019-09-22 07:22:11');
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
INSERT INTO `mt_section` VALUES ('0101','User Managements','usrMng','01'),('0102','User Profile Managemant','usrprofileMng','01'),('0201','Add Member','addMember','02'),('0202','Edit and View Member','editViewMember','02'),('0301','Total Member Report','totMemReport','03');
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
  CONSTRAINT `fk_task_id` FOREIGN KEY (`TASK_ID`) REFERENCES `mt_tasks` (`TASK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_id` FOREIGN KEY (`SECTION_ID`) REFERENCES `mt_section` (`SECTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_section_task`
--

LOCK TABLES `mt_section_task` WRITE;
/*!40000 ALTER TABLE `mt_section_task` DISABLE KEYS */;
INSERT INTO `mt_section_task` VALUES (1,'0101','01'),(2,'0101','02'),(3,'0101','03'),(4,'0101','04'),(5,'0102','01'),(6,'0102','02'),(7,'0102','03'),(8,'0102','04'),(9,'0201','02'),(10,'0202','01'),(11,'0202','03'),(12,'0202','04'),(13,'0202','05'),(14,'0301','01'),(15,'0301','05');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user_profile_privilage`
--

LOCK TABLES `web_user_profile_privilage` WRITE;
/*!40000 ALTER TABLE `web_user_profile_privilage` DISABLE KEYS */;
INSERT INTO `web_user_profile_privilage` VALUES (1,1,'01','0101','01'),(2,1,'01','0101','02'),(3,1,'01','0101','03'),(4,1,'01','0101','04'),(5,1,'01','0102','01'),(6,1,'01','0102','02'),(7,1,'01','0102','03'),(8,1,'01','0102','04'),(11,1,'03','0301','01'),(12,1,'03','0301','05'),(13,5,'03','0301','01'),(14,5,'03','0301','05'),(15,1,'02','0202','01'),(16,1,'02','0202','03'),(17,1,'02','0202','04'),(18,1,'02','0202','05'),(19,1,'02','0201','02');
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

-- Dump completed on 2019-09-22  0:25:25
