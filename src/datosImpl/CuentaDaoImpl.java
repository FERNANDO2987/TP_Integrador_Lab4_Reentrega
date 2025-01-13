package datosImpl;

import java.sql.CallableStatement;
import java.util.List;

import datos.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {
	private Conexion cn;
	public CuentaDaoImpl() {
		cn = new Conexion();
	}
	
	public boolean agregarCuenta(Cuenta cuenta) {
		cn.Open();
		String query = "CALL SP_agregarCuenta(?,?)";
		boolean exito = false;
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1,cuenta.getCliente().getId());
			cst.setInt(2,cuenta.getTipoCuenta().getId());
			exito = cst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return exito;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cuenta> leerTodasLasCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta leerUnaCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

}
