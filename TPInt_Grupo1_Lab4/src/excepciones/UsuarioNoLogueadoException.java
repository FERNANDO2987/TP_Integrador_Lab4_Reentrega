package excepciones;

public class UsuarioNoLogueadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioNoLogueadoException(){
		
	}

	@Override
	public String getMessage() {
		return "Usuario no logueado";
	}
	
}