-- Deshabilitar la comprobación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Truncar todas las tablas
TRUNCATE TABLE `bdbanco`.`cuotas`;
TRUNCATE TABLE `bdbanco`.`prestamos`;
TRUNCATE TABLE `bdbanco`.`movimientos`;
TRUNCATE TABLE `bdbanco`.`cuentas`;
TRUNCATE TABLE `bdbanco`.`usuarios`;
TRUNCATE TABLE `bdbanco`.`clientes`;
TRUNCATE TABLE `bdbanco`.`localidades`;
TRUNCATE TABLE `bdbanco`.`provincia`;
TRUNCATE TABLE `bdbanco`.`paises`;
TRUNCATE TABLE `bdbanco`.`tipos_cuenta`;
TRUNCATE TABLE `bdbanco`.`tipos_movimiento`;

-- Habilitar la comprobación de claves foráneas nuevamente
SET FOREIGN_KEY_CHECKS = 1;

