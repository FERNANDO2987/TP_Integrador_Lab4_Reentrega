package datos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import entidad.Prestamo;

public interface PrestamoDao {
	
	public List<Prestamo> ObtenerPrestamos();
	 public Map<String, BigDecimal> obtenerMontosPendientes();
	 public boolean rechazarPrestamo(int idPrestamo);
	 public boolean aprobarPrestamo(int idPrestamo);
	 public List<Prestamo> ObtenerTodosLosPrestamos();
	

}
