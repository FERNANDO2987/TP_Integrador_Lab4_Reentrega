package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		cn.Open();
		String query = "select * from vw_cuentas";
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			ResultSet rs = cst.executeQuery();
			while(rs.next())
			{
				Cuenta aux = new Cuenta();
				aux.setNroCuenta(rs.getInt("nro_cuenta"));
				aux.getCliente().setId(rs.getInt("id_cliente"));
				aux.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
				aux.getTipoCuenta().setDescripcion(rs.getString("descripcion_tipo_cuenta"));
				aux.setCbu(rs.getString("cbu"));
				aux.setSaldo(rs.getBigDecimal("saldo"));
				aux.setDeleted(rs.getBoolean("deleted"));
				
				cuentas.add(aux);
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
		return cuentas;
	}

	@Override
	public Cuenta leerUnaCuenta(int id_cuenta) {
		cn.Open();
		String query = "CALL SP_LeerUnaCuenta(?)";
		Cuenta aux = new Cuenta();
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			ResultSet rs = cst.executeQuery();
			rs.next();
			aux.setNroCuenta(rs.getInt("nro_cuenta"));
			aux.getCliente().setId(rs.getInt("id_cliente"));
			aux.getTipoCuenta().setId(rs.getInt("id_tipo_cuenta"));
			aux.getTipoCuenta().setDescripcion(rs.getString("descripcion_tipo_cuenta"));
			aux.setCbu(rs.getString("cbu"));
			aux.setSaldo(rs.getBigDecimal("saldo"));
			aux.setDeleted(rs.getBoolean("deleted"));
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return aux;
	}

	@Override
	public int CuantasCuentasActivasTieneElCliente(int id_cliente) {
		cn.Open();
		String query = "CALL SP_CuantasCuentasActivaTieneElCliente(?)";
		int resultado = 0;
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, id_cliente);
			ResultSet rs = cst.executeQuery();
			rs.next();
			resultado = rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return resultado;
	}

}