package negocio;

import java.util.ArrayList;


import entidad.Cliente;

public interface ClienteNeg {

	public ArrayList<Cliente> ListarClientes();
	public boolean agregarCliente(Cliente cliente);
	
}
