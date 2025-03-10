package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidad.Usuario;
import excepciones.UsuarioNoLogueadoException;
import negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg {
	
	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	public UsuarioNegImpl(UsuarioDao usuarioDao )
	{
		this.usuarioDao = usuarioDao;
	}
	
	public UsuarioNegImpl()
	{
		
	}
	
	public Usuario iniciarSesion(String nombreUsuario, String contrasena) throws UsuarioNoLogueadoException {
	    if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || contrasena == null || contrasena.trim().isEmpty()) {
	        throw new UsuarioNoLogueadoException("Usuario o contrase�a no pueden ser vac�os.");
	    }

	    Usuario usuario = new Usuario();
	    usuario.setUsuario(nombreUsuario);
	    usuario.setPassword(contrasena);

	    Usuario usuarioValido = usuarioDao.loguear(usuario);

	    if (usuarioValido != null) {
	        // Mostrar el valor de admin en la consola
	        System.out.println("Usuario: " + usuarioValido.getUsuario() + " | Admin: " + usuarioValido.isAdmin());
	        return usuarioValido;
	    } else {
	        // Lanzamos la excepci�n cuando el usuario no existe
	        throw new UsuarioNoLogueadoException("Usuario o contrase�a incorrectos.");
	    }
	}



	@Override
	public boolean AgregarUsuario(Usuario usuario) {
	    if (usuario == null) {
	        System.err.println("El usuario no puede ser nulo.");
	        return false;
	    }
	    if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
	        System.err.println("El nombre de usuario no puede estar vac�o.");
	        return false;
	    }
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        System.err.println("La contrase�a no puede estar vac�a.");
	        return false;
	    }
	    return usuarioDao.insertarOActualizarUsuario(usuario);
	}
	
	
	
	@Override
	public boolean ModificarUsuario(Usuario usuario) {
	    if (usuario == null) {
	        System.err.println("El usuario no puede ser nulo.");
	        return false;
	    }
	    if (usuario.getId() <= 0) {
	        System.err.println("El ID del usuario no es v�lido.");
	        return false;
	    }
	    if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
	        System.err.println("El nombre de usuario no puede estar vac�o.");
	        return false;
	    }
	    if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
	        System.err.println("La contrase�a no puede estar vac�a.");
	        return false;
	    }
	    return usuarioDao.insertarOActualizarUsuario(usuario);
	}

	@Override
	public ArrayList<Usuario> ListarUsuarios() {
	    List<Usuario> usuarios = usuarioDao.listarUsuarios();
	    if (usuarios == null || usuarios.isEmpty()) {
	        System.err.println("No se encontraron usuarios.");
	        return new ArrayList<>();
	    }
	    System.out.println("Usuarios encontrados: " + usuarios.size());
	    return new ArrayList<>(usuarios);
	}


	@Override
	public boolean EliminarUsuario(int idUser) {
	    if (idUser <= 0) {
	        System.err.println("El ID del usuario no es v�lido.");
	        return false;
	    }
	    return usuarioDao.eliminarUsuario(idUser);
	}




}
