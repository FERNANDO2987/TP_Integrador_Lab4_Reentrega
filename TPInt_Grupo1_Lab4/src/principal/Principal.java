package principal;
import java.util.ArrayList;

import entidad.Provincia;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;


public class Principal {

    public static void main(String[] args) {

    	
    
        ProvinciaNegImpl provinciaNegImpl = new ProvinciaNegImpl();
        ArrayList<Provincia> listaProvincias = provinciaNegImpl.ListarProvincias();
        		
        
        for (Provincia provincia : listaProvincias) {
            System.out.println("ID: " + provincia.getId() + ", Nombre: " + provincia.getNombre());
        }
        
        
       
    
    }
}
