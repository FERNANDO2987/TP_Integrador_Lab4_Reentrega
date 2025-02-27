package negocioImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Map;
import java.util.Set;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import entidadDTO.CuentaDTO;
import entidadDTO.CuotaDTO;
import entidadDTO.MovimientoDTO;
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
		if (prestamo.getCliente() == null || prestamo.getCuenta() == null) {
		    System.err.println("El cliente o la cuenta no est�n definidos");
		    return false;
		}
		if (!(prestamo.getCuotas() > 0)) {
		    System.err.println("Numero de cuotas no valido");
		    return false;
		}
		if (!(prestamo.getImporte().compareTo(BigDecimal.ZERO) > 0)) {
			System.err.println("Importe no valido");
		    return false;
		}
		if (prestamo.getObservaciones() == "")
		{
			System.err.println("Tipo de prestamo vacio o no valido: " + prestamo.getObservaciones());
			return false;
		}
		if (prestamo.getObservaciones() != "Adelanto de sueldo"	&&
			prestamo.getObservaciones() != "Prestamo personal"	&&
			prestamo.getObservaciones() != "Prestamo prendario"	&&
			prestamo.getObservaciones() != "Prestamo hipotecario")
		{
			System.err.println("Tipo de prestamo vacio o no valido: " + prestamo.getObservaciones());
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
	public ArrayList<CuentaDTO> ObtenerDatosCliente(int idCliente) {
		 List<CuentaDTO> datos = prestamoDao.obtenerInformacionCuenta(idCliente);

	        if (datos == null || datos.isEmpty()) {
	            throw new RuntimeException("No se encontraron datos de la cuenta.");
	        }

	        return (ArrayList<CuentaDTO>) datos;
	}

	
	public List<CuentaDTO> obtenerEstadosPendientes(int idCliente) {  
	    // Llamar al m�todo original para obtener todas las cuentas  
	    List<CuentaDTO> cuentas = prestamoDao.obtenerInformacionCuenta(idCliente);  
	    
	    // Asegurarse de que cuentas no sea null  
	    if (cuentas == null) {  
	        cuentas = new ArrayList<>(); // Inicializa como lista vac�a si es null  
	    }  
	    
	    List<CuentaDTO> cuentasPendientes = new ArrayList<>();  

	    // Filtrar cuentas para obtener solo las que tienen estados vigentes  
	    for (CuentaDTO cuenta : cuentas) {  
	        if (cuenta == null) continue; // Ignora cuentas nulas  

	        CuentaDTO cuentaPendiente = new CuentaDTO();  
	        cuentaPendiente.setNroCuenta(cuenta.getNroCuenta());  
	        cuentaPendiente.setCbu(cuenta.getCbu());  
	        cuentaPendiente.setSaldo(cuenta.getSaldo());  
	        cuentaPendiente.setCliente(cuenta.getCliente());  
	        cuentaPendiente.setTipoCuenta(cuenta.getTipoCuenta());  

	        // Inicializar listas si no lo est�n ya  
	        cuentaPendiente.setMovimientos(new ArrayList<>());  
	        cuentaPendiente.setPrestamos(new ArrayList<>());  
	        cuentaPendiente.setCuotas(new ArrayList<>());  

	        // Filtrar movimientos  
	        if (cuenta.getMovimientos() != null) {  
	            for (MovimientoDTO movimiento : cuenta.getMovimientos()) {  
	                if (movimiento != null && movimiento.getImporte() != null && movimiento.getImporte().compareTo(BigDecimal.ZERO) > 0) {  
	                	cuentaPendiente.getMovimientos().add(movimiento);  
	                }  
	            }  
	        }  

	        // Filtrar pr�stamos  
	        if (cuenta.getPrestamos() != null) {  
	            for (PrestamoDTO prestamo : cuenta.getPrestamos()) {  
	                if (prestamo != null && "pendiente".equalsIgnoreCase(prestamo.getEstado())) {  
	                	cuentaPendiente.getPrestamos().add(prestamo);  
	                }  
	            }  
	        }  

	        // Filtrar cuotas  
	        if (cuenta.getCuotas() != null) {  
	            for (CuotaDTO cuota : cuenta.getCuotas()) {  
	                // Verificar si el estado de pago es pendiente (true)  
	                if (cuota != null && cuota.isEstadoPago()) {  
	                	cuentaPendiente.getCuotas().add(cuota);  
	                }  
	            }  
	        }  

	        // Solo agregar la cuenta si tiene movimientos, pr�stamos o cuotas pendiente  
	        if (!cuentaPendiente.getMovimientos().isEmpty() ||   
	            !cuentaPendiente.getPrestamos().isEmpty() ||   
	            !cuentaPendiente.getCuotas().isEmpty()) {  
	        	cuentasPendientes.add(cuentaPendiente);  
	        }  
	    }  

	    return cuentasPendientes;  
	}
	
	public List<CuentaDTO> obtenerEstadosVigentes(int idCliente) {  
	    // Llamar al m�todo original para obtener todas las cuentas  
	    List<CuentaDTO> cuentas = prestamoDao.obtenerInformacionCuenta(idCliente);  
	    
	    // Asegurarse de que cuentas no sea null  
	    if (cuentas == null) {  
	        cuentas = new ArrayList<>(); // Inicializa como lista vac�a si es null  
	    }  
	    
	    List<CuentaDTO> cuentasVigentes = new ArrayList<>();  

	    // Filtrar cuentas para obtener solo las que tienen estados vigentes  
	    for (CuentaDTO cuenta : cuentas) {  
	        if (cuenta == null) continue; // Ignora cuentas nulas  

	        CuentaDTO cuentaVigente = new CuentaDTO();  
	        cuentaVigente.setNroCuenta(cuenta.getNroCuenta());  
	        cuentaVigente.setCbu(cuenta.getCbu());  
	        cuentaVigente.setSaldo(cuenta.getSaldo());  
	        cuentaVigente.setCliente(cuenta.getCliente());  
	        cuentaVigente.setTipoCuenta(cuenta.getTipoCuenta());  

	        // Inicializar listas si no lo est�n ya  
	        cuentaVigente.setMovimientos(new ArrayList<>());  
	        cuentaVigente.setPrestamos(new ArrayList<>());  
	        cuentaVigente.setCuotas(new ArrayList<>());  

	        // Filtrar movimientos  
	        if (cuenta.getMovimientos() != null) {  
	            for (MovimientoDTO movimiento : cuenta.getMovimientos()) {  
	                if (movimiento != null && movimiento.getImporte() != null && movimiento.getImporte().compareTo(BigDecimal.ZERO) > 0) {  
	                    cuentaVigente.getMovimientos().add(movimiento);  
	                }  
	            }  
	        }  

	        // Filtrar pr�stamos  
	        if (cuenta.getPrestamos() != null) {  
	            for (PrestamoDTO prestamo : cuenta.getPrestamos()) {  
	                if (prestamo != null && "aprobado".equalsIgnoreCase(prestamo.getEstado())) {  
	                    cuentaVigente.getPrestamos().add(prestamo);  
	                }  
	            }  
	        }  

	        // Filtrar cuotas  
	        if (cuenta.getCuotas() != null) {  
	            for (CuotaDTO cuota : cuenta.getCuotas()) {  
	                // Verificar si el estado de pago es pendiente (true)  
	                if (cuota != null && cuota.isEstadoPago()) {  
	                    cuentaVigente.getCuotas().add(cuota);  
	                }  
	            }  
	        }  

	        // Solo agregar la cuenta si tiene movimientos, pr�stamos o cuotas vigentes  
	        if (!cuentaVigente.getMovimientos().isEmpty() ||   
	            !cuentaVigente.getPrestamos().isEmpty() ||   
	            !cuentaVigente.getCuotas().isEmpty()) {  
	            cuentasVigentes.add(cuentaVigente);  
	        }  
	    }  

	    return cuentasVigentes;  
	}



	@Override
	public Prestamo ObtenerPrestamoPorId(int idPrestamo) {
	    Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);

	    if (prestamo == null) {
	        throw new RuntimeException("No se encontraron pr�stamos con ID: " + idPrestamo);
	    }

	    return prestamo;
	}





	
	

}
