
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

