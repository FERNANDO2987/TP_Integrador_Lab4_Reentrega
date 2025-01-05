INSERT INTO `bdbanco`.`provincia` (`nombre`) VALUES 
('Buenos Aires'),
('CABA'),
('Santa Fe'),
('Córdoba'),
('Mendoza');

INSERT INTO `bdbanco`.`localidades` (`nombre`) VALUES 
('La Plata'),
('Mar del Plata'),
('Rosario'),
('Córdoba Capital'),
('Mendoza Capital');

-- Asumiendo que '1' es el ID de Argentina en la tabla `paises`.
INSERT INTO `bdbanco`.`clientes` (`dni`, `cuil`, `nombre`, `apellido`, `sexo`, `id_pais`, `fecha_nacimiento`, `direccion`, `id_localidad`, `id_provincia`, `correo`, `telefono`) 
VALUES 
('20325486982', '20325486982', 'Juan', 'Pérez', 'M', 1, '1985-05-15', 'Av. Rivadavia 123', 1, 1, 'juan.perez@mail.com', '1123456789'),
('20555123456', '20555123456', 'Ana', 'González', 'F', 1, '1990-08-22', 'Calle Ficticia 456', 2, 2, 'ana.gonzalez@mail.com', '1167890123'),
('20765432100', '20765432100', 'Carlos', 'Rodríguez', 'M', 1, '1975-02-10', 'Calle Libertador 789', 3, 3, 'carlos.rodriguez@mail.com', '1134567890'),
('20554433221', '20554433221', 'Lucía', 'López', 'F', 1, '1995-12-30', 'Av. San Martín 1010', 4, 4, 'lucia.lopez@mail.com', '1145678901'),
('20987654321', '20987654321', 'Martín', 'Martínez', 'M', 1, '1982-03-11', 'Calle San Juan 2020', 5, 5, 'martin.martinez@mail.com', '1198765432');

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
('Ecuador');

-- Usuario administrador sin cliente asociado
INSERT INTO `bdbanco`.`usuarios` (`usuario`, `pass`, `admin`, `create_date`, `deleted`, `delete_date`, `id_cliente`) 
VALUES 
('admin', 'adminpassword123', 1, NOW(), 0, NULL, NULL);

-- Usuario regular relacionado con un cliente
INSERT INTO `bdbanco`.`usuarios` (`usuario`, `pass`, `admin`, `create_date`, `deleted`, `delete_date`, `id_cliente`) 
VALUES 
('usuario1', 'password123', 0, NOW(), 0, NULL, 1),  -- Relacionado con cliente ID 1
('usuario2', 'password456', 0, NOW(), 0, NULL, 2),  -- Relacionado con cliente ID 2
('usuario3', 'password789', 0, NOW(), 0, NULL, 3);  -- Relacionado con cliente ID 3

