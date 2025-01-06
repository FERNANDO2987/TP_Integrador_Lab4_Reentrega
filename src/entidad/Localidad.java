package entidad;

public class Localidad {
	private int id;
	private String nombre;
	
	
	// Constructor vacío
    public Localidad() {}
	
	// Constructor con parámetros
	public Localidad(int id, String nombre) {
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
		return "Localidad [id=" + id + ", nombre=" + nombre + "]";
	}
}
