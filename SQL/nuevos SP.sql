
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
CREATE PROCEDURE SP_ValidarUsuario(
    IN usuarioIngresado VARCHAR(50), 
    IN contraseniaIngresada VARCHAR(50)
) 
BEGIN
    -- Verificar si el usuario existe y es administrador
    IF EXISTS (
        SELECT 1 
        FROM usuarios 
        WHERE usuario = usuarioIngresado 
          AND pass = contraseniaIngresada 
          AND admin = 1
    ) THEN
        -- Retornar solo los datos del administrador
        SELECT 
            usuario, 
            admin 
        FROM 
            usuarios 
        WHERE 
            usuario = usuarioIngresado 
            AND pass = contraseniaIngresada;
    ELSE
        -- Retornar datos del cliente si no es administrador
        SELECT 
            c.*, 
            u.admin, 
            u.usuario,
            p.nombre AS nombre_pais,
            pro.nombre AS nombre_provincia,
            l.nombre AS nombre_localidad
        FROM 
            usuarios u
        LEFT JOIN 
            clientes c 
        ON 
            u.id_cliente = c.id
        LEFT JOIN 
            paises p
        ON 
            c.id_pais = p.id
		LEFT JOIN
			provincia pro
		ON
			c.id_provincia = pro.id
		LEFT JOIN
			localidades l
		ON
			c.id_localidad = l.id
        WHERE 
            u.usuario = usuarioIngresado 
            AND u.pass = contraseniaIngresada;
    END IF;
END$$
DELIMITER ;

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
DELIMITER $$
CREATE PROCEDURE SP_CuantasCuentasActivaTieneElCliente(in id_cliente_input int)
BEGIN
	SELECT COUNT(*) FROM cuentas WHERE id_cliente = id_cliente_input AND deleted = 0;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_LeerUnaCuenta(in nro_cuenta_input int)
BEGIN
	SELECT * FROM vw_cuentas WHERE nro_cuenta = nro_cuenta_input;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_LeerCuentasActivasRelacionadasACliente(in id_cliente_input int)
BEGIN
	SELECT * FROM vw_cuentas WHERE id_cliente = id_cliente_input and deleted = 0;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_ModificarCuenta(in nro_cuenta_input int, in id_tipo_cuenta_input int)
BEGIN
	UPDATE cuentas SET id_tipo_cuenta = id_tipo_cuenta_input WHERE nro_cuenta = nro_cuenta_input;
END;
$$

DELIMITER $$
CREATE PROCEDURE SP_EliminarCuenta(in nro_cuenta_input int)
BEGIN
	UPDATE cuentas SET deleted = 1 WHERE nro_cuenta = nro_cuenta_input;
