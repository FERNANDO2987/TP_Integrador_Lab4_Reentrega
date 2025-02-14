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
import entidad.Usuario;
import entidad.UsuarioCliente;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Conexion cn;
	public UsuarioDaoImpl() {
		cn = new Conexion();
	}
	
	@Override
	public Usuario loguear(Usuario usuario) {
	       Usuario usuarioBD = null;
	       ResultSet rs = null;
	       cn = new Conexion();
	       cn.Open();
	       String query = "{CALL SP_ValidarUsuario(?, ?)}";
	       try (CallableStatement stmt = cn.connection.prepareCall(query)) {
	           stmt.setString(1, usuario.getUsuario());
	           stmt.setString(2, usuario.getPassword()); 
	           rs = stmt.executeQuery();
	           if (rs != null && rs.next()) {
	        	   usuarioBD = new Usuario();
	        	   usuarioBD.getCliente().setId(rs.getInt("id"));
	        	   usuarioBD.setUsuario(rs.getString("usuario"));
	        	   usuarioBD.getCliente().setNombre(rs.getString("nombre"));
	        	   usuarioBD.getCliente().setApellido(rs.getString("apellido"));
	        	   usuarioBD.setAdmin(rs.getBoolean("admin"));
	        	   usuarioBD.getCliente().setTelefono(rs.getString("telefono"));
	        	   usuarioBD.getCliente().setCuil(rs.getString("cuil"));
	        	   usuarioBD.getCliente().getPaisNacimiento().setId(rs.getInt("id_pais"));
	        	   usuarioBD.getCliente().setDni(rs.getString("dni"));
	        	   usuarioBD.getCliente().setCorreo(rs.getString("correo"));
	        	   usuarioBD.getCliente().setDireccion(rs.getString("direccion"));
	        	   usuarioBD.getCliente().setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
	        	   usuarioBD.getCliente().setSexo(rs.getString("sexo"));
	        	   usuarioBD.getCliente().getPaisNacimiento().setId(rs.getInt("id_pais"));
	        	   usuarioBD.getCliente().getPaisNacimiento().setNombre(rs.getString("nombre_pais"));
	        	   usuarioBD.getCliente().getProvincia().setId(rs.getInt("id_provincia"));
	        	   usuarioBD.getCliente().getProvincia().setNombre(rs.getString("nombre_provincia"));
	        	   usuarioBD.getCliente().getLocalidad().setId(rs.getInt("id_localidad"));
	        	   usuarioBD.getCliente().getLocalidad().setNombre(rs.getString("nombre_localidad"));
		            
		            System.out.println(usuarioBD.toString());
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (rs != null) {
	                   rs.close();
	               }
	               cn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	       return usuarioBD;
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
	public UsuarioCliente obtenerUsuarioPorIdCliente(int idCliente) {
	    UsuarioCliente usuario = null;
	    String query = "SELECT * FROM UsuarioCliente WHERE idCliente = ?"; // Consulta SQL para obtener el usuario

	    // Preparar la conexi�n y consulta
	    try (PreparedStatement ps = cn.connection.prepareStatement(query)) {
	        // Establecer el par�metro para la consulta
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




	@Override
	public boolean eliminarUsuario(int id) {
		  boolean estado = true;
		    cn.Open(); 

		   
		    String query = "{CALL EliminarUsuarioYCliente(?)}"; 

		    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
		    
		        stmt.setInt(1, id);

		     
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        
		        estado = false; 
		        e.printStackTrace();
		    } finally {
		      
		        cn.close();
		    }

		    return estado; 
	}

}
