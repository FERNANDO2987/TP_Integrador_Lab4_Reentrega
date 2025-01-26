package negocioImpl;

import java.util.List;

import datos.TipoCuentaDao;
import datosImpl.TipoCuentaDaoImpl;
import entidad.TipoCuenta;
import negocio.TipoCuentaNeg;

public class TipoCuentaNegImpl implements TipoCuentaNeg {

	@Override
	public List<TipoCuenta> leerTiposCuenta() {
		TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
		return tipoCuentaDao.leerTiposCuenta();
	}

}
