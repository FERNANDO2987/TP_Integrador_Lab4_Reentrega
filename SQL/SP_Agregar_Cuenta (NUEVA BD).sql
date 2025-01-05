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
