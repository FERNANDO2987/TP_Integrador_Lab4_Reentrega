

INSERT INTO `bdbanco`.`provincia` (`nombre`) VALUES
('Buenos Aires'),
('CABA'),
('Santa Fe'),
('Córdoba'),
('Mendoza'),
('Catamarca'),
('Chaco'),
('Chubut'),
('Corrientes'),
('Entre Ríos'),
('Formosa'),
('Jujuy'),
('La Pampa'),
('La Rioja'),
('Misiones'),
('Neuquén'),
('Río Negro'),
('Salta'),
('San Juan'),
('San Luis'),
('Santa Cruz'),
('Santiago del Estero'),
('Tierra del Fuego'),
('Tucumán');

INSERT INTO `bdbanco`.`localidades` (`nombre`) VALUES
('La Plata'), -- Buenos Aires
('Belgrano'), -- CABA
('Rosario'), -- Santa Fe
('Córdoba Capital'), -- Cordoba
('Mendoza Capital'), -- Mendoza
('San Fernando del Valle de Catamarca'), -- Catamarca
('Resistencia'), -- Chaco
('Rawson'), -- Chubut
('Corrientes Capital'), -- Corrientes
('Paraná'), -- Entre Ríos
('Formosa Capital'), -- Formosa
('San Salvador de Jujuy'), -- Jujuy
('Santa Rosa'), -- La Pampa
('La Rioja Capital'), -- La Rioja
('Posadas'), -- Misiones
('Neuquén Capital'), -- Neuquén
('Viedma'), -- Río Negro
('Salta Capital'), -- Salta
('San Juan Capital'), -- San Juan
('San Luis Capital'), -- San Luis
('Río Gallegos'), -- Santa Cruz
('Santiago del Estero Capital'), -- Santiago del Estero
('Ushuaia'), -- Tierra del Fuego
('San Miguel de Tucumán'); -- Tucumán

INSERT INTO `bdbanco`.`paises` (`nombre`) VALUES
('Argentina'),
('Brasil'),
('Chile'),
('México'),
('Uruguay'),
('Paraguay'),
('Colombia'),
('Perú'),
('Bolivia'),
('Ecuador'),
('Estados Unidos'),
('España'),
('Italia'),
('Canadá'),
('Francia');

-- Asumiendo que '1' es el ID de Argentina en la tabla `paises`.
INSERT INTO `bdbanco`.`clientes` (`dni`, `cuil`, `nombre`, `apellido`, `sexo`, `id_pais`, `fecha_nacimiento`, `direccion`, `id_localidad`, `id_provincia`, `correo`, `telefono`, `create_date`, `deleted`, `delete_date`) VALUES
('20325486982', '20325486982', 'Juan', 'Pérez', 'M', 1, '1985-05-15', 'Av. Rivadavia 123', 1, 1, 'juan.perez@mail.com', '1123456789', '2024-07-01 10:00:00', 0, NULL),
('20555123456', '20555123456', 'Ana', 'González', 'F', 1, '1990-08-22', 'Calle Ficticia 456', 2, 2, 'ana.gonzalez@mail.com', '1167890123', '2024-07-05 11:30:00', 0, NULL),
('20765432100', '20765432100', 'Carlos', 'Rodríguez', 'M', 1, '1975-02-10', 'Calle Libertador 789', 3, 3, 'carlos.rodriguez@mail.com', '1134567890', '2024-07-10 14:15:00', 0, NULL),
('20554433221', '20554433221', 'Lucía', 'López', 'F', 1, '1995-12-30', 'Av. San Martín 1010', 4, 4, 'lucia.lopez@mail.com', '1145678901', '2024-07-15 09:45:00', 0, NULL),
('20987654321', '20987654321', 'Martín', 'Martínez', 'M', 1, '1982-03-11', 'Calle San Juan 2020', 5, 5, 'martin.martinez@mail.com', '1198765432', '2024-07-20 12:00:00', 0, NULL),
('20111222333', '20111222333', 'Sofía', 'Gómez', 'F', 1, '1988-07-03', 'Calle Belgrano 3030', 6, 6, 'sofia.gomez@mail.com', '1156789012', '2024-07-25 15:30:00', 0, NULL),
('20444555666', '20444555666', 'Diego', 'Fernández', 'M', 1, '1979-11-28', 'Av. Pellegrini 4040', 1, 1, 'diego.fernandez@mail.com', '1189012345', '2024-07-30 10:30:00', 0, NULL),
('20777888999', '20777888999', 'Valentina', 'Díaz', 'F', 1, '1992-04-18', 'Calle Moreno 5050', 8, 8, 'valentina.diaz@mail.com', '1178901234', '2024-08-03 11:45:00', 0, NULL),
('20222333444', '20222333444', 'Nicolás', 'García', 'M', 1, '1987-09-09', 'Av. Colón 6060', 9, 9, 'nicolas.garcia@mail.com', '1167890124', '2024-08-07 14:00:00', 0, NULL),
('20555666777', '20555666777', 'Camila', 'Ruiz', 'F', 1, '1998-01-25', 'Calle San Martín 7070', 10, 10, 'camila.ruiz@mail.com', '1134567880', '2024-08-11 09:15:00', 0, NULL),
('20888999000', '20888999000', 'Leonardo', 'Álvarez', 'M', 1, '1980-06-12', 'Av. Rivadavia 8080', 11, 11, 'leonardo.alvarez@mail.com', '1145678900', '2024-08-15 12:30:00', 0, NULL),
('20333444555', '20333444555', 'Isabella', 'Romero', 'F', 1, '1993-10-05', 'Calle Belgrano 9090', 12, 12, 'isabella.romero@mail.com', '1198765457', '2024-08-19 15:00:00', 0, NULL),
('20666777888', '20666777888', 'Gabriel', 'Sánchez', 'M', 1, '1977-03-20', 'Av. Pellegrini 1000', 13, 13, 'gabriel.sanchez@mail.com', '1156789022', '2024-08-23 10:45:00', 0, NULL),
('20999000111', '20999000111', 'Martina', 'Torres', 'F', 1, '1996-08-14', 'Calle Moreno 1100', 14, 14, 'martina.torres@mail.com', '1189012340', '2024-08-27 11:15:00', 0, NULL),
('9988776655', '9988776655', 'Ricardo', 'Silva', 'M', 2, '1989-06-25', 'Calle Corrientes 1300', 1, 1, 'ricardo.silva@mail.com', '1112344321', '2024-09-01 14:30:00', 0, NULL);

