package datos;

import java.util.List;
import entidad.Cliente;

public interface ClienteDao {

	public List<Cliente> ObtenerClientes();
	public boolean agregarOmodifcarCliente(Cliente cliente);
}
