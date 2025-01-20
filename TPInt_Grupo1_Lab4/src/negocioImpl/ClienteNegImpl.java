package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg {
	
	
private ClienteDao clienteDao = new ClienteDaoImpl();
	
	public ClienteNegImpl(ClienteDao usuarioDao )
	{
		this.clienteDao = usuarioDao;
	}
	
	public ClienteNegImpl()
	{
		
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

	    if (cliente.getDni() == null || !cliente.getDni().matches("\\d{7,8}")) {
	        throw new IllegalArgumentException("El DNI debe ser un número válido de 7 u 8 dígitos.");
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




}
