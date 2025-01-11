package entidad;

public class Pais {
	private int id;
    private String nombre;
    
    
    // Constructor vacío
    public Pais() {}
    
    // Constructor con parámetros
	public Pais(int id, String nombre) {
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
		return "Pais [id=" + id + ", nombre=" + nombre + "]";
	}
}
