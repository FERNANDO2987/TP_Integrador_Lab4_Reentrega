package principal;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;

public class Principal {

    public static void main(String[] args) {

    	  PrestamoNeg prestamoNeg = new PrestamoNegImpl();
    	  
    	  
    	  PrestamoDao prestamoDao = new PrestamoDaoImpl();
    	  
    	  
    	   int idPrestamo = 7;  // ID de préstamo a procesar
           int nroCuenta = 6; // Número de cuenta del cliente
           
           // Ejecutar el método
           boolean pagoRealizado = prestamoDao.procesarPago(idPrestamo, nroCuenta);

           // Mostrar resultado en consola
           if (pagoRealizado) {
               System.out.println("✅ Pago realizado con éxito.");
           } else {
               System.out.println("❌ Error al procesar el pago.");
           }

         
    }
    
}

 