
CREATE VIEW VW_Clientes AS
SELECT
CLI.id, CLI.dni, CLI.cuil, CLI.nombre,
CLI.apellido, CLI.sexo, CLI.id_pais, PAI.nombre as nombre_pais, CLI.fecha_nacimiento,
CLI.direccion, CLI.id_localidad, LOC.nombre as nombre_localidad, CLI.id_provincia, PROV.nombre as nombre_provincia, CLI.correo,
CLI.telefono, CLI.deleted 
FROM clientes CLI
LEFT JOIN localidades LOC on CLI.id_localidad = LOC.id
LEFT JOIN provincia PROV on CLI.id_provincia = PROV.id
LEFT JOIN paises PAI on CLI.id_pais = PAI.id;

CREATE VIEW VW_Usuarios AS
SELECT
USU.id, USU.id_cliente, USU.usuario, USU.pass, USU.admin, USU.deleted 
FROM usuarios USU;

CREATE VIEW VW_Cuentas AS
SELECT CUE.nro_cuenta, CUE.id_cliente, CLI.dni, CLI.cuil, CLI.nombre, CLI.apellido, CUE.id_tipo_cuenta, TIPCUE.descripcion as descripcion_tipo_cuenta, CUE.cbu, CUE.saldo, CUE.deleted FROM cuentas CUE
LEFT JOIN tipos_cuenta TIPCUE ON CUE.id_tipo_cuenta = TIPCUE.id
LEFT JOIN clientes CLI ON CLI.id = CUE.id_cliente;

DELIMITER $$
CREATE PROCEDURE SP_ObtenerUnCliente(IN id_ingresado int)
BEGIN
	select * from vw_clientes where id = id_ingresado;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_AgregarUsuarioCliente (IN dni_input varchar(255), IN cuil_input varchar(255), IN nombre_input varchar(255), IN apellido_input varchar(255), IN sexo_input varchar(255), IN id_pais_input int, IN fecha_nacimiento_input DATE, IN direccion_input varchar(255), IN id_localidad_input int, IN id_provincia_input int, IN correo_input varchar(255), IN telefono_input varchar(255), IN usuario_input varchar(255), IN pass_input varchar(255) )
BEGIN
	declare last_id int default 0;
	start transaction;
		insert into clientes (dni, cuil, nombre, apellido, sexo, id_pais, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono)
		values (dni_input, cuil_input, nombre_input, apellido_input, sexo_input, id_pais_input, fecha_nacimiento_input, direccion_input, id_localidad_input, id_provincia_input, correo_input, telefono_input);
		
		select last_insert_id() into last_id;
		insert into usuarios (id_cliente, usuario, pass, admin) values (last_id, usuario_input, pass_input, 0);
	commit;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_ModificarCliente (IN id_input int, IN dni_input varchar(255), IN cuil_input varchar(255), IN nombre_input varchar(255), IN apellido_input varchar(255), IN sexo_input varchar(255), IN id_pais_input int, IN fecha_nacimiento_input DATE, IN direccion_input varchar(255), IN id_localidad_input int, IN id_provincia_input int, IN correo_input varchar(255), IN telefono_input varchar(255))
BEGIN
	update clientes set dni = dni_input, cuil = cuil_input, nombre = nombre_input, apellido = apellido_input, sexo = sexo_input, id_pais = id_pais_input, fecha_nacimiento = fecha_nacimiento_input, direccion = direccion_input, id_localidad = id_localidad_input, id_provincia = id_provincia_input, correo = correo_input, telefono = telefono_input where id = id_input;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_EliminarCliente (IN id_input INT)
BEGIN
	update clientes set deleted = 1 where id = id_input;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_AgregarCuenta(in id_cliente_seleccionado INT, in id_tipo_cuenta_seleccionado INT)
