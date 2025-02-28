package principal;


import java.util.List;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;



import entidadDTO.PrestamoDTO;






public class Principal {

    public static void main(String[] args) {
    	
    	
    	
    	
         PrestamoDao prestamoDao = new PrestamoDaoImpl();

         
         int idCliente = 78;
          
       

     
         // Prueba del método listarPrestamosPorCliente con un ID de cliente
         int clienteId = 78; // Cambia esto por un ID válido en tu base de datos
         System.out.println("\n=== LISTADO DE PRÉSTAMOS POR CLIENTE ID: " + clienteId + " ===");
         List<PrestamoDTO> prestamosCliente = prestamoDao.listarPrestamosPorCliente(clienteId);
         for (PrestamoDTO p : prestamosCliente) {
             System.out.println("ID Préstamo: " + p.getId());
             System.out.println("Observaciones: " + p.getObservaciones());
             System.out.println("Fecha Solicitud: " + p.getFechaAlta());
             System.out.println("Importe: " + p.getImporte());
             System.out.println("Cuotas: " + p.getCuotas());
             System.out.println("Valor de Cuota: " + p.getValorCuotas());
             System.out.println("Estado: " + p.getEstado());
             System.out.println("Cuenta CBU: " + p.getCuenta().getCbu());
             System.out.println("Saldo: " + p.getCuenta().getSaldo());
             System.out.println("Tipo de Cuenta: " + p.getCuenta().getTipoCuenta().getDescripcion());
          
             System.out.println("-----------------------------------");
         }
        
       

    	
    }
    
}

 