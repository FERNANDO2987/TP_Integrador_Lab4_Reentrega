package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.LocalidadDao;

import datosImpl.LocalidadDaoImpl;

import entidad.Localidad;
import negocio.LocalidadNeg;

public class LocalidadNegImpl implements LocalidadNeg{
	
private LocalidadDao localidadDao = new LocalidadDaoImpl();
	
	public LocalidadNegImpl(LocalidadDao localidadDao )
	{
		this.localidadDao = localidadDao;
	}
	
	public LocalidadNegImpl()
	{
		
	}
	

	@Override
	public ArrayList<Localidad> ListarLocalidades() {
		  List<Localidad> localidades = localidadDao.listarLocalidades();
		    if (localidades == null || localidades.isEmpty()) {
		        System.err.println("No se encontraron localidades.");
		        return new ArrayList<>();
		    }
		    System.out.println("Localidades encontrados: " + localidades.size());
		    return new ArrayList<>(localidades);
	}

}
