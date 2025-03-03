-- 1. 
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

-- 2.
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

-- 3.
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


-- 4.
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
    SET estado = 'aprobado' , observaciones = p_observaciones, fecha_alta = now() -- Cambiar el estado a aprobado
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

-- 5.
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

-- 6.
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

-- 7.
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_SolicitarPrestamo`(
    IN p_idCliente int,
    IN p_nroCuenta int,
    IN p_importe decimal(10,2),
    IN p_cuotas int
)
BEGIN
	INSERT INTO `bdbanco`.`prestamos`
(
`id_cliente`,
`nro_cuenta`,
`importe`,
`cuotas`,
`valor_cuotas`
)
VALUES
	(
	id_cliente,
	nro_cuenta,
	importe,
	cuotas,
	(p_valor/p_cuotas));
END$$
DELIMITER ;
