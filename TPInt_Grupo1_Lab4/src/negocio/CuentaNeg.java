package negocio;

import java.util.List;

import entidad.Cuenta;
import entidad.Movimiento;

public interface CuentaNeg {
	public List<Cuenta> leerTodasLasCuentas();
	public List<Cuenta> leerLasCuentasDelCliente(int id_cliente);
	public boolean clienteAptoDeAgregarCuenta(int id_cliente);
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public List<Movimiento> leerMovimientosDeCuenta(int nro_cuenta);
}
