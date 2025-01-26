package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {
	public List<Cuenta> leerTodasLasCuentas();
	public List<Cuenta> leerLasCuentasDelCliente(int id_cliente);
	public boolean clienteAptoDeAgregarCuenta(int id_cliente);
}
