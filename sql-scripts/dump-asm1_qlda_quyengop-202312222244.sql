-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: asm1_qlda_quyengop
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.36-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `donation_status`
--

DROP TABLE IF EXISTS `donation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation_status` (
  `id` int(11) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation_status`
--

LOCK TABLES `donation_status` WRITE;
/*!40000 ALTER TABLE `donation_status` DISABLE KEYS */;
INSERT INTO `donation_status` VALUES (0,'Mới tạo'),(1,'Đang quyên góp'),(2,'Kết thúc quyên góp'),(3,'Đóng quyên góp');
/*!40000 ALTER TABLE `donation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bk_users`
--

DROP TABLE IF EXISTS `bk_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bk_users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bk_users`
--

LOCK TABLES `bk_users` WRITE;
/*!40000 ALTER TABLE `bk_users` DISABLE KEYS */;
INSERT INTO `bk_users` VALUES ('john','{noop}test1234',1),('mary','{noop}test123',1),('susan','{noop}test123',1);
/*!40000 ALTER TABLE `bk_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organization_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `donation_code_unique` (`code`),
  KEY `donation_FK` (`status_id`),
  CONSTRAINT `donation_FK` FOREIGN KEY (`status_id`) REFERENCES `donation_status` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
INSERT INTO `donation` VALUES (1,'D001','2023-10-01','Dự án đầu tiên','2023-10-30',19500,'Vì trẻ em','CONG TY ABC','0911 123 123','2023-11-15',1),(2,'D002','2023-11-01','Nội dung mới abcd','2023-11-15',423003,'Tên đợt quyên góp NEW','Công nhân viên chức','0944665588','2023-11-16',1),(3,'D003','2023-11-05','Dự án thứ ba','2023-12-01',39000,'Xanh sạch đẹp','Khối Doanh nghiệp','090111222','2023-12-10',1),(4,'D004','2023-09-01','Dự án cũ','2023-09-15',25000,'Mùa hè xanh','Đoàn phường','0283243234','2023-09-15',1),(5,'D005','2023-09-02','Dự án cũ 1','2023-09-16',8000,'Dự án 11X','Thông tin xã hội','0283243234','2023-09-17',1),(6,'D006','2023-09-02','Dự án số 6','2023-09-01',65000,'Ngầm hóa','Sở điện lực','0283243234','2023-09-17',1),(7,'D007','2023-09-22','Dự án số 7','2023-10-25',26000,'Dự án 77X','Công ty ABCD','0283243234','2023-09-17',1),(8,'D008','2023-09-22','Dự án số 8','2023-10-03',1000,'Dự án 88Y','Công ty ABCDE','0283243234','2023-09-17',1),(9,'D009','2023-09-22','Dự án số 9','2023-10-03',21000,'Dự án 99Y','Công ty ABCDEF','0283243234','2023-09-17',1),(32,'D010','2023-11-26','Test dự án mới','2023-11-22',8000,'Dự án sửa đổi','Org VNM','012301230123','2023-11-02',1),(55,'DA123','2023-11-27','Dự án mới toe','2023-11-30',0,'Vì đồng bào vùng cao','CÔNG TY CỔ PHẦN DƯỢC PHẨM THÁI MINH MIỀN  NAM','222222222222222','2023-11-01',1),(81,'QD1234567','2023-11-28','Dự án mới','2023-11-23',0,'Phan Văn A','Tên tổ chức','2222222333333','2023-11-01',1),(110,'DA012','2023-12-03','Dự án mới test','2024-01-03',4000,'Vì trẻ em vùng cao','Cao nguyên','0911231231','2023-12-03',1),(113,'DA999','2023-12-04','Dự án mới 999','2024-01-04',9009000,'Đợt 999','Tên tổ chức mới DA999','Tên tổ chức mới DA999','2023-12-08',1),(114,'DA098','2023-12-09','Dự án mới 098','2024-01-09',0,'Đợt 098','Tổ chức 098','098098098098','2023-12-08',1);
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`),
  UNIQUE KEY `email_unique` (`email`),
  KEY `FK_user_has_role` (`role_id`),
  CONSTRAINT `FK_user_has_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1000,'Dump','your.email@google.com','Anonymous','','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','024112234',1,'dump','2021-10-03',2,0),(1001,'11/1 địa chỉ','your.email1001@google.com','Nguyễn Văn Một 11','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','1111223',1,'u001','2023-12-06',1,0),(1002,'22/2202 đường Lê Văn Hai','your.email1002@google.com','Lê Văn Hai','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','22222222222222',0,'u002','2023-12-06',2,0),(1003,'TPHCM','hoangpv@gmail.com','Phan Văn Hoàng','note 111','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','0911777888',1,'u006','2023-10-01',2,0),(1004,'33/3 Ba Ba','your.email1004@google.com','Trần Văn Ba','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','3333333333',0,'u003','2023-12-06',2,0),(1005,'Hà Nội','halt@fpt.vn','Lê Trọng Hà','note 333','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','02412312312',0,'u007','2023-10-02',2,0),(1006,'Địa chỉ 444','your.email04@google.com','Họ Văn Bốn','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','44444444444',0,'u004','2023-12-06',2,0),(1007,'Huế','huongha@yahoo.com','Hà Thị Hường','note 444','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','024112234',0,'u008','2021-10-03',2,0),(1009,'Địa chỉ 9999999','your.email1009@google.com','Họ Văn Chín','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','999999999',1,'u009','2023-12-05',2,0),(1010,'Địa chỉ nahf log in','your.login1010@google.com','Tên Log In','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','091111222',1,'ten_login','2023-12-05',2,0),(1011,'log địa chỉ','your.login1011@google.com','Nguyễn Văn Lốc','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','0911321321',1,'tenlogin','2023-12-05',2,1),(1012,'Địa chỉ người dùng','your.email1012@google.com','Hà Thị Năm','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','Số điện thoại',1,'u005','2023-12-06',2,1),(1013,'Nhà bên hông','your.email1013@google.com','Nguyễn Văn Nên','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','0932131231',1,'u010','2023-12-06',2,1),(1014,'12/1 Mười Hai','your.email.12@google.com','Trịnh Thị Mười Hai','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','12121212',1,'user012','2023-12-06',2,0),(1015,'20/2 Hai Hai 222','u020.email@google.com','Họ Văn 20','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','2020202020',1,'u020','2023-12-08',2,0),(1016,'11//011a','your.email011@google.com','Họ Văn Tên u011a','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','0110110110119',1,'u011','2023-12-09',2,0),(1017,'012/12','your.email012@google.com','Họ Và Tên u012','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','012012012012',1,'u012','2023-12-09',2,0),(1018,'ÃÂÃÂÃÂÃÂÃÂÃÂ¡ÃÂÃÂ»ÃÂÃÂa chÃÂÃÂ¡ÃÂÃÂ»ÃÂÃÂ 013A','your.email013@google.com','NguyÃÂÃÂ¡ÃÂÃÂ»ÃÂÃÂn VÃÂÃÂÃÂÃÂn 013A','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','013013013013',1,'u013','2023-12-09',2,0),(1019,'18/1 địa chỉ','18your.email@google.com','Phan Văn 18','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$AAbp6KfZajBO03Eor2EEd.99x0jXwyvSO1lcyS2EtYNa.KOxpg4SG','1818181818118',1,'u018','2023-12-10',2,0),(1022,'22/2','your.email1022@google.com','Nguyễn Văn Hai','Thông tin ghi chú của người dùng','{bcrypt}$2a$10$xm900aKmyv6Xq76hOyd7UOvbraPPLsHB3ortiqxugcITNekaZOsIK','222222222',1,'u0031','2023-12-18',2,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_donation_status`
--

DROP TABLE IF EXISTS `user_donation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_donation_status` (
  `id` int(11) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_donation_status`
--

LOCK TABLES `user_donation_status` WRITE;
/*!40000 ALTER TABLE `user_donation_status` DISABLE KEYS */;
INSERT INTO `user_donation_status` VALUES (0,'Chờ xác nhận'),(1,'Đã quyên góp');
/*!40000 ALTER TABLE `user_donation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_donation`
--

DROP TABLE IF EXISTS `user_donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_donation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `donation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_donation_userdo` (`donation_id`),
  KEY `FK_user_donation` (`user_id`),
  CONSTRAINT `FK_user_donation` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_donation_userdo` FOREIGN KEY (`donation_id`) REFERENCES `donation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_donation`
--

LOCK TABLES `user_donation` WRITE;
/*!40000 ALTER TABLE `user_donation` DISABLE KEYS */;
INSERT INTO `user_donation` VALUES (1,'2023-10-01',500,NULL,1,'donation1-1',1,1001),(2,'2023-11-12',1000,NULL,1,'donation1-2',1,1002),(3,'2023-05-01',200001,NULL,1,'donation2-2',2,1001),(4,'2023-08-13',200002,'',1,'donation2-2a',2,1002),(5,'2023-05-21',200003,'',0,'donation2-2b',2,1002),(6,'2023-12-17',11000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',2,1000),(7,'2023-12-17',12000,NULL,1,'Chúc chương trình thành công',2,1000),(8,'2023-12-17',7000,NULL,1,'Ngon nhé',3,1018),(9,'2023-12-17',11000,NULL,1,'Thêm nào',3,1018),(10,'2023-12-17',10000,NULL,0,'Chúc thành công',3,1000),(13,'2023-12-17',1000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1000),(15,'2023-12-17',3000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1017),(22,'2023-12-17',17000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1003),(23,'2023-12-17',10000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1003),(24,'2023-12-17',1000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1003),(26,'2023-12-17',3000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1004),(27,'2023-12-17',8000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',9,1004),(28,'2023-12-17',4000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',9,1004),(30,'2023-12-17',2000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1002),(31,'2023-12-17',11000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1002),(34,'2023-12-17',9000,NULL,1,'Lời nhắn ABC',3,1000),(36,'2023-12-17',7000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',1,1000),(37,'2023-12-17',8000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',1,1000),(38,'2023-12-17',10000,NULL,0,'Chúc mừng',1,1018),(39,'2023-12-17',9000,NULL,0,'Success',1,1018),(40,'2023-12-17',9000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',1,1018),(41,'2023-12-17',9000,NULL,1,'abc',1,1002),(42,'2023-12-17',9000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',9,1002),(44,'2023-12-17',1000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1000),(45,'2023-12-18',17000,NULL,0,'Hello',3,1000),(46,'2023-12-18',1000,NULL,0,'Continue',3,1000),(47,'2023-12-18',12000,NULL,1,'Xanh',4,1001),(48,'2023-12-18',13000,NULL,1,'Chúc mau đạt được chỉ tiêu',4,1000),(49,'2023-12-18',8000,NULL,1,'For you',5,1000),(50,'2023-12-18',65000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',6,1001),(51,'2023-12-18',26000,NULL,1,'Nothing',7,1001),(52,'2023-12-18',17000,NULL,0,'More',7,1001),(53,'2023-12-18',1000,NULL,1,'Hãy gửi lời nhắn của bạn đến BTC chương trình',8,1001),(54,'2023-12-19',19000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',3,1000),(55,'2023-12-19',17000,NULL,0,'Call me at 0900111222',8,1000),(56,'2023-12-19',17000,NULL,0,'Call me at 0900111222',8,1000),(57,'2023-12-19',0,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',4,1002),(58,'2023-12-20',1000,NULL,0,'Hãy gửi lời nhắn của bạn đến BTC chương trình',2,1001),(59,'2023-12-22',9000,NULL,0,'Thử nhé',2,1001);
/*!40000 ALTER TABLE `user_donation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-22 22:44:36
