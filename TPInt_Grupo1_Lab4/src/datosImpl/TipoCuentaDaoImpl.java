package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.TipoCuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
	private Conexion cn;
	public TipoCuentaDaoImpl() {
		cn = new Conexion();
	}
	@Override
	public List<TipoCuenta> leerTiposCuenta() {
		cn.Open();
		String query = "SELECT * FROM tipos_cuenta";
		List<TipoCuenta> tiposCuenta = new ArrayList();
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			ResultSet rs = cst.executeQuery();
			while(rs.next())
			{
				TipoCuenta aux = new TipoCuenta();
				aux.setId(rs.getInt("id"));
				aux.setDescripcion(rs.getString("descripcion"));
				
				tiposCuenta.add(aux);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return tiposCuenta;
	}

}
