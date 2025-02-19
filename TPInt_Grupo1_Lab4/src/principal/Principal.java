package principal;


import entidad.ProvinciaConClientes;
import negocio.ProvinciasNeg;
import negocioImpl.ProvinciaNegImpl;


import java.util.List;





public class Principal {

    public static void main(String[] args) {


    	
    	
    
        ProvinciasNeg impl = new ProvinciaNegImpl();
    
        
        // Llamar al método ObtenerMovimientosPorFecha
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
 