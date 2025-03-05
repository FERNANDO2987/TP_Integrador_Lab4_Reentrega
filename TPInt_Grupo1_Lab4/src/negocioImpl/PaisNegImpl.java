package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PaisDao;
import datosImpl.PaisDaoImpl;
import entidad.Pais;
import excepciones.PaisNoEncontradoException;
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
	public ArrayList<Pais> ListarPaises() throws PaisNoEncontradoException {
		List<Pais> paises = new ArrayList<Pais>();
		try {	
		paises = paisDao.listarPaises();
	   
	    System.out.println("Paises encontrados: " + paises.size());
	    return (ArrayList<Pais>) paises;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	     if (paises == null || paises.isEmpty()) {
	        System.err.println("No se encontraron paises.");
	        throw new PaisNoEncontradoException();
	    }
		return null;
	}


	
}
