package principal;



import entidad.ProvinciaConClientes;
import negocio.ProvinciasNeg;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;



import datosImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.Provincia;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;

import negocioImpl.ProvinciaNegImpl;


import java.util.List;





public class Principal {

    public static void main(String[] args) {



    	
    	
    
        ProvinciasNeg impl = new ProvinciaNegImpl();
    
        
        // Llamar al m�todo ObtenerMovimientosPorFecha
        List<ProvinciaConClientes> povincias = impl.ObtenerCantidadClientesPorProvincia();

        // Verificar si hay resultados
        if (povincias.isEmpty()) {
            System.out.println("No se encontraron povincias.");
        } else {
            System.out.println("povincias encontrados:");
            for (ProvinciaConClientes provincia : povincias) {
                System.out.println("Provincia: " + provincia.getProvincia().getNombre());
                System.out.println("Cantidad: " + provincia.getCantidadClientes());
                System.out.println("--------------------------------");
            }
        }
        

    }
    
}

 