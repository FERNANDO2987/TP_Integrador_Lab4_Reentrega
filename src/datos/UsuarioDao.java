package datos;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	public boolean agregarUsuarioCliente(Usuario usuario);
	public boolean comprobarUsuarioLogeo(Usuario usuario);
	public List<Usuario> leerTodosLosClientes();
	
}
