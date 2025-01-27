package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg{

	private PrestamoDao prestamoDao = new PrestamoDaoImpl();
	public PrestamoNegImpl(PrestamoDao prestamoDao)
	{
		this.prestamoDao = prestamoDao;
	}
	
	public PrestamoNegImpl() 
	{
		
	}
	
	@Override
	public List<Prestamo> ListarPrestamos() {
		List<Prestamo> prestamos = prestamoDao.ListarPrestamos();
		if(prestamos == null || prestamos.isEmpty())
		{
			System.err.println("No se encontraron usuarios.");
			return new ArrayList<>();
		}
		System.out.println("Prestamos encontrados: " + prestamos.size());
		return new ArrayList<>(prestamos);
	}

	@Override
	public boolean RechazarPrestamo(int idPrestamo, String observacion) {
		if(idPrestamo < 1)
		{
	        System.err.println("El ID del prestamo no es válido.");
	        return false;
		}
		if(observacion == null)
		{
			System.err.println("La observacion no puede estar vacía");
	        return false;
		}
		return prestamoDao.RechazarPrestamo(idPrestamo, observacion);
	}

	@Override
	public boolean AprobarPrestamo(int idPrestamo, String observacion) {
		/*
		if(idPrestamo < 1)
		{
	        System.err.println("El ID del prestamo no es )válido.");
	        return false;
		}
		if(observacion == null)
		{
			System.err.println("La observacion no puede estar vacía");
	        return false;
		}
		*/
		return prestamoDao.AprobarPrestamo(idPrestamo, observacion);
	}
	
}
