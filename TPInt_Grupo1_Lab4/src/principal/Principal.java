package principal;
import java.util.ArrayList;
import java.util.List;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Provincia;
import negocioImpl.ProvinciaNegImpl;


public class Principal {

    public static void main(String[] args) {
    	CuentaDao cuentaDao = new CuentaDaoImpl();
    	List<Cuenta> cuentas = cuentaDao.leerTodasLasCuentas();
    	
    	for(Cuenta cuenta : cuentas)
    	{
    		System.out.println(cuenta.toString());
    	}
    
      
       
    
    }
}
