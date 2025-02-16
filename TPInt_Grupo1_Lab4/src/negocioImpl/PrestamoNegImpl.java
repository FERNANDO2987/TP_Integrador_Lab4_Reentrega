package negocioImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg{

	
	
private PrestamoDao prestamoDao = new  PrestamoDaoImpl();
	
	public PrestamoNegImpl(PrestamoDao prestamoDao)
	{
		this.prestamoDao = prestamoDao;
		
	}
	
	public PrestamoNegImpl()
	{
		
	}
	
	@Override
	public ArrayList<Prestamo> ListarPrestamos() {
		   List<Prestamo> prestamos = prestamoDao.ObtenerPrestamos();

	        if (prestamos == null || prestamos.isEmpty()) {
	            throw new RuntimeException("No se encontraron prestamos.");
	        }

	        return (ArrayList<Prestamo>) prestamos;
	}

	@Override
	public Map<String, BigDecimal> ObtenerMontosPendientes() {
		  // Llamamos al m�todo de la capa de datos (DAO)
	    Map<String, BigDecimal> montos = prestamoDao.obtenerMontosPendientes();

	    // Validamos que los montos no sean nulos y tengan valores
	    if (montos == null || montos.isEmpty()) {
	        throw new RuntimeException("No se encontraron montos pendientes.");
	    }

	    // Validamos que los valores de los montos sean v�lidos (no nulos ni cero)
	    BigDecimal montoSolicitado = montos.get("montoTotalSolicitado");
	    BigDecimal montoAdjudicado = montos.get("montoTotalAdjudicado");

	    if (montoSolicitado == null || montoSolicitado.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new RuntimeException("Monto total solicitado no v�lido.");
	    }

	    if (montoAdjudicado == null || montoAdjudicado.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new RuntimeException("Monto total adjudicado no v�lido.");
	    }

	    return montos;
	}

	@Override
	public boolean RechazarPrestamo(int idPrestamo) {
		 // Obtener la lista de pr�stamos
	    List<Prestamo> prestamos = prestamoDao.ObtenerPrestamos();
	    
	    // Buscar el pr�stamo por ID
	    Prestamo prestamo = prestamos.stream()
	            .filter(p -> p.getId() == idPrestamo)
	            .findFirst()
	            .orElse(null);

	    // Validar si el pr�stamo existe
	    if (prestamo == null) {
	        throw new RuntimeException("No se encontr� el pr�stamo con ID: " + idPrestamo);
	    }

	    if (!"pendiente".equalsIgnoreCase(prestamo.getEstado())) {
	        throw new RuntimeException("El pr�stamo no est� en estado 'pendiente'. No se puede aprobar.");
	    }
	    return prestamoDao.rechazarPrestamo(idPrestamo);
	}
	
	

	@Override
	public boolean AprobarPrestamo(int idPrestamo) {
		 // Obtener la lista de pr�stamos
	    List<Prestamo> prestamos = prestamoDao.ObtenerPrestamos();
	    
	    // Buscar el pr�stamo por ID
	    Prestamo prestamo = prestamos.stream()
	            .filter(p -> p.getId() == idPrestamo)
	            .findFirst()
	            .orElse(null);

	    // Validar si el pr�stamo existe
	    if (prestamo == null) {
	        throw new RuntimeException("No se encontr� el pr�stamo con ID: " + idPrestamo);
	    }

	    if (!"pendiente".equalsIgnoreCase(prestamo.getEstado())) {
	        throw new RuntimeException("El pr�stamo no est� en estado 'pendiente'. No se puede aprobar.");
	    }
	    return prestamoDao.aprobarPrestamo(idPrestamo);
	}

	@Override
	public ArrayList<Prestamo> ListarTodosLosPrestamos() {
		  List<Prestamo> prestamos = prestamoDao.ObtenerTodosLosPrestamos();

	        if (prestamos == null || prestamos.isEmpty()) {
	            throw new RuntimeException("No se encontraron prestamos.");
	        }

	        return (ArrayList<Prestamo>) prestamos;
	}

}
