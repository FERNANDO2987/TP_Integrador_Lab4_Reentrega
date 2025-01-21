package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{

	private Conexion cn;
	public ProvinciaDaoImpl() {
		cn = new Conexion();
	}
	@Override
	public List<Provincia> listarProvincias() {
		   List<Provincia> provincias = new ArrayList<>();
		    cn.Open();
		    String query = "{CALL ObtenerProvincias()}";

		    try (CallableStatement cst = cn.connection.prepareCall(query);
		         ResultSet rs = cst.executeQuery()) {

		        while (rs.next()) {
		        	Provincia provincia = new Provincia();

		            // Asignación de propiedades
		        	provincia.setId(rs.getInt("id"));
		        	provincia.setNombre(rs.getString("nombre"));
		            
		            // Agregar el provincias a la lista
		        	provincias.add(provincia);
		        }

		    } catch (Exception e) {
		        // Registro de la excepción
		        System.err.println("Error al listar las provincias: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }

		    return provincias;
	}
	
}
