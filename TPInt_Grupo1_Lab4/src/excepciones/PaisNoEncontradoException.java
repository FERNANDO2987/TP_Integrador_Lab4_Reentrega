package excepciones;

public class PaisNoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No se pudo encontrar el pais";
	}
}
