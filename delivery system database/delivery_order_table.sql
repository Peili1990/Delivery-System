CREATE DATABASE  IF NOT EXISTS `delivery` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `delivery`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: delivery
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `order_type` int(11) NOT NULL,
  `submit_date` date DEFAULT NULL,
  `ship_date` date DEFAULT NULL,
  `estimate_date` date DEFAULT NULL,
  `deliver_date` date DEFAULT NULL,
  `item_name` varchar(32) DEFAULT NULL,
  `item_weight` varchar(20) DEFAULT NULL,
  `item_volume` varchar(20) DEFAULT NULL,
  `item_quantity` int(11) DEFAULT NULL,
  `item_note` varchar(128) DEFAULT NULL,
  `receiver_first_name` varchar(16) DEFAULT NULL,
  `receiver_last_name` varchar(16) DEFAULT NULL,
  `receiver_street` varchar(16) DEFAULT NULL,
  `receiver_city` varchar(16) DEFAULT NULL,
  `receiver_state` varchar(2) DEFAULT NULL,
  `receiver_zip` varchar(6) DEFAULT NULL,
  `receiver_email` varchar(45) DEFAULT NULL,
  `receiver_phone` varchar(16) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `pay_method` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `order_type_idx` (`order_type`),
  KEY `status_idx` (`status`),
  CONSTRAINT `order_type` FOREIGN KEY (`order_type`) REFERENCES `order_type_table` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status` FOREIGN KEY (`status`) REFERENCES `order_status_table` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (1,1000,0,0,'2014-03-07',NULL,'2014-03-14',NULL,'bottle','12 pt.','12 oz.',1,'easy break','Alex','Shepherd','Lake Ln','Shepherd Creak','PA','123123','shepherd@silenthill.com','1231231231',4.99,'0'),(2,1000,1,0,'2014-03-10',NULL,'2014-03-17',NULL,'lm','123','123',1,'111','meng','lin','11123','gwu','AZ','22222','123@123.com','123456',4.99,'0'),(3,1000,0,0,'2014-03-10',NULL,'2014-03-17',NULL,'lm','123','123',1,'111','meng','lin','11123','gwu','AZ','22222','123@123.com','123456',4.99,'0'),(4,1001,0,0,'2014-03-17',NULL,'2014-03-24',NULL,'pen','20','',2,'','jack','22','ss','ss','DC','22222','33@ww','3456678943',4.99,'0'),(6,1001,3,0,'2014-03-17',NULL,'2014-03-24',NULL,'123','12','12',1,'123','df','fd','123fd','32f','DC','123123','123@123','1231231231',4.99,'0'),(7,1001,2,0,'2014-03-17',NULL,'2014-03-24',NULL,'123','231','12',2,'123','sdf','fd','123123','21','DC','123123','123@123','1231231231',4.99,'0'),(8,1001,1,0,'2014-03-17',NULL,'2014-03-24',NULL,'','','',1,'','','','','','DC','','','',4.99,'0');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-25 21:38:01
