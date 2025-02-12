package entidad;

public class UsuarioCliente {
	   private int id;
	    private int idCliente;
	    private String usuario;
	    private String password;
	    private boolean admin;
	    
	    public UsuarioCliente(int id, int idCliente, String usuario, String password, boolean admin) {
			super();
			this.id = id;
			this.idCliente = idCliente;
			this.usuario = usuario;
			this.password = password;
			this.admin = admin;
		}
	    
	    
	    
		public UsuarioCliente() {
		
		}



		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
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
		public boolean isAdmin() {
			return admin;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}
		
		
		
		
		@Override
		public String toString() {
			return "UsuarioCliente [id=" + id + ", idCliente=" + idCliente + ", usuario=" + usuario + ", password="
					+ password + ", admin=" + admin + "]";
		}
	

}