-- Usuario administrador sin cliente asociado
INSERT INTO `bdbanco`.`usuarios` (`usuario`, `pass`, `admin`, `create_date`, `deleted`, `delete_date`, `id_cliente`) VALUES
('admin', 'admin', 1, '2023-01-01 10:00:00', 0, NULL, NULL),
('superadmin', 'passadmin1', 1, '2023-01-05 11:30:00', 0, NULL, NULL),
('root', 'root1234', 1, '2023-01-10 14:15:00', 0, NULL, NULL);

-- Usuario regular relacionado con un cliente
INSERT INTO `bdbanco`.`usuarios` (`usuario`, `pass`, `admin`, `create_date`, `deleted`, `delete_date`, `id_cliente`) VALUES
('JPerez12', 'pass111', 0, '2024-08-01 10:00:00', 0, NULL, 1),
('AGonzalez34', 'pass222', 0, '2024-08-05 11:30:00', 0, NULL, 2),
('CRodriguez56', 'pass333', 0, '2024-08-10 14:15:00', 0, NULL, 3),
('LLopez78', 'pass444', 0, '2024-08-15 09:45:00', 0, NULL, 4),
('MMartinez90', 'pass555', 0, '2024-08-20 12:00:00', 0, NULL, 5),
('SGomez21', 'pass666', 0, '2024-08-25 15:30:00', 0, NULL, 6),
('DFernandez43', 'pass777', 0, '2024-08-30 10:30:00', 0, NULL, 7),
('VDiaz65', 'pass888', 0, '2024-09-03 11:45:00', 0, NULL, 8),
('NGarcia87', 'pass999', 0, '2024-09-07 14:00:00', 0, NULL, 9),
('CRuiz09', 'pass1010', 0, '2024-09-11 09:15:00', 0, NULL, 10),
('Alvarez10', 'pass1111', 0, '2024-09-15 12:30:00', 0, NULL, 11),
('IRomero32', 'pass1212', 0, '2024-09-19 15:00:00', 0, NULL, 12),
('GSanchez54', 'pass1313', 0, '2024-09-23 10:45:00', 0, NULL, 13),
('MTorres76', 'pass1414', 0, '2024-09-27 11:15:00', 0, NULL, 14),
('ADiaz98', 'pass1515', 0, '2024-10-01 14:30:00', 0, NULL, 15);

INSERT INTO `bdbanco`.`tipos_cuenta` (`descripcion`) VALUES
('Caja de ahorro'),
('Cuenta corriente');

INSERT INTO `bdbanco`.`tipos_movimiento` (`descripcion`) VALUES
('Alta de cuenta'),
('Alta de un préstamo'),
('Pago de préstamo'),
('Transferencia');

