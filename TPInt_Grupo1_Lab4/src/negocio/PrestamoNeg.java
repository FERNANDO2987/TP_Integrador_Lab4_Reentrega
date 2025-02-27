package negocio;


import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


import entidad.Prestamo;
import entidadDTO.CuentaDTO;

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
		
		 public List<Prestamo> ObtenerMovimientosPorFecha(LocalDate fechaDesde, LocalDate fechaHasta);
		 public ArrayList<CuentaDTO> ObtenerDatosCliente(int idCliente);
			public List<CuentaDTO> obtenerEstadosPendientes(int idCliente);
			public List<CuentaDTO> obtenerEstadosVigentes(int idCliente) ;
			public Prestamo ObtenerPrestamoPorId(int idPrestamo);
	 
	
	

}