BEGIN
	DECLARE cuentasActivas INT DEFAULT 0;
	DECLARE ultimoNroCuenta INT DEFAULT 0;
	DECLARE ultimoIdMovimiento INT DEFAULT 0;
	DECLARE saldo INT DEFAULT 0;
    DECLARE error_msg TEXT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
        error_msg = MESSAGE_TEXT;
        Select error_msg;
		ROLLBACK;
	END;

	START TRANSACTION;
	SELECT COUNT(*) INTO cuentasActivas 
	FROM cuentas 
	WHERE id_cliente = id_cliente_seleccionado AND deleted = 0;

	IF cuentasActivas < 3 THEN
		INSERT INTO cuentas (id_cliente, id_tipo_cuenta, cbu, saldo, createDate)
		VALUES (id_cliente_seleccionado, id_tipo_cuenta_seleccionado, REPLACE(REPLACE(REPLACE(DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), '-', ''), ':', ''), ' ', ''), 0, CURDATE());

		SELECT LAST_INSERT_ID() INTO ultimoNroCuenta;
		INSERT INTO movimientos(detalle, importe, id_tipos_movimiento, nro_cuenta, createDate) 
		VALUES ('Alta de Cuenta', 10000, 1, ultimoNroCuenta, CURDATE());

		UPDATE cuentas SET saldo = saldo + 10000 WHERE nro_cuenta = ultimoNroCuenta;
	ELSE
		SELECT 'Se excedio el limite de cuentas' AS mensaje;
	END IF;

	COMMIT;
END; $$

-- Crear Movimiento
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_AgregarMovimiento`(
	IN p_detalle varchar(255),
    IN p_importe decimal(10,2),
    IN p_tipoMovimiento int,
    IN p_nroCuenta int
)
BEGIN
    INSERT INTO `bdbanco`.`movimientos`
	(`detalle`,
	`importe`,
	`id_tipos_movimiento`,
	`nro_cuenta`)
	VALUES(
	p_detalle,
	p_importe,
	p_tipoMovimiento,
	p_nroCuenta);
END$$
DELIMITER ;

-- rechazar prestamo

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_RechazarPrestamo`(
	IN p_id_prestamo int,
    IN p_observaciones varchar(255)
)
BEGIN
	update prestamos 
    set estado = 'rechazado', observaciones = p_observaciones
    where id = p_id_prestamo; 
END$$
DELIMITER ;


-- aprobar prestamo

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_AprobarPrestamo`(
	IN p_id int,
    IN p_observaciones varchar(255)
)
BEGIN
	DECLARE v_importe DECIMAL(10,2);
    DECLARE v_nro_cuenta INT;
    DECLARE v_estado_prestamo VARCHAR(255); 
    DECLARE v_cuotas INT;
    DECLARE v_valor_cuotas decimal(10,2);
    DECLARE contador INT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        ROLLBACK; 
    START TRANSACTION;
    
	SELECT 
		nro_cuenta, 
		cuotas, 
		valor_cuotas, 
		estado, 
		importe
	INTO 
		v_nro_cuenta, 
		v_cuotas, 
		v_valor_cuotas, 
		v_estado_prestamo, 
		v_importe
	FROM prestamos
	WHERE id = p_id;

    IF v_estado_prestamo <> ('pendiente') THEN
        -- Si el prestamo ya fue gestionado, salir
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'ERROR';
    END IF;
    
    -- Agregar movimiento 
    call SP_AgregarMovimiento('Prestamo aprobado', v_importe, 2, v_nro_cuenta);
    
    -- Set estado de prestamo a 'aprobado'
    UPDATE prestamos
    SET estado = 'aprobado' , observaciones = p_observaciones -- Cambiar el estado a aprobado
    WHERE id = p_id;
    
    -- Sumar dinero a la cuenta
    UPDATE cuentas
    SET saldo = saldo + v_importe
    WHERE nro_cuenta = v_nro_cuenta;
    
    
    -- Crear cuotas    
 
    SET contador = v_cuotas;

    WHILE contador > 0 DO
        INSERT INTO cuotas (id_prestamo, nro_cuota, monto)
        VALUES (p_id, contador, v_valor_cuotas);
        
        SET contador = contador - 1;
    END WHILE;
    
    COMMIT;
END$$
DELIMITER ;
