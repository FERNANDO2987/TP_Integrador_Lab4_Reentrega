package principal;

import java.util.List;

import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;
import entidadDTO.CuentaDTO;
import entidadDTO.CuotaDTO;
import entidadDTO.MovimientoDTO;
import entidadDTO.PrestamoDTO;






public class Principal {

    public static void main(String[] args) {



    	PrestamoDao prestamoDao = new PrestamoDaoImpl();


        // Definir un idCliente para la prueba  
        int idCliente = 72; // Cambia este valor por uno que exista en tu base de datos  

        // Llamar al método obtenerDatosCliente  
        List<CuentaDTO> cuentas = prestamoDao.obtenerDatosCliente(idCliente);  
        
        // Mostrar los resultados  
        if (cuentas != null && !cuentas.isEmpty()) {  
            for (CuentaDTO cuenta : cuentas) {  
                // Mostrar información básica de la cuenta  
                System.out.println("Número de Cuenta: " + cuenta.getNroCuenta());  
                System.out.println("CBU: " + cuenta.getCbu());  
                System.out.println("Saldo: " + cuenta.getSaldo());  
                System.out.println("ID Cliente: " + cuenta.getCliente().getId());  
                System.out.println("Tipo de Cuenta: " + cuenta.getTipoCuenta().getDescripcion());  
                
                // Mostrar movimientos  
                System.out.println("Movimientos:");  
                for (MovimientoDTO movimiento : cuenta.getMovimientos()) {  
                    System.out.println("  - ID Movimiento: " + movimiento.getId());  
                    System.out.println("    Detalle: " + movimiento.getDetalle());  
                    System.out.println("    Importe: " + movimiento.getImporte());  
                    System.out.println("    Tipo de Movimiento: " + movimiento.getTipoMovimiento().getDescripcion());  
                }  
                
                // Mostrar préstamos  
                System.out.println("Préstamos:");  
                for (PrestamoDTO prestamo : cuenta.getPrestamos()) {  
                    System.out.println("  - ID Préstamo: " + prestamo.getId());  
                    System.out.println("    Observaciones: " + prestamo.getObservaciones());  
                    System.out.println("    Importe: " + prestamo.getImporte());  
                    System.out.println("    Cuotas: " + prestamo.getCuotas());  
                    System.out.println("    Valor Cuotas: " + prestamo.getValorCuotas());  
                    System.out.println("    Estado: " + prestamo.getEstado());  
                    System.out.println("    Fecha Alta: " + prestamo.getFechaAlta());  
                    
                    // Mostrar cuotas del préstamo  
                    System.out.println("    Cuotas:");  
                    for (CuotaDTO cuota : cuenta.getCuotas()) {  
                        System.out.println("      - ID Cuota: " + cuota.getId());  
                        System.out.println("        Número Cuota: " + cuota.getNroCuota());  
                        System.out.println("        Monto: " + cuota.getMonto());  
                        System.out.println("        Fecha de Pago: " + cuota.getFechaPago());  
                        System.out.println("        Estado de Pago: " + cuota.isEstadoPago());  
                    }  
                }  
                System.out.println("===================================");  
            }  
        } else {  
            System.out.println("No se encontraron cuentas para el cliente con ID: " + idCliente);  
        }  
    	
    }
    
}

 