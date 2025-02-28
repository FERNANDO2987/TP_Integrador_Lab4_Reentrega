package negocioImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;

import entidad.Prestamo;

import entidadDTO.PrestamoDTO;
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

	public List<Prestamo> ListarPrestamos2() {
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
	public ArrayList<Prestamo> ListarPrestamos() {
		   List<Prestamo> prestamos = prestamoDao.ObtenerPrestamos();

	        if (prestamos == null || prestamos.isEmpty()) {
	            throw new RuntimeException("No se encontraron prestamos.");
	        }

	        return (ArrayList<Prestamo>) prestamos;
	}
	

	@Override
	public boolean RechazarPrestamo(int idPrestamo, String observacion) {
		if(idPrestamo < 1)
		{
	        System.err.println("El ID del prestamo no es v�lido.");
	        return false;
		}
		if(!prestamoDao.ChequearPendiente(idPrestamo)) {
			System.err.println("El id proporcionado no existe o pertenece a un prestamo evaluado.");
	        return false;
		}
		if(observacion == null)
		{
			System.err.println("La observacion no puede estar vac�a");
	        return false;
		}
		return prestamoDao.RechazarPrestamo(idPrestamo, observacion);
	}

	@Override
	public boolean AprobarPrestamo(int idPrestamo, String observacion) {
		if(idPrestamo < 1)
		{
	        System.err.println("El ID del prestamo no es )v�lido.");
	        return false;
		}
		if(!prestamoDao.ChequearPendiente(idPrestamo)) {
			System.err.println("El id proporcionado no existe o pertenece a un prestamo evaluado.");
	        return false;
		}
		if(observacion == null)
		{
			System.err.println("La observacion no puede estar vac�a");
	        return false;
		}
		
		return prestamoDao.AprobarPrestamo(idPrestamo, observacion);
	}

	

	@Override
	public boolean AgregarPrestamo(Prestamo prestamo) {
	    if (prestamo == null) {
	        System.err.println("El objeto prestamo es null");
	        return false;
	    }

	    if (prestamo.getCliente() == null || prestamo.getCuenta() == null) {
	        System.err.println("El cliente o la cuenta no est�n definidos");
	        return false;
	    }

	    Integer cuotas = prestamo.getCuotas(); // Almacena el valor en una variable auxiliar

	    if (cuotas == null || cuotas <= 0) {
	        System.err.println("N�mero de cuotas no v�lido");
	        return false;
	    }

	    if (prestamo.getImporte() == null || prestamo.getImporte().compareTo(BigDecimal.ZERO) <= 0) {
	        System.err.println("Importe no v�lido");
	        return false;
	    }

	    if (prestamo.getObservaciones() == null || prestamo.getObservaciones().isEmpty()) {
	        System.err.println("Tipo de pr�stamo vac�o o no v�lido");
	        return false;
	    }

	    Set<String> tiposValidos = new HashSet<>(Arrays.asList(
	    	    "Adelanto de sueldo",
	    	    "Prestamo personal",
	    	    "Prestamo prendario",
	    	    "Prestamo hipotecario"
	    	));

	    if (!tiposValidos.contains(prestamo.getObservaciones())) {
	        System.err.println("Tipo de pr�stamo inv�lido: " + prestamo.getObservaciones());
	        return false;
	    }

	    if (prestamoDao == null) {
	        System.err.println("prestamoDao no ha sido inicializado");
	        return false;
	    }

	    return prestamoDao.AgregarPrestamo(prestamo);
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
	        throw new RuntimeException("No se encontro el prestamo con ID: " + idPrestamo);
	    }

	    if (!"pendiente".equalsIgnoreCase(prestamo.getEstado())) {
	        throw new RuntimeException("El prestamo no esta en estado 'pendiente'. No se puede aprobar.");
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
	        throw new RuntimeException("No se encontro el prestamo con ID: " + idPrestamo);
	    }

	    if (!"pendiente".equalsIgnoreCase(prestamo.getEstado())) {
	        throw new RuntimeException("El prestamo no esta en estado 'pendiente'. No se puede aprobar.");
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



	@Override
	public List<Prestamo> ObtenerMovimientosPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) {
	
		
		List<Prestamo> prestamos = prestamoDao.obtenerMovimientosPorFecha(fechaDesde, fechaHasta);
		if(prestamos == null || prestamos.isEmpty())
		{
			System.err.println("No se encontraron movimientos por fecha.");
			return new ArrayList<>();
		}
		System.out.println("Prestamos encontrados por Movimientos: " + prestamos.size());
		return new ArrayList<>(prestamos);
		
		
	}





	




	@Override
	public Prestamo ObtenerPrestamoPorId(int idPrestamo) {
	    Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);

	    if (prestamo == null) {
	        throw new RuntimeException("No se encontraron prestamos con ID: " + idPrestamo);
	    }

	    return prestamo;
	}




	@Override
	public List<PrestamoDTO> ListarPrestamosPorCliente(int clienteId) {
	    List<PrestamoDTO> prestamos = prestamoDao.listarPrestamosPorCliente(clienteId);

	    if (prestamos == null || prestamos.isEmpty()) {
	        return new ArrayList<>(); // Retorna una lista vac�a
	    }

	    return prestamos; // Retorna los pr�stamos encontrados
	}





	@Override
	public List<PrestamoDTO> ListarPrestamosPorEstadosPendientes(int clienteId) {
				
				List<PrestamoDTO> prestamos = prestamoDao.listarPrestamosPorEstadosPendientes(clienteId);

		        if (prestamos == null || prestamos.isEmpty()) {
		            throw new RuntimeException("No se encontraron prestamos pendientes.");
		        }

		        return (ArrayList<PrestamoDTO>) prestamos;
				
	}




	@Override
	public List<PrestamoDTO> ListarPrestamosPorEstadosAprobados(int clienteId) {
		List<PrestamoDTO> prestamos = prestamoDao.listarPrestamosPorEstadosAprobados(clienteId);
		if(prestamos == null || prestamos.isEmpty())
		{
			System.err.println("No se encontraron prestamos pendientes");
			return new ArrayList<>();
		}
		System.out.println("Prestamos encontrados: " + prestamos.size());
		return new ArrayList<>(prestamos);
	}



	@Override
	public String PagarCuota(int idPrestamo) {
	    if (idPrestamo <= 0) {
	        System.err.println("La cuota no se puede pagar.");
	        return "ID de pr�stamo no v�lido.";
	    }
	    return prestamoDao.pagarCuota(idPrestamo);
	}



	
	

}