INSERT INTO `bdbanco`.`cuentas` (`id_cliente`, `id_tipo_cuenta`, `cbu`, `saldo`, `create_date`, `deleted`, `delete_date`) VALUES
(1, 1, '0011223344556677889900', 100000.00, '2024-08-01 10:00:00', 0, NULL),
(2, 2, '0022334455667788990011', 5000.50, '2024-08-05 11:30:00', 0, NULL),
(3, 1, '0033445566778899001122', 25000.75, '2024-08-10 14:15:00', 0, NULL),
(4, 2, '0044556677889900112233', 1200000.25, '2024-08-15 09:45:00', 0, NULL),
(5, 1, '0055667788990011223344', 800.00, '2024-08-20 12:00:00', 0, NULL),
(6, 2, '0066778899001122334455', 30000.50, '2024-08-25 15:30:00', 0, NULL),
(7, 1, '0077889900112233445566', 15000000.75, '2024-08-30 10:30:00', 0, NULL),
(8, 2, '0088990011223344556677', 900000.25, '2024-09-03 11:45:00', 0, NULL),
(9, 1, '0099001122334455667788', 2000.00, '2024-09-07 14:00:00', 0, NULL),
(10, 2, '0100112233445566778899', 70000.50, '2024-09-11 09:15:00', 0, NULL),
(11, 1, '0111223344556677889900', 28000.75, '2024-09-15 12:30:00', 0, NULL),
(12, 2, '0122334455667788990011', 13000.25, '2024-09-19 15:00:00', 0, NULL),
(13, 1, '0133445566778899001122', 1100000.00, '2024-09-23 10:45:00', 0, NULL),
(14, 2, '0144556677889900112233', 600000.50, '2024-09-27 11:15:00', 0, NULL),
(15, 1, '0155667788990011223344', 260000.75, '2024-10-01 14:30:00', 0, NULL);

INSERT INTO `bdbanco`.`movimientos` (`detalle`, `importe`, `id_tipos_movimiento`, `nro_cuenta`, `create_date`) VALUES
-- Aperturas de cuenta
('Apertura de cuenta', 100000.00, 1, 1, '2024-08-01 10:00:00'),
('Apertura de cuenta', 5000.50, 1, 2, '2024-08-05 11:30:00'),
('Apertura de cuenta', 25000.75, 1, 3, '2024-08-10 14:15:00'),
('Apertura de cuenta', 1200000.25, 1, 4, '2024-08-15 09:45:00'),
('Apertura de cuenta', 800.00, 1, 5, '2024-08-20 12:00:00'),
('Apertura de cuenta', 30000.50, 1, 6, '2024-08-25 15:30:00'),
('Apertura de cuenta', 15000000.75, 1, 7, '2024-08-30 10:30:00'),
('Apertura de cuenta', 900000.25, 1, 8, '2024-09-03 11:45:00'),
('Apertura de cuenta', 2000.00, 1, 9, '2024-09-07 14:00:00'),
('Apertura de cuenta', 70000.50, 1, 10, '2024-09-11 09:15:00'),
('Apertura de cuenta', 28000.75, 1, 11, '2024-09-15 12:30:00'),
('Apertura de cuenta', 13000.25, 1, 12, '2024-09-19 15:00:00'),
('Apertura de cuenta', 1100000.00, 1, 13, '2024-09-23 10:45:00'),
('Apertura de cuenta', 600000.50, 1, 14, '2024-09-27 11:15:00'),
('Apertura de cuenta', 260000.75, 1, 15, '2024-10-01 14:30:00'),
-- Transferencias
('Transferencia recibida', 5000.00, 4, 1, '2024-10-05 10:00:00'),
('Transferencia enviada', 1000.00, 4, 3, '2024-10-10 11:30:00'),
('Transferencia recibida', 1500.00, 4, 7, '2024-10-15 14:15:00'),
('Transferencia enviada', 2000.00, 4, 10, '2024-10-20 09:45:00'),
('Transferencia recibida', 2800.00, 4, 13, '2024-10-25 12:00:00'),
-- Pagos de préstamotipos_movimiento
('Pago de préstamo', 50000.00, 3, 1, '2025-09-01 10:00:00'),
('Pago de préstamo', 33333.33, 3, 2, '2025-10-02 11:30:00');

INSERT INTO `bdbanco`.`prestamos` (`id`, `observaciones`, `id_cliente`, `nro_cuenta`, `fecha_alta`, `importe`, `cuotas`, `valor_cuotas`, `estado`) VALUES
(1, 'Préstamo personal', 1, 1, '2024-09-01', 50000.00, 1, 50000.00, 'aprobado'),
(2, 'Préstamo para vivienda', 2, 2, '2024-10-02', 100000.00, 3, 33333.33, 'aprobado');

INSERT INTO `bdbanco`.`cuotas` (`id_prestamo`, `nro_cuota`, `monto`, `estado_pago`, `fecha_pago`, `create_date`, `deleted`, `delete_date`) VALUES
(1, 1, 50000.00, 1, '2024-09-01', '2024-09-01 10:00:00', 0, NULL),
(2, 1, 33333.33, 1, '2024-10-02', '2024-10-02 11:30:00', 0, NULL),
(2, 2, 33333.33, 0, NULL, '2024-10-02 11:30:00', 0, NULL),
(2, 3, 33333.34, 0, NULL, '2024-10-02 11:30:00', 0, NULL);

