package datosImpl;

import java.sql.CallableStatement;

import datos.TransferenciaDao;
import entidad.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {
	private Conexion cn;
	public TransferenciaDaoImpl() {
		cn = new Conexion();
	}
	
	@Override
	public boolean agregarTransferencia(Transferencia transferencia) {
		cn.Open();
		boolean exito = false;
		final String query = "CALL SP_AgregarTransferencia(?,?,?,?)";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setString(1, transferencia.getCuentaOrigen().getCbu());
			cst.setString(2, transferencia.getCuentaDestino().getCbu());
			cst.setBigDecimal(3, transferencia.getMonto());
			cst.setString(4, transferencia.getDetalle());
			exito = cst.execute();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return exito;
	}
	
}
