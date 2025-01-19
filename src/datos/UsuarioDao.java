package datos;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	public boolean agregarUsuarioCliente(Usuario usuario);
	public boolean comprobarUsuarioLogeo(Usuario usuario);
	public List<Usuario> leerTodosLosClientes();
	// el otro archivo
	public Usuario obtenerUsuarioPorId(int id);
	public List<Usuario> listarUsuarios();
	public boolean insertarOActualizarUsuario(Usuario usuario);
	public boolean eliminarUsuario(int id);
	
}
