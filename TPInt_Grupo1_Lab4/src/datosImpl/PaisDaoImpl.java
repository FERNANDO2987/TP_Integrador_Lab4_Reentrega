package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.PaisDao;
import entidad.Pais;


public class PaisDaoImpl implements PaisDao{

	private Conexion cn;
	public PaisDaoImpl() {
		cn = new Conexion();
	}

	
	
	@Override
	public List<Pais> listarPaises() {
		   List<Pais> paises = new ArrayList<>();
		    cn.Open();
		    String query = "{CALL ObtenerPaises()}";

		    try (CallableStatement cst = cn.connection.prepareCall(query);
		         ResultSet rs = cst.executeQuery()) {

		        while (rs.next()) {
		            Pais pais = new Pais();

		            // Asignación de propiedades
		            pais.setId(rs.getInt("id"));
		            pais.setNombre(rs.getString("nombre"));
		            
		            // Agregar el pais a la lista
		            paises.add(pais);
		        }

		    } catch (Exception e) {
		        // Registro de la excepción
		        System.err.println("Error al listar los paises: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }

		    return paises;
	}





}
