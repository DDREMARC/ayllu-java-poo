-- ===================================================
-- Tabla única: personas
-- Base de datos: ayllu_pollos
-- La columna es_empleado (0/1) distingue cliente de empleado.
-- Patrón de CREATE TABLE / INSERT visto en el hub S10
-- (ejemplo de la tabla "productos" del profe).
-- ===================================================

USE ayllu_pollos;

-- Primero borrar las tablas viejas si existen
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS empleados;

-- Nueva tabla unificada
CREATE TABLE personas (
    codigo_persona   VARCHAR(20)  NOT NULL,
    nombre           VARCHAR(100) NOT NULL,
    numero_telefono  VARCHAR(20)  NOT NULL,
    es_empleado      INT          NOT NULL,
    -- Solo aplican si es cliente (es_empleado = 0)
    zona_residencia  VARCHAR(100),
    deuda_total      FLOAT,
    -- Solo aplican si es empleado (es_empleado = 1)
    cargo            VARCHAR(50),
    pago_por_dia     FLOAT,
    dias_trabajados  INT,
    bono             FLOAT,
    PRIMARY KEY (codigo_persona)
);

-- Datos de prueba: clientes
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total)
VALUES ('C001', 'María López',   '987654321', 0, 'San Juan de Lurigancho',   0);
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total)
VALUES ('C002', 'Jorge Quispe',  '912345678', 0, 'Los Olivos',             50.5);
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total)
VALUES ('C003', 'Ana Flores',    '923456789', 0, 'Comas',                 120.0);

-- Datos de prueba: empleados
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono)
VALUES ('E001', 'Carlos Mamani', '999111222', 1, 'Vendedor',   60.0, 22, 50.0);
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono)
VALUES ('E002', 'Rosa Ticona',   '999333444', 1, 'Cajera',     55.0, 20, 30.0);
INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono)
VALUES ('E003', 'Luis Ccopa',    '999555666', 1, 'Repartidor', 50.0, 25,  0.0);
