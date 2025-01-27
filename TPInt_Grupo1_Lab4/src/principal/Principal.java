package principal;
import java.util.ArrayList;

import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;
import entidad.Provincia;
import negocioImpl.ProvinciaNegImpl;


public class Principal {

    public static void main(String[] args) {

    	
    
        PrestamoDaoImpl impl = new PrestamoDaoImpl();
        ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) impl.ListarPrestamos();
        		
        
        for (Prestamo p : prestamos) {
            System.out.println(p.getId());
            System.out.println(p.getCliente().getNombre());
            System.out.println(p.getCliente().getApellido());
            System.out.println(p.getCliente().getCorreo());
            System.out.println(p.getCliente().getTelefono());
            System.out.println(p.getCuenta().getCbu());
            System.out.println(p.getCuenta().getNroCuenta());
            System.out.println(p.getFechaAlta());
            System.out.println(p.getImporte());
            System.out.println(p.getCuotas());
            System.out.println(p.getObservaciones());
            System.out.println(p.getEstado());
        }
        
        
       
    
    }
}
