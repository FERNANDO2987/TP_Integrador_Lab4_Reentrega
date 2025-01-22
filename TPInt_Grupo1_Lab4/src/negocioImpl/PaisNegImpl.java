package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PaisDao;
import datosImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNeg;


public class PaisNegImpl implements PaisNeg {

	private PaisDao paisDao = new PaisDaoImpl();
	
	public PaisNegImpl(PaisDao paisDao )
	{
		this.paisDao = paisDao;
	}
	
	public PaisNegImpl()
	{
		
	}

	@Override
	public ArrayList<Pais> ListarPaises() {
		  List<Pais> paises = paisDao.listarPaises();
		    if (paises == null || paises.isEmpty()) {
		        System.err.println("No se encontraron paises.");
		        return new ArrayList<>();
		    }
		    System.out.println("Paises encontrados: " + paises.size());
		    return new ArrayList<>(paises);
	}
	
}
