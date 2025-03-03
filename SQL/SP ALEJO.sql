-- VISTAS
CREATE VIEW VW_Clientes AS
SELECT
CLI.id, CLI.dni, CLI.cuil, CLI.nombre,
CLI.apellido, CLI.sexo, CLI.id_pais, PAI.nombre as nombre_pais, CLI.fecha_nacimiento,
CLI.direccion, CLI.id_localidad, LOC.nombre as nombre_localidad, CLI.id_provincia, PROV.nombre as nombre_provincia, CLI.correo,
CLI.telefono, CLI.deleted, USU.usuario, USU.pass
FROM clientes CLI
LEFT JOIN localidades LOC on CLI.id_localidad = LOC.id
LEFT JOIN provincia PROV on CLI.id_provincia = PROV.id
LEFT JOIN paises PAI on CLI.id_pais = PAI.id
LEFT JOIN usuarios USU on USU.id_cliente = CLI.id;

CREATE VIEW VW_Usuarios AS
SELECT
USU.id, USU.id_cliente, USU.usuario, USU.pass, USU.admin, USU.deleted 
FROM usuarios USU;

CREATE VIEW VW_Cuentas AS
SELECT CUE.nro_cuenta, CUE.id_cliente, CLI.dni, CLI.cuil, CLI.nombre, CLI.apellido, CUE.id_tipo_cuenta, TIPCUE.descripcion as descripcion_tipo_cuenta, CUE.cbu, CUE.saldo, CUE.deleted FROM cuentas CUE
LEFT JOIN tipos_cuenta TIPCUE ON CUE.id_tipo_cuenta = TIPCUE.id
LEFT JOIN clientes CLI ON CLI.id = CUE.id_cliente;

-- PARTE DE TRANSFERENCIA
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_AgregarPrestamo`(
	IN p_idCliente int,
    IN p_nroCuenta int,
    IN p_importe decimal(10,2),
    IN p_cuotas int,
    IN p_observaciones varchar(255)
)
BEGIN
	INSERT INTO `bdbanco`.`prestamos`
	(`id_cliente`,
	`nro_cuenta`,
	`importe`,
	`cuotas`,
	`valor_cuotas`,
    `observaciones`)
	VALUES
	(p_idCliente,
	p_nroCuenta,
	p_importe,
	p_cuotas,
	(p_importe/p_cuotas),
    p_observaciones);

END$$
DELIMITER ;

-- PARTE DE TIPOS CUENTA
-- NO USE STORED

-- PARTE DE CUENTAS
delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_AgregarCuenta`(in id_cliente_seleccionado INT, in id_tipo_cuenta_seleccionado INT)
BEGIN
	DECLARE ultimoNroCuenta INT DEFAULT 0;
	DECLARE ultimoIdMovimiento INT DEFAULT 0;
	DECLARE saldo INT DEFAULT 0;
	START TRANSACTION;
			INSERT INTO cuentas (id_cliente, id_tipo_cuenta, cbu, saldo, create_date)
			VALUES (id_cliente_seleccionado, id_tipo_cuenta_seleccionado, REPLACE(REPLACE(REPLACE(DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), '-', ''), ':', ''), ' ', ''), 0, CURDATE());

			SELECT LAST_INSERT_ID() INTO ultimoNroCuenta;
			INSERT INTO movimientos(detalle, importe, id_tipos_movimiento, nro_cuenta, create_date) 
			VALUES ('Alta de Cuenta', 10000, 1, ultimoNroCuenta, CURDATE());

			UPDATE cuentas SET saldo = saldo + 10000 WHERE nro_cuenta = ultimoNroCuenta;
	COMMIT;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ModificarCuenta`(in nro_cuenta_input int, in id_tipo_cuenta_input int)
BEGIN
	UPDATE cuentas SET id_tipo_cuenta = id_tipo_cuenta_input WHERE nro_cuenta = nro_cuenta_input;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LeerUnaCuenta`(in nro_cuenta_input int)
BEGIN
	SELECT * FROM vw_cuentas WHERE nro_cuenta = nro_cuenta_input;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CuantasCuentasActivaTieneElCliente`(in id_cliente_input int)
BEGIN
	SELECT COUNT(*) FROM cuentas WHERE id_cliente = id_cliente_input AND deleted = 0;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LeerCuentasActivasRelacionadasACliente`(in id_cliente_input int)
BEGIN
	SELECT * FROM vw_cuentas WHERE id_cliente = id_cliente_input and deleted = 0;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_EliminarCuenta`(in nro_cuenta_input int)
BEGIN
	UPDATE cuentas SET deleted = 1 WHERE nro_cuenta = nro_cuenta_input;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_MovimientosDeCuenta`(IN nro_cuenta_input int)
BEGIN
select M.id,  M.detalle, M.importe, TM.id as TMid, TM.descripcion as TMdescripcion, M.nro_cuenta, M.create_date, M.deleted, M.delete_date  from movimientos M
left join tipos_movimiento TM on TM.id = M.id_tipos_movimiento
left join cuentas C on C.nro_cuenta = M.nro_cuenta
WHERE M.deleted = 0 and C.nro_cuenta = nro_cuenta_input;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ObtenerCuentaXcbu`(IN cbuInput VARCHAR(255))
BEGIN
	select * from vw_cuentas where cbu like cbuInput;
END;
$$

delimiter $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ObtenerClienteXUserPass`(in userInput varchar(255), in passInput varchar(255))
BEGIN
SELECT * from vw_clientes where usuario like userInput and pass like passInput;
END;
$$