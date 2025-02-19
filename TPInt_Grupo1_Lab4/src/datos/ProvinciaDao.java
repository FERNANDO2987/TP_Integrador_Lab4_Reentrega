package datos;

import java.util.List;

import entidad.Provincia;
import entidad.ProvinciaConClientes;

public interface ProvinciaDao {
	
	public List<Provincia> listarProvincias();
	public List<ProvinciaConClientes> obtenerCantidadClientesPorProvincia();

}
