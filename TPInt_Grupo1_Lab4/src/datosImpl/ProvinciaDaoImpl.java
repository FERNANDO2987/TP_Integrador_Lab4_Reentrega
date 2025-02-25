package datosImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.ProvinciaDao;
import entidad.Pais;
import entidad.Provincia;
import entidad.ProvinciaConClientes;

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
	
	

	 @Override
	    public List<ProvinciaConClientes> obtenerCantidadClientesPorProvincia() {
	        List<ProvinciaConClientes> lista = new ArrayList<>();
	        String query = "{CALL ObtenerCantidadClientesPorProvincia()}"; // Llamada al SP

	        cn.Open(); // Asegurar conexión antes de ejecutar

	        try (CallableStatement cst = cn.connection.prepareCall(query);
	             ResultSet rs = cst.executeQuery()) {

	            while (rs.next()) {
	                // Crear instancia de Provincia con el ID corregido
	                Provincia provincia = new Provincia();
	                provincia.setId(rs.getInt("IdProvincia")); // Corregido
	                provincia.setNombre(rs.getString("Provincia"));

	                // Obtener la cantidad de clientes
	                int cantidadClientes = rs.getInt("CantidadClientes");

	                // Crear instancia de ProvinciaConClientes
	                ProvinciaConClientes provinciaConClientes = new ProvinciaConClientes(provincia, cantidadClientes);

	                // Agregar a la lista
	                lista.add(provinciaConClientes);
	            }

	        } catch (Exception e) {
	            System.err.println("Error al obtener la cantidad de clientes por provincia:");
	            e.printStackTrace(); // Muestra el error detallado en consola
	        } finally {
	            cn.close(); // Asegurar cierre de la conexión
	        }

	        return lista;
	    }



	
}
