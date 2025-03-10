package negocio;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioNeg {
	
	public boolean AgregarUsuario(Usuario usuario);
	public boolean ModificarUsuario(Usuario usuario);
	public ArrayList<Usuario> ListarUsuarios();
	public boolean EliminarUsuario(int idUser);
	public Usuario iniciarSesion(String nombreUsuario, String contrasena);

}
