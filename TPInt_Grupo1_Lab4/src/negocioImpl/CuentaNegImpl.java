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

}
