package entidad;



public class Usuario {
    private int id;
    private Cliente cliente = new Cliente();
    private String usuario;
    private String password;
    private boolean admin;

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(int id, Cliente cliente, String usuario, String password, boolean admin) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.password = password;
        this.admin = admin;
    }

    // Getters y Setters
    public int getId() { // Cambiado a int
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

    public boolean isAdmin() { // Cambiado a isAdmin
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // ToString
    @Override
    public String toString() {
        return "Usuario [id=" + id + 
               ", cliente=" + cliente.toString() + 
               ", usuario=" + usuario + 
               ", admin=" + admin + "]";
        
    }
}
