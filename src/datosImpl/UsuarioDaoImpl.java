package datosImpl;
import java.sql.CallableStatement;
import java.sql.Date;

import datos.UsuarioDao;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	private Conexion cn;
	public UsuarioDaoImpl()
	{
		cn = new Conexion();
	}
	@Override
	public boolean agregarUsuarioCliente(Usuario usuario) {
		cn.Open();
		String query = "CALL SP_AgregarUsuarioCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean exito = false;
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setString(1, usuario.getCliente().getDni());
			cst.setString(2, usuario.getCliente().getCuil());
			cst.setString(3, usuario.getCliente().getNombre());
			cst.setString(4, usuario.getCliente().getApellido());
			cst.setString(5, usuario.getCliente().getSexo());
			cst.setInt(6, usuario.getCliente().getPaisNacimiento().getId());
			cst.setDate(7, Date.valueOf(usuario.getCliente().getFechaNacimiento()));
			cst.setString(8, usuario.getCliente().getDireccion());
			cst.setInt(9, usuario.getCliente().getLocalidad().getId());
			cst.setInt(10, usuario.getCliente().getProvincia().getId());
			cst.setString(11, usuario.getCliente().getCorreo());
			cst.setString(12, usuario.getCliente().getTelefono());
			cst.setString(13, usuario.getUsuario());
			cst.setString(14, usuario.getPassword());
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

}
