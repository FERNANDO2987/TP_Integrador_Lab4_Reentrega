package datos;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {

	public Usuario obtenerUsuarioPorId(int id);
	public List<Usuario> listarUsuarios();
	public boolean insertarOActualizarUsuario(Usuario usuario);
	public boolean eliminarUsuario(int id);
	
}
