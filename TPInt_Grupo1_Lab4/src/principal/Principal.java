package principal;
import java.math.BigDecimal;
import java.util.ArrayList;


import datosImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Provincia;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.ProvinciaNegImpl;


import java.util.List;

import entidad.Prestamo;
import negocioImpl.PrestamoNegImpl;



public class Principal {

    public static void main(String[] args) {


    	
    	
    
        PrestamoNeg impl = new PrestamoNegImpl();
        
        Prestamo pr = new Prestamo();
        Cliente cl = new Cliente();
        Cuenta cu = new Cuenta();
        cu.setNroCuenta(222222);
        cl.setId(2);
        
        pr.setCliente(cl);
        pr.setCuenta(cu);
        pr.setImporte(BigDecimal.valueOf(7000));
        pr.setCuotas(3);
        impl.AgregarPrestamo(pr);      
        
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
            System.out.println("----------------------------------------------");


    	// Crear instancia de PrestamoNegImpl
        PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();

        try {
            // Llamar al m�todo ListarPrestamos y obtener la lista de pr�stamos
            List<Prestamo> listaPrestamos = prestamoNeg.ListarPrestamos();

            // Recorrer la lista y mostrar los datos
            for (Prestamo prestamo : listaPrestamos) {
                System.out.println("ID: " + prestamo.getId());
                System.out.println("Importe: " + prestamo.getImporte());
                System.out.println("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido());
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error al listar los pr�stamos: " + e.getMessage());

        }
    
    }
}
 