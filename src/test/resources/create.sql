-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema resource_management_test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `resource_management_test` ;

-- -----------------------------------------------------
-- Schema resource_management_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resource_management_test` DEFAULT CHARACTER SET utf8 ;
USE `resource_management_test` ;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_description` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `valid` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'basketball player in fener','antic','pero1',1),(2,'jobdesc','userLast','user',0),(3,'job','user1Last','user1',0),(4,'job','user45Last','user45',0),(5,'job','user789','user789',0),(6,'job,job','user7822','user7822',0),(7,'job','user4','user4',0),(8,'job','user6','user6',0),(9,'desc','anitc','perooo',0),(10,'opis','antikj','Pero',0),(11,'job1','useret','user45665',0),(12,'job','ussert4','user34',0),(13,'job','Employee1','Employee1',0),(15,'dev','dimitrovski','atanas',1),(16,'basketball','james','lebron',0),(17,'dev','test','usertest',0),(18,'job','lazarov','user11',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `valid` smallint(6) NOT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jwuielqxxof14w9087a4uhpcc` (`manager_id`),
  CONSTRAINT `FK_jwuielqxxof14w9087a4uhpcc` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'descNewRB12','2015-10-11','project1New11','inDevelopment','2015-10-15',1,15),(2,'projectdesc','2015-10-15','project2','in development','2015-10-30',0,3),(3,'desc','2015-01-01','project4','done','2015-01-30',0,3),(4,'desc','2015-01-01','project4','done','2015-01-30',0,4),(5,'desc','2015-10-01','project4','done','2015-10-30',1,11),(6,'desc','2015-10-21','project3','done','2015-11-10',1,11),(7,'desc','2015-10-15','project5','done','2015-11-18',1,12),(8,'descNewRB','2015-10-11','project1NewRB','inDev','2015-10-15',0,4),(10,'projectdesc',NULL,'projectname',NULL,NULL,0,1),(11,'new project desc','2014-02-03','new project','inDevelopment','2015-03-04',1,12),(12,'proje ct2232','2015-09-29','project2','inDevelopment','2015-10-29',1,3),(14,'description project','2015-10-11','projectManaged','inDevelopment','2015-10-15',1,1),(15,'project','2015-08-31','new project','inDevelopment','2015-09-11',1,3),(16,'Project1Novo','2015-10-11','Project1Novo','inDevelopment','2015-10-22',0,12);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




--
-- Table structure for table `effort`
--

DROP TABLE IF EXISTS `effort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `effort` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8w15xhlc3tknc0ehkssc05efh` (`employee_id`),
  KEY `FK_x983e61t03gxqcc0nvtuv1vs` (`project_id`),
  CONSTRAINT `FK_8w15xhlc3tknc0ehkssc05efh` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_x983e61t03gxqcc0nvtuv1vs` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `effort`
--

LOCK TABLES `effort` WRITE;
/*!40000 ALTER TABLE `effort` DISABLE KEYS */;
INSERT INTO `effort` VALUES (2,1,2),(8,5,2),(9,1,1),(10,2,2),(11,2,5),(12,2,6),(13,4,6),(14,8,6),(15,3,1),(16,12,1),(17,11,1),(18,4,14),(19,3,14),(20,1,14);
/*!40000 ALTER TABLE `effort` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




--
-- Table structure for table `effort_information`
--

DROP TABLE IF EXISTS `effort_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `effort_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_date` datetime DEFAULT NULL,
  `percent` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `effort_id` bigint(20) DEFAULT NULL,
  `to_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8xutcwxg403hbfro66yet13px` (`effort_id`),
  CONSTRAINT `FK_8xutcwxg403hbfro66yet13px` FOREIGN KEY (`effort_id`) REFERENCES `effort` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `effort_information`
--

LOCK TABLES `effort_information` WRITE;
/*!40000 ALTER TABLE `effort_information` DISABLE KEYS */;
INSERT INTO `effort_information` VALUES (1,'2015-01-10 00:00:00',100,'ROLE_DESIGNER',9,'2015-10-10 00:00:00'),(2,'2015-10-15 00:00:00',60,'ROLE_DESIGNER',15,'2015-10-30 00:00:00'),(3,'2015-10-30 00:00:00',40,'ROLE_FRONTEND',9,'2015-11-10 00:00:00'),(6,'2015-09-30 00:00:00',5,'ROLE_FRONTEND',16,'2015-10-04 00:00:00'),(7,'2015-09-30 00:00:00',7,'ROLE_BACKEND',17,'2015-10-04 00:00:00'),(8,'2015-10-04 00:00:00',50,'ROLE_BACKEND',17,'2015-10-06 00:00:00'),(9,'2015-10-04 00:00:00',50,'ROLE_BACKEND',17,'2015-10-06 00:00:00'),(10,'2015-10-05 00:00:00',50,'ROLE_BACKEND',16,'2015-10-13 00:00:00'),(11,'2015-10-12 00:00:00',50,'ROLE_DESIGNER',16,'2015-10-13 00:00:00'),(12,'2015-10-04 00:00:00',50,'ROLE_BACKEND',18,'2015-10-05 00:00:00'),(13,'2015-10-05 00:00:00',37,'ROLE_BACKEND',19,'2015-10-06 00:00:00'),(14,'2015-10-05 00:00:00',50,'ROLE_BACKEND',20,'2015-10-06 00:00:00'),(15,'2015-10-06 00:00:00',5,'ROLE_FRONTEND',9,'2015-10-12 00:00:00'),(16,'2015-10-14 00:00:00',2,'ROLE_DESIGNER',9,'2015-10-26 00:00:00'),(17,'2015-10-04 00:00:00',2,'ROLE_FRONTEND',15,'2015-10-12 00:00:00');
/*!40000 ALTER TABLE `effort_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;




--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_password` varchar(255) DEFAULT NULL,
  `user_role` varchar(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `valid` smallint(6) NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FK_r1usl9qoplqsbrhha5e0niqng` (`employee_id`),
  CONSTRAINT `FK_r1usl9qoplqsbrhha5e0niqng` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'pass','ROLE_USER','user',1,1),(2,'admin','ROLE_ADMIN','admin',0,3),(3,'pass','ROLE_USER','user5',0,4),(5,'pass','ROLE_USER','atanas30',1,15),(6,'cavs','ROLE_USER','king',0,16),(7,'oasss','ROLE_USER','userr',0,17),(8,'pass','ROLE_USER','username',0,11),(9,'pass','ROLE_USER','paul',0,12),(10,'pass','ROLE_USER','kire',1,18);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
