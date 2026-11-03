-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ayllu_pollos
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ayllu_pollos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ayllu_pollos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ayllu_pollos`;

--
-- Table structure for table `gasto_fijo`
--

DROP TABLE IF EXISTS `gasto_fijo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gasto_fijo` (
  `id_gasto` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipo` int(11) NOT NULL,
  `fecha` date NOT NULL DEFAULT curdate(),
  `monto` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `id_tipo` (`id_tipo`),
  CONSTRAINT `gasto_fijo_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_gasto` (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto_fijo`
--

LOCK TABLES `gasto_fijo` WRITE;
/*!40000 ALTER TABLE `gasto_fijo` DISABLE KEYS */;
INSERT INTO `gasto_fijo` VALUES (1,1,'2026-10-05',150.00),(2,2,'2026-10-12',85.50),(3,3,'2026-10-20',60.00);
/*!40000 ALTER TABLE `gasto_fijo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `codigo_persona` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `numero_telefono` varchar(20) NOT NULL,
  `es_empleado` int(11) NOT NULL,
  `zona_residencia` varchar(100) DEFAULT NULL,
  `deuda_total` float DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `pago_por_dia` float DEFAULT NULL,
  `dias_trabajados` int(11) DEFAULT NULL,
  `bono` float DEFAULT NULL,
  PRIMARY KEY (`codigo_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES ('C001','María López','987654321',0,'San Juan de Lurigancho',0,NULL,NULL,NULL,NULL),('C002','Jorge Quispe','912345678',0,'Los Olivos',50.5,NULL,NULL,NULL,NULL),('C003','Ana Flores','923456789',0,'Comas',120,NULL,NULL,NULL,NULL),('E001','Carlos Mamani','999111222',1,NULL,NULL,'Vendedor',60,22,50),('E002','Rosa Ticona','999333444',1,NULL,NULL,'Cajera',55,20,30),('E003','Luis Ccopa','999555666',1,NULL,NULL,'Repartidor',50,25,0);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_gasto`
--

DROP TABLE IF EXISTS `tipo_gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_gasto` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_gasto`
--

LOCK TABLES `tipo_gasto` WRITE;
/*!40000 ALTER TABLE `tipo_gasto` DISABLE KEYS */;
INSERT INTO `tipo_gasto` VALUES (1,'Implementos'),(2,'Combustible y peajes'),(3,'Agua y gas');
/*!40000 ALTER TABLE `tipo_gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `Codigo_venta` int(11) NOT NULL AUTO_INCREMENT,
  `Precio` decimal(10,2) NOT NULL,
  `Peso` decimal(10,2) NOT NULL,
  `Pago_pollo` decimal(10,2) NOT NULL,
  `Fecha` date NOT NULL,
  `Codigo_Cliente` varchar(11) NOT NULL,
  PRIMARY KEY (`Codigo_venta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,30.00,20.00,70.00,'2026-10-30','C001'),(9,20.00,20.00,20.00,'2026-11-01','C002');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
