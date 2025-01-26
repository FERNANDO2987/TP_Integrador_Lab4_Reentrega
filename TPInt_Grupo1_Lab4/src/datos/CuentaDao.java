package datos;

import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public List<Cuenta> leerTodasLasCuentas();
	public Cuenta leerUnaCuenta(int id_cuenta);
	public int CuantasCuentasActivasTieneElCliente(int id_cliente);
	public List<Cuenta> leerCuentasActivasRelacionadasACliente(int id_cliente);
	
}
