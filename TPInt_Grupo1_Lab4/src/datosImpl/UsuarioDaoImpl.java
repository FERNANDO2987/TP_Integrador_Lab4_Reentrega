package datosImpl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import datos.UsuarioDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import entidad.Usuario;
import entidad.UsuarioCliente;

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

	            // Asignación de propiedades
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
	        // Registro de la excepción
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
	            // Asignación de parámetros
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
	        cn.close(); // Cerrar la conexión
	    }

	    return success;
	}


	
	@Override
	public UsuarioCliente obtenerUsuarioPorIdCliente(int idCliente) {
	    UsuarioCliente usuario = null;
	    String query = "SELECT * FROM UsuarioCliente WHERE idCliente = ?"; // Consulta SQL para obtener el usuario

	    // Preparar la conexión y consulta
	    try (PreparedStatement ps = cn.connection.prepareStatement(query)) {
	        // Establecer el parámetro para la consulta
	        ps.setInt(1, idCliente);

	        // Ejecutar la consulta
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Si encontramos un usuario, lo asignamos a la instancia 'usuario'
	                usuario = new UsuarioCliente();
	                usuario.setIdCliente(rs.getInt("idCliente"));
	                usuario.setUsuario(rs.getString("usuario"));
	                usuario.setPassword(rs.getString("pass"));
	                usuario.setAdmin(rs.getBoolean("admin"));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener el usuario por ID: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return usuario;
	}

	public Usuario obtenerUsuario(int idCliente) {
		Usuario usuario = new Usuario();
	    final String query = "{CALL ObtenerUsuario(?)}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query)) {
	        cst.setInt(1, idCliente);
	        ResultSet rs = cst.executeQuery();
	        
            // Evitar NullPointerException creando instancias si son necesarias
            if (usuario.getCliente() == null) {
            	usuario.setCliente(new Cliente());
            }
            usuario.getCliente().setId(rs.getInt("IdCliente"));         
            usuario.setUsuario(rs.getString("Usuario"));
            usuario.setPassword(rs.getString("Pass"));           

	    } catch (Exception e) {
	    	System.err.println("Error al obtener al usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
		
		return usuario;
	}


	@Override
	public boolean eliminarUsuario(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
