package principal;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.Provincia;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.ProvinciaNegImpl;


public class Principal {

    public static void main(String[] args) {

    	CuentaDao cuentadao = new CuentaDaoImpl();
    	List<Movimiento> movimientos = cuentadao.leerMovimientosDeLaCuenta(2);
    	for(Movimiento mov : movimientos)
    	{
    		System.out.println(mov.toString());
    	}
    	
    
        
    }
}
 