package datosImpl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.SplittableRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import datos.ClienteDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

import entidad.UsuarioCliente;

public class ClienteDaoImpl implements ClienteDao {
	private Conexion cn;
	public ClienteDaoImpl() {
		cn = new Conexion();
	}

	
	private static void enviarCorreo(String destinatario, String asunto, String mensaje, String remitente, String password) {
	        // Configuración del servidor SMTP
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP
	        props.put("mail.smtp.port", "587"); // Puerto SMTP
	        props.put("mail.smtp.auth", "true"); // Autenticación
	        props.put("mail.smtp.starttls.enable", "true"); // Conexión segura

	        // Credenciales del remitente
	        final String remitente = "programadoressomos404@gmail.com"; // Cambiar por tu correo
	        final String password = "opwlysytyzcqgagv"; // Cambiar por tu contraseña

	        // Crear la sesión
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(remitente, password);
	            }
	        });

	        try {
	            // Crear el mensaje
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(remitente));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	            message.setSubject(asunto);
	            message.setText(mensaje);

	            // Enviar el mensaje
	            Transport.send(message);
	            System.out.println("Correo enviado exitosamente a " + destinatario);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }


	
	@Override
	public List<Cliente> ObtenerClientes() {
	    List<Cliente> listaClientes = new ArrayList<>();
	    final String query = "{CALL ObtenerClientes()}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("IdCliente"));
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cliente.setSexo(rs.getString("Sexo"));

	            // Evitar NullPointerException creando instancias si son necesarias
	            if (cliente.getPaisNacimiento() == null) {
	                cliente.setPaisNacimiento(new Pais());
	            }
	            cliente.getPaisNacimiento().setNombre(rs.getString("Pais"));

	            if (cliente.getProvincia() == null) {
	                cliente.setProvincia(new Provincia());
	            }
	            cliente.getProvincia().setNombre(rs.getString("Provincia"));

	            if (cliente.getLocalidad() == null) {
	                cliente.setLocalidad(new Localidad());
	            }
	            cliente.getLocalidad().setNombre(rs.getString("Localidad"));

	            cliente.setFechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
	            cliente.setDireccion(rs.getString("Direccion"));
	            cliente.setCorreo(rs.getString("Correo"));
	            cliente.setTelefono(rs.getString("Telefono"));

	            listaClientes.add(cliente);
	        }
	    } catch (Exception e) {
	        // Usar un sistema de logging para registrar el error
	        System.err.println("Error al obtener la lista de clientes: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return listaClientes;
	}


	@Override
	public boolean agregarOmodifcarCliente(Cliente cliente) {
	    boolean resultado = false;
	    final String query = "{CALL AgregarOModifcarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

	    System.out.println("Conectando a la base de datos...");
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query)) {
	        // Verificación de parámetros
	        if (cliente.getId() != 0) {
	            System.out.println("Cliente con ID existente: " + cliente.getId());
	            cst.setInt(1, cliente.getId());
	        } else {
	            System.out.println("Nuevo cliente, ID es 0 (pasando NULL).");
	            cst.setNull(1, java.sql.Types.INTEGER); // Pasar NULL si es un nuevo cliente
	        }

	        System.out.println("Estableciendo los otros parámetros...");
	        // Establecer los otros parámetros de entrada
	        cst.setString(2, cliente.getDni());
	        cst.setString(3, cliente.getCuil());
	        cst.setString(4, cliente.getNombre());
	        cst.setString(5, cliente.getApellido());
	        cst.setString(6, cliente.getSexo());
	        cst.setInt(7, cliente.getPaisNacimiento().getId());
	        cst.setDate(8, Date.valueOf(cliente.getFechaNacimiento()));
	        cst.setString(9, cliente.getDireccion());
	        cst.setInt(10, cliente.getLocalidad().getId());
	        cst.setInt(11, cliente.getProvincia().getId());
	        cst.setString(12, cliente.getCorreo());
	        cst.setString(13, cliente.getTelefono());

	        // Registrar el parámetro de salida para obtener el ID del cliente generado
	        cst.registerOutParameter(14, java.sql.Types.INTEGER);

	        // Ejecutar la consulta
	        int filasAfectadas = cst.executeUpdate();
	        int idGenerado = cst.getInt(14); // Obtener el ID generado del parámetro de salida
	        System.out.println("Resultado de la ejecución del cliente: " + (filasAfectadas > 0 ? "Éxito" : "Fallo"));
	        System.out.println("ID generado del cliente: " + idGenerado);

	        // Verificar si la inserción fue exitosa y si el ID generado es válido
	        if (filasAfectadas > 0 && idGenerado > 0) {
	            // Código para crear el usuario solo si el cliente fue agregado correctamente
	            String nombreUsuario = generarNombreUsuario(cliente.getNombre(), cliente.getApellido());
	            String contrasena = generarContrasenaAleatoria();
	            UsuarioCliente usuario = new UsuarioCliente();
	            usuario.setIdCliente(idGenerado); // Asignar el ID generado
	            usuario.setUsuario(nombreUsuario);
	            usuario.setPassword(contrasena);
	            usuario.setAdmin(false); // Establecer si es admin o no

	            // Llamar a agregarUsuarioCliente solo si el cliente fue agregado exitosamente
	            resultado = agregarUsuarioCliente(usuario);
	            
	         // Enviar el correo después de agregar al cliente
	            String asunto = "Bienvenido a nuestro Sistema Bancario";
	            String mensaje = String.format("Hola %s %s, gracias por registrarte. ¡Bienvenido!", cliente.getNombre(), cliente.getApellido());
	            enviarCorreo(cliente.getCorreo(), asunto, mensaje,nombreUsuario,contrasena);
	            
	            System.out.println("Resultado de la ejecución del usuario: " + (resultado ? "Éxito" : "Fallo"));
	        } else {
	            System.out.println("No se pudo agregar o modificar el cliente, no se insertó correctamente.");
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al agregar o modificar el cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return resultado;
	}



	
	// Método para generar el nombre de usuario
	private String generarNombreUsuario(String nombre, String apellido) {
	    if (nombre == null || apellido == null || nombre.isEmpty() || apellido.isEmpty()) {
	        return "";
	    }
	    return (nombre.substring(0, 1) + apellido).toLowerCase();
	}
	
	// Método para generar una contraseña aleatoria de 4 caracteres
	private String generarContrasenaAleatoria() {
	    SplittableRandom random = new SplittableRandom();
	    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder contrasena = new StringBuilder(4);
	    
	    for (int i = 0; i < 4; i++) {
	        int indice = random.nextInt(caracteres.length());
	        contrasena.append(caracteres.charAt(indice));
	    }
	    
	    return contrasena.toString();
	}





	@Override
	public boolean agregarUsuarioCliente(UsuarioCliente usuario) {
	    boolean success = false;
	    String query = "{CALL InsertarOActualizarUsuario(?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

	    try {
	        cn.Open();

	        // Preparar la llamada al procedimiento almacenado
	        try (CallableStatement cst = cn.connection.prepareCall(query)) {
	            // Asignación de parámetros
	            if (usuario.getIdCliente() > 0) { // Verificación si idCliente es válido
	                cst.setInt(1, usuario.getIdCliente()); // p_id_cliente
	            } else {
	                cst.setNull(1, java.sql.Types.INTEGER); // En caso de cliente nulo o inválido
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
	    	 cn.close();
	    }

	    return success;
	}



	
	public boolean existeDni(String dni) {
	    boolean existe = false;
	    final String query = "SELECT COUNT(1) FROM clientes WHERE dni = ?";  

	    cn.Open();
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, dni);  // Asignamos el DNI al parámetro de la consulta

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                existe = rs.getInt(1) > 0;  // Si el resultado es mayor que 0, significa que el DNI existe
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar existencia del DNI: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return existe;
	}

	
	public boolean existeCuil(String cuil) {
	    boolean existe = false;
	    final String query = "SELECT COUNT(1) FROM clientes WHERE cuil = ?";  

	    cn.Open();
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, cuil);  // Asignamos el CUIL al parámetro de la consulta

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                existe = rs.getInt(1) > 0;  // Si el resultado es mayor que 0, significa que el DNI existe
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar existencia del CUIL: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return existe;
	}




	@Override
	public boolean existeCorreo(String correo) {
		  boolean existe = false;
		    final String query = "SELECT COUNT(1) FROM clientes WHERE correo = ?";  

		    cn.Open();
		    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
		        pst.setString(1, correo);  // Asignamos el CUIL al parámetro de la consulta

		        try (ResultSet rs = pst.executeQuery()) {
		            if (rs.next()) {
		                existe = rs.getInt(1) > 0;  // Si el resultado es mayor que 0, significa que el DNI existe
		            }
		        }
		    } catch (SQLException e) {
		        System.err.println("Error al verificar existencia del CORREO: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		    return existe;
	}




	@Override
	public boolean existeTelefono(String telefono) {
		  boolean existe = false;
		    final String query = "SELECT COUNT(1) FROM clientes WHERE telefono = ?";  

		    cn.Open();
		    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
		        pst.setString(1, telefono);  // Asignamos el CUIL al parámetro de la consulta

		        try (ResultSet rs = pst.executeQuery()) {
		            if (rs.next()) {
		                existe = rs.getInt(1) > 0;  // Si el resultado es mayor que 0, significa que el DNI existe
		            }
		        }
		    } catch (SQLException e) {
		        System.err.println("Error al verificar existencia del TELEFONO: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		    return existe;
	}







	@Override
	public boolean existeCliente(int idCliente) {
		  boolean existe = false;
		    final String query = "SELECT COUNT(1) FROM clientes WHERE id = ?";  

		    cn.Open();
		    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
		        pst.setInt(1, idCliente);  // Asignamos el CUIL al parámetro de la consulta

		        try (ResultSet rs = pst.executeQuery()) {
		            if (rs.next()) {
		                existe = rs.getInt(1) > 0;  // Si el resultado es mayor que 0, significa que el DNI existe
		            }
		        }
		    } catch (SQLException e) {
		        System.err.println("Error al verificar existencia del CLIENTE " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		    return existe;
	}




	@Override
	public boolean eliminarCliente(int idCliente) {
		  boolean estado = true;
		    cn.Open(); 

		   
		    String query = "{CALL EliminarCliente(?)}"; 

		    try (CallableStatement stmt = cn.connection.prepareCall(query)) {
		    
		        stmt.setInt(1, idCliente);

		     
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        
		        estado = false; 
		        e.printStackTrace();
		    } finally {
		      
		        cn.close();
		    }

		    return estado; 
	}




	@Override
	public boolean ModificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	
	



}
