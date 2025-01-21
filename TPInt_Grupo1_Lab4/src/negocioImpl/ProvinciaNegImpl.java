package negocioImpl;

import java.util.ArrayList;
import java.util.List;
import datos.ProvinciaDao;
import datosImpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciasNeg;

public class ProvinciaNegImpl implements ProvinciasNeg {
	
	private ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
	
	public ProvinciaNegImpl(ProvinciaDao provinciaDao )
	{
		this.provinciaDao = provinciaDao;
	}
	
	public ProvinciaNegImpl()
	{
		
	}

	@Override
	public ArrayList<Provincia> ListarProvincias() {
		  List<Provincia> provincias = provinciaDao.listarProvincias();
		    if (provincias == null || provincias.isEmpty()) {
		        System.err.println("No se encontraron provincias.");
		        return new ArrayList<>();
		    }
		    System.out.println("Provincias encontrados: " + provincias.size());
		    return new ArrayList<>(provincias);
	}

}
