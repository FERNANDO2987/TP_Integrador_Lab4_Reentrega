package negocioImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.ClienteDao;
import datos.UsuarioDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg {
	
	
private ClienteDao clienteDao = new ClienteDaoImpl();
private UsuarioDao usuarioDao = new  UsuarioDaoImpl();
	
	public ClienteNegImpl(ClienteDao clienteDao, UsuarioDao usuarioDao )
	{
		this.clienteDao = clienteDao;
		this.usuarioDao = usuarioDao;
	}
	
	public ClienteNegImpl()
	{
		
	}
	

	@Override
	public boolean EliminarCliente(int idCliente) {
	    if (idCliente <= 0) {
	        throw new IllegalArgumentException("El ID del cliente debe ser un número positivo.");
	    }

	    try {
	        if (!clienteDao.existeCliente(idCliente)) {
	            System.out.println("El cliente con ID " + idCliente + " no existe.");
	            return false;
	        }

	        boolean eliminado = clienteDao.eliminarCliente(idCliente);
	        System.out.println(eliminado ? "Cliente con ID " + idCliente + " eliminado correctamente." : 
	                                      "No se pudo eliminar el cliente con ID: " + idCliente);

	        return eliminado;

	    } catch (Exception e) {
	        System.out.println("Error al eliminar el cliente con ID " + idCliente + ": " + e.getMessage());
	        e.printStackTrace(); // Para depuración
	        return false;
	    }
	}




	@Override
	public boolean agregarCliente(Cliente cliente) {
		   if (cliente == null) {
		        throw new IllegalArgumentException("El cliente no puede ser nulo.");
		    }

		    if (cliente.getNombre() == null || !cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
		        throw new IllegalArgumentException("El nombre del cliente solo debe contener letras y espacios.");
		    }

		    if (cliente.getApellido() == null || !cliente.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
		        throw new IllegalArgumentException("El apellido del cliente solo debe contener letras y espacios.");
		    }

		    if (cliente.getDni() == null || !cliente.getDni().matches("\\d{8}")) {
		        throw new IllegalArgumentException("El DNI debe ser un número válido 8 dígitos.");
		    }

		    if (cliente.getCuil() == null || !cliente.getCuil().matches("^\\d{2}-\\d{8}-\\d$")) {
		        throw new IllegalArgumentException("El CUIL debe ser válido (formato XX-XXXXXXXX-X).");
		    }

		    if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(java.time.LocalDate.now().minusYears(18))) {
		        throw new IllegalArgumentException("El cliente debe ser mayor de 18 años.");
		    }

		    if (cliente.getCorreo() == null || !cliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
		        throw new IllegalArgumentException("El correo del cliente es inválido.");
		    }

		    if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{10,15}")) {
		        throw new IllegalArgumentException("El teléfono debe ser un número válido entre 10 y 15 dígitos.");
		    }

		    if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {
		        throw new IllegalArgumentException("La dirección del cliente es obligatoria.");
		    }

		    if (cliente.getPaisNacimiento() == null || cliente.getPaisNacimiento().getId() <= 0) {
		        throw new IllegalArgumentException("El país de nacimiento debe ser válido.");
		    }

		    if (cliente.getLocalidad() == null || cliente.getLocalidad().getId() <= 0) {
		        throw new IllegalArgumentException("La localidad debe ser válida.");
		    }

		    if (cliente.getProvincia() == null || cliente.getProvincia().getId() <= 0) {
		        throw new IllegalArgumentException("La provincia debe ser válida.");
		    }

	    return clienteDao.agregarOmodifcarCliente(cliente);
	}

	
	

	
	@Override
	public boolean AgregarUsuario(Usuario usuario) {
	    if (usuario == null) {
	        System.err.println("El usuario no puede ser nulo.");
	        return false;
	    }
	    if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
	        System.err.println("El nombre de usuario no puede estar vacío.");
	        return false;
	    }
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        System.err.println("La contraseña no puede estar vacía.");
	        return false;
	    }
	    return usuarioDao.insertarOActualizarUsuario(usuario);
	}
	
	
	@Override
	public ArrayList<Cliente> ListarClientes() {
	    List<Cliente> clientes = clienteDao.ObtenerClientes();

        if (clientes == null || clientes.isEmpty()) {
            throw new RuntimeException("No se encontraron clientes.");
        }

        return (ArrayList<Cliente>) clientes;
	}

	
	@Override
	public boolean ExisteDni(String dni) {
	    if (dni == null || !dni.matches("\\d{8}")) {
	        throw new IllegalArgumentException("El DNI debe ser un número válido de 8 dígitos.");
	    }
	    System.out.println("Verificando DNI: " + dni);  // Verificación para ver si el DNI es correcto
	    return clienteDao.existeDni(dni);
	}
	
	
	@Override
	public boolean ExisteCuil(String cuil) {
		   if (cuil == null || !cuil.matches("^\\d{2}-\\d{8}-\\d$")) {
		        throw new IllegalArgumentException("El CUIL debe ser válido (formato XX-XXXXXXXX-X).");
		    }
		    System.out.println("Verificando CUIL: " + cuil);  // Verificación para ver si el Cuil es correcto
		    return clienteDao.existeCuil(cuil);
	}

	@Override
	public boolean ExisteCorreo(String correo) {
		   if (correo == null || !correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
		        throw new IllegalArgumentException("El correo del cliente es inválido.");
		    }
		    System.out.println("Verificando CORREO: " + correo);  // Verificación para ver si el Correo es correcto
		    return clienteDao.existeCorreo(correo);	
		
	}
	

	@Override
	public boolean ExisteTelefono(String telefono) {
		   if (telefono == null || !telefono.matches("\\d{10,15}")) {
		        throw new IllegalArgumentException("El teléfono debe ser un número válido entre 10 y 15 dígitos.");
		    }
		    System.out.println("Verificando TELEFONO: " + telefono);  // Verificación para ver si el telefono es correcto
		    return clienteDao.existeTelefono(telefono);	
	}


	
	@Override  
	public Map<String, String> AgregarCliente(Cliente cliente) {  
	    Map<String, String> errores = new HashMap<>();  

	    if (cliente == null) {  
	        errores.put("cliente", "El cliente no puede ser nulo.");  
	        return errores;  
	    }  

	    if (cliente.getNombre() == null || !cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {  
	        errores.put("nombre", "El nombre solo debe contener letras y espacios.");  
	    }  

	    if (cliente.getApellido() == null || !cliente.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {  
	        errores.put("apellido", "El apellido solo debe contener letras y espacios.");  
	    }  

	    // Validación del DNI
	    if (cliente.getDni() == null || !cliente.getDni().matches("\\d{8}")) {  
	        errores.put("dni", "El DNI debe ser un número válido de 8 dígitos.");  
	    } else {
	        // Verificación si el DNI ya existe en la base de datos
	        if (ExisteDni(cliente.getDni())) {
	            errores.put("dni", "El DNI ya está registrado en el sistema.");
	        }
	    }

	    
	    //Validacion Cuil
	    if (cliente.getCuil() == null || !cliente.getCuil().matches("^\\d{2}-\\d{8}-\\d$")) {  
	        errores.put("cuil", "El CUIL debe ser válido (formato XX-XXXXXXXX-X).");  
	    } else {
	        // Verificación si el CUIL ya existe en la base de datos
	        if (ExisteCuil(cliente.getCuil())) {
	            errores.put("cuil", "El CUIL ya está registrado en el sistema.");
	        }
	    }

	    if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(java.time.LocalDate.now().minusYears(18))) {  
	        errores.put("fechaNacimiento", "El cliente debe ser mayor de 18 años.");  
	    }  

	 
	    //Validacion correo
	    if (cliente.getCorreo() == null || !cliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {  
	        errores.put("correo", "El correo del cliente es inválido.");  
	    } else {
	        // Verificación si el CORREO ya existe en la base de datos
	        if (ExisteCorreo(cliente.getCorreo())) {
	            errores.put("correo", "El CORREO ya está registrado en el sistema.");
	        }
	    }

	   
	    
	    //Validacion correo
	    if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{10,15}")) {  
	        errores.put("telefono", "El teléfono debe ser un número válido entre 10 y 15 dígitos.");  
	    } else {
	        // Verificación si el CORREO ya existe en la base de datos
	        if (ExisteTelefono(cliente.getTelefono())) {
	            errores.put("telefono", "El TELEFONO ya está registrado en el sistema.");
	        }
	    }

	    if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {  
	        errores.put("direccion", "La dirección del cliente es obligatoria.");  
	    }  

	    if (cliente.getPaisNacimiento() == null || cliente.getPaisNacimiento().getId() <= 0) {  
	        errores.put("paisNacimiento", "El país de nacimiento debe ser válido.");  
	    }  

	    if (cliente.getLocalidad() == null || cliente.getLocalidad().getId() <= 0) {  
	        errores.put("localidad", "La localidad debe ser válida.");  
	    }  

	    if (cliente.getProvincia() == null || cliente.getProvincia().getId() <= 0) {  
	        errores.put("provincia", "La provincia debe ser válida.");  
	    }  

	    if (!errores.isEmpty()) {  
	        return errores;  // Si hay errores, los devolvemos sin guardar el cliente.  
	    }  

	    // Si no hay errores, procedemos a guardar el cliente.  
	    boolean exito = clienteDao.agregarOmodifcarCliente(cliente);  
	    if (exito) {  
	        limpiarCliente(cliente);  
	    } else {  
	        errores.put("general", "Error al guardar el cliente en la base de datos. Intente nuevamente más tarde.");  
	    }  
	    return errores;  
	}


	private void limpiarCliente(Cliente cliente) {  
	    cliente.setNombre("");  
	    cliente.setApellido("");  
	    cliente.setDni("");  
	    cliente.setCuil("");  
	    cliente.setFechaNacimiento(null);  // Para fechas, mantener null
	    cliente.setCorreo("");  
	    cliente.setTelefono("");  
	    cliente.setDireccion("");  
	    cliente.setPaisNacimiento(null);  
	    cliente.setLocalidad(null);  
	    cliente.setProvincia(null);  
	}

	@Override
	public Map<String, String> ModificarCliente(Cliente cliente) {
		  Map<String, String> errores = new HashMap<>();  

		    if (cliente == null) {  
		        errores.put("cliente", "El cliente no puede ser nulo.");  
		        return errores;  
		    }  

		    if (cliente.getNombre() == null || !cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {  
		        errores.put("nombre", "El nombre solo debe contener letras y espacios.");  
		    }  

		    if (cliente.getApellido() == null || !cliente.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {  
		        errores.put("apellido", "El apellido solo debe contener letras y espacios.");  
		    }  


		    
		  

		    if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(java.time.LocalDate.now().minusYears(18))) {  
		        errores.put("fechaNacimiento", "El cliente debe ser mayor de 18 años.");  
		    }  

		 
		    //Validacion correo
	

		   
		    
		

		    if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {  
		        errores.put("direccion", "La dirección del cliente es obligatoria.");  
		    }  

		    if (cliente.getPaisNacimiento() == null || cliente.getPaisNacimiento().getId() <= 0) {  
		        errores.put("paisNacimiento", "El país de nacimiento debe ser válido.");  
		    }  

		    if (cliente.getLocalidad() == null || cliente.getLocalidad().getId() <= 0) {  
		        errores.put("localidad", "La localidad debe ser válida.");  
		    }  

		    if (cliente.getProvincia() == null || cliente.getProvincia().getId() <= 0) {  
		        errores.put("provincia", "La provincia debe ser válida.");  
		    }  

		    if (!errores.isEmpty()) {  
		        return errores;  // Si hay errores, los devolvemos sin guardar el cliente.  
		    }  

		    // Si no hay errores, procedemos a guardar el cliente.  
		    boolean exito = clienteDao.modificarCliente(cliente);  
		    if (exito) {  
		        limpiarCliente(cliente);  
		    } else {  
		        errores.put("general", "Error al Modificar el cliente en la base de datos. Intente nuevamente más tarde.");  
		    }  
		    return errores; 
	}







}
