package datosImpl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import datos.UsuarioDao;
import entidad.Cliente;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Conexion cn;
	public UsuarioDaoImpl() {
		cn = new Conexion();
	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarUsuarios() {
	    List<Usuario> usuarios = new ArrayList<>();
	    cn.Open();
	    String query = "{CALL ObtenerUsuarios()}";

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Usuario usuario = new Usuario();

	            // Asignaci�n de propiedades
	            usuario.setId(rs.getInt("id"));

	            // Manejo del cliente relacionado
	            if (rs.getObject("id_cliente") != null) {
	                Cliente cliente = new Cliente();
	                cliente.setId(rs.getInt("id_cliente"));
	                usuario.setCliente(cliente);
	            }

	            usuario.setUsuario(rs.getString("usuario"));
	            usuario.setPassword(rs.getString("pass")); // Cambiado a setPassword
	            usuario.setAdmin(rs.getBoolean("admin"));

	            // Agregar el usuario a la lista
	            usuarios.add(usuario);
	        }

	    } catch (Exception e) {
	        // Registro de la excepci�n
	        System.err.println("Error al listar los usuarios: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return usuarios;
	}



	@Override
	public boolean insertarOActualizarUsuario(Usuario usuario) {
	    boolean success = false;
	    String query = "{CALL InsertarOActualizarUsuario(?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

	    try {
	        cn.Open();

	        // Preparar la llamada al procedimiento almacenado
	        try (CallableStatement cst = cn.connection.prepareCall(query)) {
	            // Asignaci�n de par�metros
	            if (usuario.getCliente() != null) {
	                cst.setInt(1, usuario.getCliente().getId()); // p_id_cliente
	            } else {
	                cst.setNull(1, java.sql.Types.INTEGER); // En caso de cliente nulo
	            }
	            cst.setString(2, usuario.getUsuario());   // p_usuario
	            cst.setString(3, usuario.getPassword());  // p_pass
	            cst.setBoolean(4, usuario.isAdmin());     // p_admin

	            // Ejecutar el procedimiento almacenado
	            int rowsAffected = cst.executeUpdate();
	            success = rowsAffected > 0;
	        }
	    } catch (Exception e) {
	        System.err.println("Error al insertar o actualizar el usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); // Cerrar la conexi�n
	    }

	    return success;
	}




	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
