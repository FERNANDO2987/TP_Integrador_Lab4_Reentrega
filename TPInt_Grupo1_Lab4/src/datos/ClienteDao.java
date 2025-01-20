package datos;

import java.util.List;
import entidad.Cliente;

public interface ClienteDao {
	public Cliente leerUnCliente(int id);
	public List<Cliente> ObtenerClientes();
	public boolean modificarCliente(Cliente cliente);
}
