package entidad;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private Pais paisNacimiento = new Pais();
    private LocalDate fechaNacimiento;
    private String direccion;
    private Localidad localidad = new Localidad();
    private Provincia provincia = new Provincia();
    private String correo;
    private String telefono;
    

    
    
	// Constructor vac�o
    public Cliente() {}
    
    // Constructor con par�metros   
	public Cliente(int id, String dni, String cuil, String nombre, String apellido, String sexo, Pais paisNacimiento,
			LocalDate fechaNacimiento, String direccion, Localidad localidad, Provincia provincia, String correo,
			String telefono) {
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
	
	}

	
	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
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



	// ToString
	@Override
	public String toString() {
	    return "Cliente {" +
	           "\n  id=" + id +
	           ",\n  dni='" + dni + '\'' +
	           ",\n  cuil='" + cuil + '\'' +
	           ",\n  nombre='" + nombre + '\'' +
	           ",\n  apellido='" + apellido + '\'' +
	           ",\n  sexo='" + sexo + '\'' +
	           ",\n  paisNacimiento=" + paisNacimiento.toString() +
	           ",\n  fechaNacimiento=" + fechaNacimiento +
	           ",\n  direccion='" + direccion + '\'' +
	           ",\n  localidad=" + localidad.toString() +
	           ",\n  provincia=" + provincia.toString() +
	           ",\n  correo='" + correo + '\'' +
	           ",\n  telefono='" + telefono + '\'' +
	           "\n}";
	}

}
