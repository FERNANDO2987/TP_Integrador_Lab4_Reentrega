package entidad;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private int dni;
    private int cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private Pais paisNacimiento;
    private LocalDate fechaNacimiento;
    private String direccion;
    private Localidad localidad;
    private Provincia provincia;
    private String correo;
    private String telefono;
    
    private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
    
    
    // Constructor   
	public Cliente(int id, int dni, int cuil, String nombre, String apellido, String sexo, Pais paisNacimiento,
			LocalDate fechaNacimiento, String direccion, Localidad localidad, Provincia provincia, String correo,
			String telefono, LocalDate createDate, boolean deleted, LocalDate deleteDate) {
		super();
		this.id = id;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.paisNacimiento = paisNacimiento;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correo = correo;
		this.telefono = telefono;
		this.createDate = createDate;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}

	
	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getCuil() {
		return cuil;
	}
	public void setCuil(int cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Pais getPaisNacimiento() {
		return paisNacimiento;
	}
	public void setPaisNacimiento(Pais paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public LocalDate getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(LocalDate deleteDate) {
		this.deleteDate = deleteDate;
	}


	// ToString
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", paisNacimiento=" + paisNacimiento + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", correo="
				+ correo + ", telefono=" + telefono + ", createDate=" + createDate + ", deleted=" + deleted
				+ ", deleteDate=" + deleteDate + "]";
	}
}

