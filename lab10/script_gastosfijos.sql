-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-10-2026 a las 05:25:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ayllu_pollos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_gasto`
--

CREATE TABLE `tipo_gasto` (
                              `id_tipo` int(11) NOT NULL,
                              `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_gasto`
--

INSERT INTO `tipo_gasto` (`id_tipo`, `nombre`) VALUES
                                                   (1, 'Implementos'),
                                                   (2, 'Combustible y peajes'),
                                                   (3, 'Agua y gas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tipo_gasto`
--
ALTER TABLE `tipo_gasto`
    ADD PRIMARY KEY (`id_tipo`);

--
-- Estructura de tabla para la tabla `gasto_fijo`
--

CREATE TABLE `gasto_fijo` (
    `id_gasto` int(11) NOT NULL AUTO_INCREMENT,
    `id_tipo` int(11) NOT NULL,
    `fecha` date NOT NULL DEFAULT CURRENT_DATE,
    `monto` decimal(10,2) NOT NULL,
    PRIMARY KEY (`id_gasto`),
    KEY `idx_gasto_fijo_tipo` (`id_tipo`),
    CONSTRAINT `fk_gasto_fijo_tipo`
        FOREIGN KEY (`id_tipo`) REFERENCES `tipo_gasto` (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
