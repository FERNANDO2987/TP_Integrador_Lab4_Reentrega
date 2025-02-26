package datos;

import java.util.List;
import entidad.Cliente;
import entidad.Usuario;
import entidad.UsuarioCliente;

public interface ClienteDao {
	public List<Cliente> ObtenerClientes();
	public Cliente ObtenerClienteXUsuarioPass(Usuario usuario);
	public boolean agregarOmodifcarCliente(Cliente cliente);
	public boolean modificarCliente(Cliente cliente);
	public boolean eliminarCliente(int idCliente);
	public boolean agregarUsuarioCliente(UsuarioCliente usuarioCliente);
	public boolean existeDni(String dni);
	public boolean existeCuil(String cuil);
	public boolean existeCorreo(String correo);
	public boolean existeTelefono(String telefono);
	public boolean existeCliente(int idCliente);

}
