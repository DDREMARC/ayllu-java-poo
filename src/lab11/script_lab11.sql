-- Script SQL del Laboratorio 11 - base de datos ayllu_pollos
-- Tablas: ventas, personas, gasto_fijo y tipo_gasto (cada una con su clave primaria).

CREATE DATABASE IF NOT EXISTS `ayllu_pollos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ayllu_pollos`;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `gasto_fijo`;
CREATE TABLE `gasto_fijo` (
  `id_gasto` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipo` int(11) NOT NULL,
  `fecha` date NOT NULL DEFAULT curdate(),
  `monto` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `id_tipo` (`id_tipo`),
  CONSTRAINT `gasto_fijo_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_gasto` (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `personas`;
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

INSERT INTO `personas` VALUES ('C001','María López','987654321',0,'San Juan de Lurigancho',0,NULL,NULL,NULL,NULL),('C002','Jorge Quispe','912345678',0,'Los Olivos',50.5,NULL,NULL,NULL,NULL),('C003','Ana Flores','923456789',0,'Comas',120,NULL,NULL,NULL,NULL),('E001','Carlos Mamani','999111222',1,NULL,NULL,'Vendedor',60,22,50),('E002','Rosa Ticona','999333444',1,NULL,NULL,'Cajera',55,20,30),('E003','Luis Ccopa','999555666',1,NULL,NULL,'Repartidor',50,25,0);
DROP TABLE IF EXISTS `tipo_gasto`;
CREATE TABLE `tipo_gasto` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `tipo_gasto` VALUES (1,'Implementos'),(2,'Combustible y peajes'),(3,'Agua y gas');
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `Codigo_venta` int(11) NOT NULL AUTO_INCREMENT,
  `Precio` decimal(10,2) NOT NULL,
  `Peso` decimal(10,2) NOT NULL,
  `Pago_pollo` decimal(10,2) NOT NULL,
  `Fecha` date NOT NULL,
  `Codigo_Cliente` varchar(11) NOT NULL,
  PRIMARY KEY (`Codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `ventas` VALUES (1,100.00,100.00,25.00,'2026-03-24','XD234'),(3,12.00,12.00,40.00,'2024-12-12','SWE13');

SET FOREIGN_KEY_CHECKS = 1;