END;
$$

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
    SET estado = 'aprobado' , observaciones = p_observaciones, fecha_alta = now() 
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

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_ListarPrestamos`()
BEGIN
	SELECT   
        p.id AS Id,  
        cl.nombre AS Nombre,
        cl.apellido AS Apellido,  
        cl.correo AS Correo,  
        cl.telefono AS Telefono,  
        cu.cbu AS CBU,
        cu.id_cliente as Id_cliente,
        cu.nro_cuenta as Nro_cuenta,
        p.create_date as Fecha_solicitud,  
        p.importe AS Importe,  
        p.cuotas AS Cuotas,  
        p.observaciones AS Observaciones,
        p.estado AS Estado
    FROM   
        bdbanco.prestamos p  
    JOIN   
        bdbanco.clientes cl ON p.id_cliente = cl.id  
    JOIN   
        bdbanco.cuentas cu ON p.nro_cuenta = cu.nro_cuenta AND cu.deleted = 0  
    WHERE   
        p.deleted = 0 ; 
END$$
DELIMITER ;


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


DELIMITER $$
CREATE PROCEDURE SP_MovimientosDeCuenta(IN nro_cuenta_input int)
BEGIN
select M.id,  M.detalle, M.importe, TM.id as TMid, TM.descripcion as TMdescripcion, M.nro_cuenta, M.create_date, M.deleted, M.delete_date  from movimientos M
left join tipos_movimiento TM on TM.id = M.id_tipos_movimiento
left join cuentas C on C.nro_cuenta = M.nro_cuenta
WHERE M.deleted = 0 and C.nro_cuenta = nro_cuenta_input;
END;
$$
DELIMITER $$
CREATE procedure SP_ObtenerClienteXUserPass(in userInput varchar(255), in passInput varchar(255))
BEGIN
SELECT * from vw_clientes where usuario like userInput and pass like passInput;
END;
$$


DELIMITER $$

CREATE PROCEDURE SP_AgregarTransferencia(
    IN cbuOrigen VARCHAR(255), 
    IN cbuDestino VARCHAR(255), 
    IN montoInput DECIMAL(10,2), 
    IN detalleInput VARCHAR(255)
)
BEGIN
	DECLARE cuentaOrigen INT DEFAULT 0;
    DECLARE cuentaDestino INT DEFAULT 0;
    
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
        ROLLBACK; 
    START TRANSACTION;

		-- HACER EL MOVIMIENTO DEL LADO ORIGEN
		
		SELECT nro_cuenta INTO cuentaOrigen FROM cuentas WHERE cbu LIKE cbuOrigen;
		
		INSERT INTO movimientos (detalle, importe, id_tipos_movimiento, nro_cuenta, create_date)
		VALUES (detalleInput, montoInput * (-1), 4, cuentaOrigen, CURDATE());
		
		UPDATE cuentas SET saldo = saldo - montoInput WHERE cbu LIKE cbuOrigen;

		-- HACER EL MOVIMIENTO DEL LADO DESTINO
		
		SELECT nro_cuenta INTO cuentaDestino FROM cuentas WHERE cbu LIKE cbuDestino;
		
		INSERT INTO movimientos (detalle, importe, id_tipos_movimiento, nro_cuenta, create_date)
		VALUES (detalleInput, montoInput, 4, cuentaDestino, CURDATE());
		
		UPDATE cuentas SET saldo = saldo + montoInput WHERE cbu LIKE cbuDestino;

    COMMIT;
END $$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_ObtenerCuentaXcbu(IN cbuInput VARCHAR(255))
BEGIN
	select * from vw_cuentas where cbu like cbuInput;
END;
$$

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerPrestamosFiltrados`(
	IN p_mayor_a decimal,
    IN p_menor_a decimal
)
BEGIN

SELECT 

 c.id AS ID_Cliente,
        c.dni AS DNI,
        c.nombre AS Nombre,
        c.apellido AS Apellido,
        p.id AS ID_Prestamo,
        p.importe AS Monto_Solicitado,
        p.cuotas AS Cuotas,
        p.estado AS Estado
	
FROM prestamos p
INNER JOIN clientes c ON p.id_cliente = c.id
WHERE p.deleted = 0 and p.importe > p_mayor_a and p.importe < p_menor_a;

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerPrestamos`()
BEGIN

SELECT 

 c.id AS ID_Cliente,
        c.dni AS DNI,
        c.nombre AS Nombre,
        c.apellido AS Apellido,
        p.id AS ID_Prestamo,
        p.importe AS Monto_Solicitado,
        p.cuotas AS Cuotas,
        p.estado AS Estado,
        p.create_date AS Fecha_Solicitud,
        p.observaciones AS Tipo_Prestamo
     


FROM prestamos p
INNER JOIN clientes c ON p.id_cliente = c.id


WHERE p.estado="pendiente" and p.deleted = 0;

END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarPrestamosPorCliente`(IN IdCliente INT)
BEGIN
    SELECT 
        p.id AS ID_Prestamo,
        p.observaciones AS Tipo_Prestamo,
        p.id_cliente AS ID_Cliente,
        p.nro_cuenta AS Nro_Cuenta,
        p.fecha_alta AS Fecha_Solicitud,
        p.importe AS Monto_Solicitado,
        p.cuotas AS Cuotas,
        p.valor_cuotas AS Valor_Cuota,
        p.estado AS Estado
    FROM prestamos p
    WHERE p.id_cliente = IdCliente
        AND p.estado = 'aprobado' 
        AND p.deleted = 0;
