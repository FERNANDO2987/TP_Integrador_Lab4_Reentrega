package principal;


import java.util.List;

import entidad.Prestamo;
import negocioImpl.PrestamoNegImpl;


public class Principal {

    public static void main(String[] args) {


    	// Crear instancia de PrestamoNegImpl
        PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();

        try {
            // Llamar al método ListarPrestamos y obtener la lista de préstamos
            List<Prestamo> listaPrestamos = prestamoNeg.ListarPrestamos();

            // Recorrer la lista y mostrar los datos
            for (Prestamo prestamo : listaPrestamos) {
                System.out.println("ID: " + prestamo.getId());
                System.out.println("Importe: " + prestamo.getImporte());
                System.out.println("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido());
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error al listar los préstamos: " + e.getMessage());
        }
    
    }
}
