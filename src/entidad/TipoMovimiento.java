package entidad;

public class TipoMovimiento {
	private int id;
    private String descripcion;
    
    
    // Constructor vacío
    public TipoMovimiento() {}

    // Constructor con parámetros
	public TipoMovimiento(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	
	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	// ToString
	@Override
	public String toString() {
		return "TipoMovimiento [id=" + id + ", descripcion=" + descripcion + "]";
	}    
    
	
}
