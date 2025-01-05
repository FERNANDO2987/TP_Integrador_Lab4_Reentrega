package entidad;

import java.time.LocalDate;

public class Usuario {
	private int id;
	private Cliente cliente;
	private String usuario;
	private String password;
	private boolean admin;
	
	private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
    
    
    // Constructor   
	public Usuario(int id, Cliente cliente, String usuario, String password, boolean admin, LocalDate createDate,
			boolean deleted, LocalDate deleteDate) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.usuario = usuario;
		this.password = password;
		this.admin = admin;
		this.createDate = createDate;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}


	// Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public boolean getDeleted() {
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
		return "Usuario [id=" + id + ", cliente=" + cliente + ", usuario=" + usuario + ", password=" + password
				+ ", admin=" + admin + ", createDate=" + createDate + ", deleted=" + deleted + ", deleteDate="
				+ deleteDate + "]";
	}
}
