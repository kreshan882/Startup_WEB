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
  PRIMARY KEY (`SECTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_section`
--

LOCK TABLES `mt_section` WRITE;
/*!40000 ALTER TABLE `mt_section` DISABLE KEYS */;
INSERT INTO `mt_section` VALUES ('0101','User Managements','usrMng','01'),('0102','User Profile Managemant','usrprofileMng','01');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mt_section_task`
--

LOCK TABLES `mt_section_task` WRITE;
/*!40000 ALTER TABLE `mt_section_task` DISABLE KEYS */;
INSERT INTO `mt_section_task` VALUES (1,'0101','01'),(2,'0101','02'),(3,'0101','03'),(4,'0101','04'),(5,'0102','01'),(6,'0102','02'),(7,'0102','03'),(8,'0102','04');
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
INSERT INTO `web_user` VALUES ('admin','kreshan r','5f4dcc3b5aa765d61d8327deb882cf99',1,'kk@gmail.com','0777418581',1,'2019-09-17 00:00:18'),('kreshan','kre','5f4dcc3b5aa765d61d8327deb882cf99',1,'dd@gmail.com','0777474747',1,'2019-09-18 04:55:32');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user_profile`
--

LOCK TABLES `web_user_profile` WRITE;
/*!40000 ALTER TABLE `web_user_profile` DISABLE KEYS */;
INSERT INTO `web_user_profile` VALUES (1,'Admin',1,'2019-09-18 07:22:38'),(2,'NormalUser',2,'2019-09-18 07:22:38'),(4,'kreee',1,'2019-09-18 07:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_user_profile_privilage`
--

LOCK TABLES `web_user_profile_privilage` WRITE;
/*!40000 ALTER TABLE `web_user_profile_privilage` DISABLE KEYS */;
INSERT INTO `web_user_profile_privilage` VALUES (1,1,'01','0101','01'),(2,1,'01','0101','02'),(3,1,'01','0101','03'),(4,1,'01','0101','04'),(5,1,'01','0102','01'),(6,1,'01','0102','02'),(7,1,'01','0102','03'),(8,1,'01','0102','04'),(9,4,'01','0101','01'),(10,4,'01','0101','02');
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

-- Dump completed on 2019-09-18  5:40:38
