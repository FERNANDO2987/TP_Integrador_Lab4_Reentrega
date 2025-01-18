package negocio;

import java.util.ArrayList;


import entidad.Cliente;

public interface ClienteNeg {

	public Cliente obtenerClientePorId(int id);
	public ArrayList<Cliente> ObtenerClientes();
	public boolean updateCliente(Cliente cliente);
	
}
