package entidad;

public class ProvinciaConClientes {
    private Provincia provincia;
    private int cantidadClientes;

    // Constructor
    public ProvinciaConClientes(Provincia provincia, int cantidadClientes) {
        this.provincia = provincia;
        this.cantidadClientes = cantidadClientes;
    }

    // Getters y Setters
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public int getCantidadClientes() {
        return cantidadClientes;
    }

    public void setCantidadClientes(int cantidadClientes) {
        this.cantidadClientes = cantidadClientes;
    }

    // ToString
    @Override
    public String toString() {
        return "ProvinciaConClientes [provincia=" + provincia + ", cantidadClientes=" + cantidadClientes + "]";
    }
}
