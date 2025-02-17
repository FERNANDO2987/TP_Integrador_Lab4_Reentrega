package negocio;


import java.util.List;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


import entidad.Prestamo;

public interface PrestamoNeg {

	public List<Prestamo> ListarPrestamos2();
	public boolean RechazarPrestamo(int idPrestamo, String observacion);
	public boolean AprobarPrestamo(int idPrestamo, String observacion);
	public boolean AgregarPrestamo(Prestamo prestamo);

	
	public ArrayList<Prestamo> ListarPrestamos();
	 public Map<String, BigDecimal> ObtenerMontosPendientes();
	 public boolean RechazarPrestamo(int idPrestamo);
	 public boolean AprobarPrestamo(int idPrestamo);
		public ArrayList<Prestamo> ListarTodosLosPrestamos();
	 
	

}
