package principal;


import java.util.List;

import datos.CuentaDao;


import datos.PrestamoDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.PrestamoDaoImpl;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;

public class Principal {

    public static void main(String[] args) {
    	CuentaDao cuentaDao = new CuentaDaoImpl();
    	System.out.println(cuentaDao.existeEsteCbu("2025020712301"));

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

 