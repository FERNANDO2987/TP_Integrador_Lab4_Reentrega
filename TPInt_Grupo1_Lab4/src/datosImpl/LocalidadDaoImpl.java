package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.LocalidadDao;
import entidad.Localidad;


public class LocalidadDaoImpl implements LocalidadDao {
	
	private Conexion cn;
	public LocalidadDaoImpl() {
		cn = new Conexion();
	}


	@Override
	public List<Localidad> listarLocalidades() {
		   List<Localidad> localidades = new ArrayList<>();
	    cn.Open();
	    String query = "{CALL ObtenerLocalidades()}";

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Localidad localidad = new Localidad();

	            // Asignación de propiedades
	            localidad.setId(rs.getInt("id"));
	            localidad.setNombre(rs.getString("nombre"));
	            
	            // Agregar el pais a la lista
	            localidades.add(localidad);
	        }

	    } catch (Exception e) {
	        // Registro de la excepción
	        System.err.println("Error al listar las localidades: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return localidades;
	}

}
