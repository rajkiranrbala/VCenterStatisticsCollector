CREATE DATABASE  IF NOT EXISTS `cmpe283` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cmpe283`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 54.203.253.104    Database: cmpe283
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

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
-- Table structure for table `datastoreinfo`
--

DROP TABLE IF EXISTS `datastoreinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datastoreinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DataStoreName` varchar(20) NOT NULL DEFAULT '',
  `disksize` double NOT NULL,
  `freespace` double NOT NULL,
  `diskpercentage` double NOT NULL,
  `RollupStamp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hostinfo`
--

DROP TABLE IF EXISTS `hostinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hostname` varchar(255) NOT NULL,
  `cpuUsage` double NOT NULL,
  `cpuMax` double NOT NULL,
  `cpuPercentage` double NOT NULL,
  `memUsage` double NOT NULL,
  `memMax` double NOT NULL,
  `memPercentage` double NOT NULL,
  `uptime` double NOT NULL,
  `tx` double NOT NULL,
  `rx` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hosttaskinfo`
--

DROP TABLE IF EXISTS `hosttaskinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hosttaskinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `targetVM` varchar(20) NOT NULL DEFAULT '',
  `Action` varchar(20) NOT NULL DEFAULT '',
  `timeTaken` double NOT NULL,
  `RollupStamp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vmcpuinfo`
--

DROP TABLE IF EXISTS `vmcpuinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vmcpuinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HostName` varchar(255) NOT NULL,
  `CPUUsage` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vmdiskinfo`
--

DROP TABLE IF EXISTS `vmdiskinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vmdiskinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HostName` varchar(255) NOT NULL,
  `DiskID` varchar(255) NOT NULL,
  `WriteIO` double NOT NULL,
  `ReadIO` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vmmeminfo`
--

DROP TABLE IF EXISTS `vmmeminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vmmeminfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HostName` varchar(255) NOT NULL,
  `TotalMem` double NOT NULL,
  `UsedMem` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vmnetinfo`
--

DROP TABLE IF EXISTS `vmnetinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vmnetinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HostName` varchar(255) NOT NULL,
  `Interface` varchar(255) NOT NULL,
  `Tx` double NOT NULL,
  `Rx` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vmprocessinfo`
--

DROP TABLE IF EXISTS `vmprocessinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vmprocessinfo` (
  `DateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HostName` varchar(255) NOT NULL,
  `Process` double NOT NULL,
  `Threads` double NOT NULL,
  `RollupStamp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'cmpe283'
--
/*!50003 DROP PROCEDURE IF EXISTS `rollupdata` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `rollupdata`(IN currentTimeStamp timestamp,  IN startTime timestamp, IN endTime timestamp, IN rollupStamp int, IN targetRollUpStamp int)
BEGIN

insert into `vmcpuinfo`
SELECT  currentTimeStamp, `HostName`, avg(`CPUUsage`), targetRollUpStamp FROM `vmcpuinfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`;

INSERT INTO `vmdiskinfo`
SELECT  currentTimeStamp, `HostName`, `DiskID`, avg(`WriteIO`), avg(`ReadIO`), targetRollUpStamp FROM `vmdiskinfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`, `DiskID`;

INSERT INTO `vmnetinfo`
SELECT  currentTimeStamp, `HostName`, `Interface`, avg(`Tx`), avg(`Rx`) , targetRollUpStamp FROM `vmnetinfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`, `Interface`;

INSERT INTO `vmmeminfo`
SELECT  currentTimeStamp, `HostName`, max(`TotalMem`), avg(`UsedMem`), targetRollUpStamp FROM `vmmeminfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`;

INSERT INTO `vmprocessinfo`
SELECT  currentTimeStamp, `HostName`, avg(`Process`), avg(`Threads`), targetRollUpStamp FROM `vmprocessinfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`;

INSERT INTO `hostinfo`
SELECT  currentTimeStamp, `HostName`, avg(`cpuUsage`), max(`cpuMax`), avg(`cpuPercentage`), avg(`memUsage`), max(`memMax`), avg(`memPercentage`), max(`uptime`), avg(`tx`), avg(`rx`), targetRollUpStamp FROM `hostinfo`
WHERE `RollupStamp` = rollupStamp AND `DateTime` >= startTime AND `DateTime` <= endTime
group by `HostName`;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-02  4:50:38
