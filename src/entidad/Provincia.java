package entidad;

public class Provincia {
	private int id;
	private String nombre;
	
	
	// Constructor vacío
    public Provincia() {}
    
	// Constructor con parámetros
	public Provincia(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	// ToString
	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nombre=" + nombre + "]";
	}
}
