package principal;

import java.util.List;

import datos.CuentaDao;
import datos.PrestamoDao;
import datosImpl.CuentaDaoImpl;
import datosImpl.PrestamoDaoImpl;
import entidadDTO.CuentaDTO;
import entidadDTO.CuotaDTO;
import entidadDTO.MovimientoDTO;
import entidadDTO.PrestamoDTO;






public class Principal {

    public static void main(String[] args) {
    	CuentaDao cuentaDao = new CuentaDaoImpl();
    	System.out.println(cuentaDao.existeEsteCbu("2025020712301"));


    }
    
}

 