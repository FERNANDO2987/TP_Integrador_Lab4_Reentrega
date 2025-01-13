package datos;

import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public List<Cuenta> leerTodasLasCuentas();
	public Cuenta leerUnaCuenta(Cuenta cuenta);
}
