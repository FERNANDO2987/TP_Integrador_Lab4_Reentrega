package negocioImpl;

import datos.CuentaDao;
import datos.TransferenciaDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.TransferenciaDaoImpl;
import entidad.Transferencia;
import negocio.TransferenciaNeg;

public class TransferenciaNegImpl implements TransferenciaNeg {

	@Override
	public boolean agregarTransferencia(Transferencia transferencia) {
		TransferenciaDao  transferenciaDao = new TransferenciaDaoImpl();
		return transferenciaDao.agregarTransferencia(transferencia);
	}

	@Override
	public boolean validarCbuOrigen(Transferencia transferencia) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.existeEsteCbu(transferencia.getCuentaOrigen().getCbu());
	}

	@Override
	public boolean validarCbuDestino(Transferencia transferencia) {
		CuentaDao cuentaDao = new CuentaDaoImpl();
		return cuentaDao.existeEsteCbu(transferencia.getCuentaDestino().getCbu());
	}

	@Override
	public boolean validarDineroOrigen(Transferencia transferencia) {
		return false;
		
	}

	@Override
	public boolean validarDetalle(Transferencia transferencia) {
		if(transferencia.getDetalle() == null || transferencia.getDetalle().isEmpty())
		{
			return false;
		}
		return true;
	}

}
