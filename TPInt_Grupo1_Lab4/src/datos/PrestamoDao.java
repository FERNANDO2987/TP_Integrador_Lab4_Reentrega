package datos;


import java.util.List;

import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.Map;


import entidad.Cuenta;
import entidad.Prestamo;
import entidadDTO.CuentaDTO;

public interface PrestamoDao {

	public List<Prestamo> ListarPrestamos();
	public boolean RechazarPrestamo(int idPrestamo, String observacion);
	public boolean AprobarPrestamo(int idPrestamo, String observacion);
	public boolean AgregarPrestamo(Prestamo prestamo);
	public boolean ChequearPendiente(int id);

	
	public List<Prestamo> ObtenerPrestamos();
	 public Map<String, BigDecimal> obtenerMontosPendientes();
	 public boolean rechazarPrestamo(int idPrestamo);
	 public boolean aprobarPrestamo(int idPrestamo);
	 public List<Prestamo> ObtenerTodosLosPrestamos();
	 public List<Prestamo> ObtenerPrestamosFiltrados(BigDecimal mayorA, BigDecimal menorA);
	 public List<Prestamo> obtenerMovimientosPorFecha(LocalDate fechaDesde, LocalDate fechaHasta);
	 public List<CuentaDTO> obtenerInformacionCuenta(int idCliente);
	 public Prestamo obtenerPrestamoPorId(int idPrestamo);
    public boolean procesarPago(int idPrestamo, int nroCuenta);
	


}