END;
$$

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarPrestamosPorClientesPendientes`(IN IdCliente INT)
BEGIN
    SELECT 
        p.id AS ID_Prestamo,
        p.observaciones AS Tipo_Prestamo,
        p.id_cliente AS ID_Cliente,
        p.nro_cuenta AS Nro_Cuenta,
        p.fecha_alta AS Fecha_Solicitud,
        p.importe AS Monto_Solicitado,
        p.cuotas AS Cuotas,
        p.valor_cuotas AS Valor_Cuota,
        p.estado AS Estado
    FROM prestamos p
    WHERE p.id_cliente = IdCliente
        AND p.estado = 'pendiente' 
        AND p.deleted = 0;
END;
$$

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagarCuota`(  
    IN p_id_prestamo INT  
)
BEGIN  
    DECLARE v_monto DECIMAL(10,2);  
    DECLARE v_cuotas_pendientes INT;  
    DECLARE v_importe DECIMAL(10,2);  
    DECLARE v_cuotas INT;  
    DECLARE v_id_cuota INT;  
    DECLARE v_nro_cuenta INT;  
    DECLARE v_saldo_actual DECIMAL(10,2);  
    DECLARE v_mensaje VARCHAR(255);  

    -- Manejo de errores
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION  
    BEGIN  
        ROLLBACK;  
        SELECT 'Error en el proceso de pago. Intente nuevamente.' AS mensaje;  
    END;  

    START TRANSACTION;  

    -- Obtener datos del préstamo en una sola consulta  
    SELECT importe, cuotas, nro_cuenta INTO v_importe, v_cuotas, v_nro_cuenta  
    FROM prestamos  
    WHERE id = p_id_prestamo  
    FOR UPDATE;  -- Bloquea el registro para evitar modificaciones concurrentes  

    -- Obtener la cuota más antigua no pagada  
    SELECT id, monto INTO v_id_cuota, v_monto  
    FROM cuotas  
    WHERE id_prestamo = p_id_prestamo AND estado_pago = 0  
    ORDER BY nro_cuota ASC  
    LIMIT 1  
    FOR UPDATE;  

    -- Si no hay cuotas pendientes, finalizar  
    IF v_monto IS NULL THEN  
        SET v_mensaje = 'No hay más cuotas pendientes. El préstamo ya está finalizado.';  
    ELSE  
        -- Obtener saldo actual de la cuenta asociada  
        SELECT saldo INTO v_saldo_actual  
        FROM cuentas  
        WHERE nro_cuenta = v_nro_cuenta  
        FOR UPDATE;  

        -- Verificar si hay saldo suficiente  
        IF v_saldo_actual < v_monto THEN  
            SET v_mensaje = 'Saldo insuficiente en la cuenta para pagar la cuota.';  
        ELSE  
            -- Descontar el monto pagado del saldo de la cuenta  
            UPDATE cuentas  
            SET saldo = saldo - v_monto  
            WHERE nro_cuenta = v_nro_cuenta;  

            -- Marcar la cuota como pagada  
            UPDATE cuotas   
            SET fecha_pago = CURRENT_DATE(), estado_pago = 1  
            WHERE id = v_id_cuota;  

            -- Actualizar el importe del préstamo  
            UPDATE prestamos   
            SET importe = GREATEST(importe - v_monto, 0)  
            WHERE id = p_id_prestamo;  

            -- Descontar una cuota  
            UPDATE prestamos   
            SET cuotas = GREATEST(cuotas - 1, 0)  
            WHERE id = p_id_prestamo;  

            -- Contar cuántas cuotas quedan pendientes  
            SELECT COUNT(*) INTO v_cuotas_pendientes  
            FROM cuotas  
            WHERE id_prestamo = p_id_prestamo AND estado_pago = 0;  

            -- Si no quedan cuotas, marcar el préstamo como finalizado  
            IF v_cuotas_pendientes = 0 THEN  
                UPDATE prestamos   
                SET estado = 'finalizado'  
                WHERE id = p_id_prestamo;  

                SET v_mensaje = 'El préstamo ha sido completamente pagado y está finalizado.';  
            ELSE  
                SET v_mensaje = CONCAT(v_cuotas_pendientes, ' cuotas pendientes.');  
            END IF;  
        END IF;  
    END IF;  

    COMMIT;  

    -- Retornar mensaje  
    SELECT v_mensaje AS mensaje;  
END;
$$

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarPrestamosDeClientesPorEstados`(IN IdCliente INT)
BEGIN
    SELECT 
        p.id AS ID_Prestamo,
        p.observaciones AS Tipo_Prestamo,
        p.id_cliente AS ID_Cliente,
        p.nro_cuenta AS Nro_Cuenta,
        p.fecha_alta AS Fecha_Solicitud,
        p.importe AS Monto_Solicitado,
        p.cuotas AS Cuotas,
        p.valor_cuotas AS Valor_Cuota,
        p.estado AS Estado
    FROM prestamos p
    WHERE p.id_cliente = IdCliente
        AND p.deleted = 0;
END;
$$