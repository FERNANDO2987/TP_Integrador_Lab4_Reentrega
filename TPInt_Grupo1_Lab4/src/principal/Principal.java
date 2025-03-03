package principal;


import java.util.List;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import entidadDTO.PrestamoDTO;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;






public class Principal {

    public static void main(String[] args) {
    	
    	
    	
    	
         PrestamoNeg prestamo = new PrestamoNegImpl();
         
    	// Crear una instancia de PrestamoDao
    	//PrestamoDao prestamo = new PrestamoDaoImpl();

    	// ID del cliente a consultar
    	int idCliente = 78; // Cambia esto por un ID válido en tu base de datos

    	// Prueba del método ListarPrestamosPorCliente con un ID de cliente
    	System.out.println("\n=== LISTADO DE PRÉSTAMOS POR CLIENTE ID: " + idCliente + " ===");
    	List<Prestamo> prestamosCliente = prestamo.ListarPrestamosPorClientesPendientes(idCliente);

    	// Verificar si se obtuvieron resultados
    	if (prestamosCliente.isEmpty()) {
    	    System.out.println("No se encontraron préstamos para el cliente con ID: " + idCliente);
    	} else {
    	    for (Prestamo p : prestamosCliente) {
    	        System.out.println("ID Préstamo: " + p.getId());
    	        System.out.println("Tipo de Préstamo: " + (p.getObservaciones() != null ? p.getObservaciones() : "N/A"));
    	        System.out.println("ID Cliente: " + p.getCliente().getId());
 // Asumiendo que Cliente tiene este campo
    	        System.out.println("Fecha de Solicitud: " + (p.getFechaAlta() != null ? p.getFechaAlta().toString() : "Sin fecha"));
    	        System.out.println("Monto Solicitado: " + p.getImporte());
    	        System.out.println("Cantidad de Cuotas: " + p.getCuotas());
    	        System.out.println("Valor de Cuota: " + (p.getValorCuotas()!= null ? p.getValorCuotas() : "N/A"));
    	        System.out.println("Estado: " + (p.getEstado() != null ? p.getEstado() : "Desconocido"));
    	        System.out.println("-----------------------------------");
    	    }
    	}

        
       

    	
    }
    
}

 