-- ===================================================
-- Script completo del Laboratorio 11 - base de datos ayllu_pollos
-- 3 tablas: ventas, personas (clientes y empleados), gasto_fijo (+ tipo_gasto)
-- Mismas tablas que el Laboratorio 10, reutilizadas para este laboratorio.
-- ===================================================

CREATE DATABASE IF NOT EXISTS ayllu_pollos;
USE ayllu_pollos;

-- ---------------------------------------------------
-- Tabla: ventas
-- ---------------------------------------------------
DROP TABLE IF EXISTS ventas;
CREATE TABLE ventas (
    Codigo_venta   INT(11) NOT NULL AUTO_INCREMENT,
    Precio         DECIMAL(10,2) NOT NULL,
    Peso           DECIMAL(10,2) NOT NULL,
    Pago_pollo     DECIMAL(10,2) NOT NULL,
    Fecha          DATE NOT NULL,
    Codigo_Cliente VARCHAR(11) NOT NULL,
    PRIMARY KEY (Codigo_venta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) VALUES
    (100.00, 100.00, 25.00, '2026-03-24', 'C001'),
    (12.00, 12.00, 12.00, '2026-10-20', 'C002');

-- ---------------------------------------------------
-- Tabla: personas (clientes y empleados en una sola tabla)
-- es_empleado = 1 -> empleado (cargo, pago_por_dia, dias_trabajados, bono)
-- es_empleado = 0 -> cliente  (zona_residencia, deuda_total)
-- ---------------------------------------------------
DROP TABLE IF EXISTS personas;
CREATE TABLE personas (
    codigo_persona   VARCHAR(20)  NOT NULL,
    nombre           VARCHAR(100) NOT NULL,
    numero_telefono  VARCHAR(20)  NOT NULL,
    es_empleado      INT          NOT NULL,
    zona_residencia  VARCHAR(100),
    deuda_total      DECIMAL(10,2),
    cargo            VARCHAR(50),
    pago_por_dia     DECIMAL(10,2),
    dias_trabajados  INT,
    bono             DECIMAL(10,2),
    PRIMARY KEY (codigo_persona)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total) VALUES
    ('C001', 'Maria Lopez',  '987654321', 0, 'San Juan de Lurigancho', 0),
    ('C002', 'Jorge Quispe', '912345678', 0, 'Los Olivos', 50.5);

INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono) VALUES
    ('E001', 'Carlos Mamani', '999111222', 1, 'Vendedor', 60.0, 22, 50.0),
    ('E002', 'Rosa Ticona',   '999333444', 1, 'Cajera',   55.0, 20, 30.0);

-- ---------------------------------------------------
-- Tabla: tipo_gasto (catalogo usado por gasto_fijo)
-- ---------------------------------------------------
DROP TABLE IF EXISTS gasto_fijo;
DROP TABLE IF EXISTS tipo_gasto;
CREATE TABLE tipo_gasto (
    id_tipo INT(11) NOT NULL,
    nombre  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tipo_gasto (id_tipo, nombre) VALUES
    (1, 'Implementos'),
    (2, 'Combustible y peajes'),
    (3, 'Agua y gas');

-- ---------------------------------------------------
-- Tabla: gasto_fijo
-- ---------------------------------------------------
CREATE TABLE gasto_fijo (
    id_gasto INT(11) NOT NULL AUTO_INCREMENT,
    id_tipo  INT(11) NOT NULL,
    fecha    DATE NOT NULL DEFAULT (CURRENT_DATE),
    monto    DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_gasto),
    KEY idx_gasto_fijo_tipo (id_tipo),
    CONSTRAINT fk_gasto_fijo_tipo FOREIGN KEY (id_tipo) REFERENCES tipo_gasto (id_tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO gasto_fijo (id_tipo, monto) VALUES
    (1, 150.00),
    (2, 80.00),
    (3, 45.50);
