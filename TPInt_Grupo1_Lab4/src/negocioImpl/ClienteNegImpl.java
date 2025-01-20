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
	public Cliente obtenerClientePorId(int id) {
		
	     if (id <= 0) {
	            throw new IllegalArgumentException("El ID del cliente debe ser positivo.");
	        }

	        Cliente cliente = clienteDao.leerUnCliente(id);

	        if (cliente == null) {
	            throw new RuntimeException("Cliente no encontrado con ID: " + id);
	        }

	        return cliente;
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
	public boolean updateCliente(Cliente cliente) {
	    if (cliente == null) {
	        throw new IllegalArgumentException("El cliente no puede ser nulo.");
	    }

	    if (cliente.getId() <= 0) {
	        throw new IllegalArgumentException("El ID del cliente debe ser v�lido.");
	    }

	    if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
	        throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
	    }

	    if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
	        throw new IllegalArgumentException("El apellido del cliente es obligatorio.");
	    }

	    if (cliente.getDni() == null || !cliente.getDni().matches("\\d{7,8}")) {
	        throw new IllegalArgumentException("El DNI del cliente debe ser un n�mero v�lido de 7 u 8 d�gitos.");
	    }

	    if (cliente.getCuil() == null || !cliente.getCuil().matches("\\d{2}-\\d{7,8}-\\d")) {
	        throw new IllegalArgumentException("El CUIL del cliente debe ser v�lido (formato XX-XXXXXXXX-X).");
	    }

	    if (cliente.getFechaNacimiento() == null || cliente.getFechaNacimiento().isAfter(java.time.LocalDate.now())) {
	        throw new IllegalArgumentException("La fecha de nacimiento del cliente debe ser v�lida y no estar en el futuro.");
	    }

	    if (cliente.getCorreo() == null || !cliente.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	        throw new IllegalArgumentException("El correo del cliente es inv�lido.");
	    }

	    if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{10,15}")) {
	        throw new IllegalArgumentException("El tel�fono del cliente debe ser un n�mero v�lido entre 10 y 15 d�gitos.");
	    }

	    if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {
	        throw new IllegalArgumentException("La direcci�n del cliente es obligatoria.");
	    }

	    if (cliente.getPaisNacimiento() == null || cliente.getPaisNacimiento().getId() <= 0) {
	        throw new IllegalArgumentException("El pa�s de nacimiento del cliente debe ser v�lido.");
	    }

	    if (cliente.getLocalidad() == null || cliente.getLocalidad().getId() <= 0) {
	        throw new IllegalArgumentException("La localidad del cliente debe ser v�lida.");
	    }

	    if (cliente.getProvincia() == null || cliente.getProvincia().getId() <= 0) {
	        throw new IllegalArgumentException("La provincia del cliente debe ser v�lida.");
	    }

	    // Validaci�n exitosa, se intenta actualizar el cliente
	    return clienteDao.modificarCliente(cliente);
	}


}
