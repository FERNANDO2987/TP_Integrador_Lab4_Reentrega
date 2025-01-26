package negocioImpl;

import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {

	@Override
	public List<Cuenta> leerTodasLasCuentas() {
		CuentaDao cuentaDao =  new CuentaDaoImpl();
		List<Cuenta> cuentas  = cuentaDao.leerTodasLasCuentas();
		return cuentas;
	}

	@Override
	public List<Cuenta> leerLasCuentasDelCliente(int id_cliente) {
		CuentaDao cuentaDao =  new CuentaDaoImpl();
		List<Cuenta> cuentas  = cuentaDao.leerCuentasActivasRelacionadasACliente(id_cliente);
		return cuentas;
		
	}

	@Override
	public boolean clienteAptoDeAgregarCuenta(int id_cliente) {
		CuentaDao cuentaDao =  new CuentaDaoImpl();
		int cantCuentas = cuentaDao.CuantasCuentasActivasTieneElCliente(id_cliente);
		if(cantCuentas < 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
