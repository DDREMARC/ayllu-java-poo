
CREATE DATABASE IF NOT EXISTS ayllu_pollos
    DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE ayllu_pollos;

DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS gasto_fijo;
DROP TABLE IF EXISTS tipo_gasto;
DROP TABLE IF EXISTS personas;

CREATE TABLE personas (
    codigo_persona   VARCHAR(20)   NOT NULL,
    nombre           VARCHAR(100)  NOT NULL,
    numero_telefono  VARCHAR(20)   NOT NULL,
    es_empleado      TINYINT(1)    NOT NULL,
    zona_residencia  VARCHAR(100)  DEFAULT NULL,
    deuda_total      DECIMAL(10,2) DEFAULT NULL,
    cargo            VARCHAR(50)   DEFAULT NULL,
    pago_por_dia     DECIMAL(10,2) DEFAULT NULL,
    dias_trabajados  INT           DEFAULT NULL,
    bono             DECIMAL(10,2) DEFAULT NULL,
    PRIMARY KEY (codigo_persona)
) ENGINE=InnoDB;

CREATE TABLE tipo_gasto (
    id_tipo  INT         NOT NULL,
    nombre   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo)
) ENGINE=InnoDB;

CREATE TABLE gasto_fijo (
    id_gasto  INT           NOT NULL AUTO_INCREMENT,
    id_tipo   INT           NOT NULL,
    fecha     DATE          NOT NULL DEFAULT (CURRENT_DATE),
    monto     DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_gasto),
    CONSTRAINT fk_gasto_tipo FOREIGN KEY (id_tipo) REFERENCES tipo_gasto (id_tipo)
) ENGINE=InnoDB;

CREATE TABLE ventas (
    Codigo_venta    INT           NOT NULL AUTO_INCREMENT,
    Precio          DECIMAL(10,2) NOT NULL,
    Peso            DECIMAL(10,2) NOT NULL,
    Pago_pollo      DECIMAL(10,2) NOT NULL,
    Fecha           DATE          NOT NULL,
    Codigo_Cliente  VARCHAR(20)   NOT NULL,
    PRIMARY KEY (Codigo_venta),
    CONSTRAINT fk_venta_cliente FOREIGN KEY (Codigo_Cliente) REFERENCES personas (codigo_persona)
) ENGINE=InnoDB;

INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total) VALUES
('C001', 'Maria Lopez',  '987654321', 0, 'San Juan de Lurigancho', 0.00),
('C002', 'Jorge Quispe', '912345678', 0, 'Los Olivos',             50.50),
('C003', 'Ana Flores',   '923456789', 0, 'Comas',                  120.00);

INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono) VALUES
('E001', 'Carlos Mamani', '999111222', 1, 'Vendedor',   60.00, 22, 50.00),
('E002', 'Rosa Ticona',   '999333444', 1, 'Cajera',     55.00, 20, 30.00),
('E003', 'Luis Ccopa',    '999555666', 1, 'Repartidor', 50.00, 25,  0.00);

INSERT INTO tipo_gasto (id_tipo, nombre) VALUES
(1, 'Implementos'),
(2, 'Combustible y peajes'),
(3, 'Agua y gas');

INSERT INTO gasto_fijo (id_tipo, fecha, monto) VALUES
(1, '2026-10-05', 120.50),
(2, '2026-10-12',  85.00),
(3, '2026-10-20',  60.00),
(1, '2026-10-28',  45.90),
(2, '2026-11-01', 100.00);

INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) VALUES
(9.50, 20.00, 190.00, '2026-10-30', 'C001'),
(9.50, 10.00,  44.50, '2026-11-01', 'C002'),
(9.80, 25.00, 125.00, '2026-11-03', 'C003');
