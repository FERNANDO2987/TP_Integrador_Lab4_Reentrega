package negocio;

import java.util.ArrayList;


import entidad.Provincia;
import entidad.ProvinciaConClientes;



public interface ProvinciasNeg {
	
	public ArrayList<Provincia> ListarProvincias();
	public ArrayList<ProvinciaConClientes> ObtenerCantidadClientesPorProvincia();

}
