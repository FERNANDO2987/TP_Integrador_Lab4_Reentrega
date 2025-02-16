package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import entidad.Prestamo;

public interface PrestamoNeg {
	
	public ArrayList<Prestamo> ListarPrestamos();
	 public Map<String, BigDecimal> ObtenerMontosPendientes();
	 public boolean RechazarPrestamo(int idPrestamo);
	 public boolean AprobarPrestamo(int idPrestamo);

}
