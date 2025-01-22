package datos;

import java.util.List;
import entidad.Cliente;

public interface ClienteDao {
	public boolean modificarCliente(Cliente cliente);
	public List<Cliente> ObtenerClientes();
	public boolean agregarOmodifcarCliente(Cliente cliente);
}
