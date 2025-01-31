package negocio;

import java.util.ArrayList;
import java.util.Map;

import entidad.Cliente;
import entidad.Usuario;

public interface ClienteNeg {

	public ArrayList<Cliente> ListarClientes();
	public Cliente obtenerCliente(int idCliente);
	public boolean agregarCliente(Cliente cliente);
	public boolean EliminarCliente(int idCliente);
	public boolean AgregarUsuario(Usuario usuario);
	 public Map<String, String> AgregarCliente(Cliente cliente);
	 public boolean ExisteDni(String dni);
	 public boolean ExisteCuil(String cuil);
	 public boolean ExisteCorreo(String correo);
	 public boolean ExisteTelefono(String telefono);
	
}
