package datosImpl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public class ClienteDaoImpl implements ClienteDao {
	private Conexion cn;
	public ClienteDaoImpl() {
		cn = new Conexion();
	}
	@Override
	public Cliente leerUnCliente(int id) {
		Cliente cliente = new Cliente();
		cn.Open();
		String query = "{CALL SP_ObtenerUnCliente(?)}";
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, id);
			ResultSet rs = cst.executeQuery();
			if(rs.next())
			{
				cliente.setId(rs.getInt("id"));
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.getPaisNacimiento().setId(rs.getInt("id_pais"));
				cliente.getPaisNacimiento().setNombre(rs.getString("nombre_pais"));
				cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
				cliente.setDireccion(rs.getString("direccion"));
				cliente.getLocalidad().setId(rs.getInt("id_localidad"));
				cliente.getLocalidad().setNombre(rs.getString("nombre_localidad"));
				cliente.getProvincia().setId(rs.getInt("id_provincia"));
				cliente.getProvincia().setNombre(rs.getString("nombre_provincia"));
				cliente.setCorreo(rs.getString("correo"));
				cliente.setTelefono(rs.getString("telefono"));
			
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}	
		return cliente;
	}


	
	@Override
	public List<Cliente> ObtenerClientes() {
	    List<Cliente> listaClientes = new ArrayList<>();
	    final String query = "{CALL ObtenerClientes()}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("IdCliente"));
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cliente.setSexo(rs.getString("Sexo"));

	            // Evitar NullPointerException creando instancias si son necesarias
	            if (cliente.getPaisNacimiento() == null) {
	                cliente.setPaisNacimiento(new Pais());
	            }
	            cliente.getPaisNacimiento().setNombre(rs.getString("Pais"));

	            if (cliente.getProvincia() == null) {
	                cliente.setProvincia(new Provincia());
	            }
	            cliente.getProvincia().setNombre(rs.getString("Provincia"));

	            if (cliente.getLocalidad() == null) {
	                cliente.setLocalidad(new Localidad());
	            }
	            cliente.getLocalidad().setNombre(rs.getString("Localidad"));

	            cliente.setFechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
	            cliente.setDireccion(rs.getString("Direccion"));
	            cliente.setCorreo(rs.getString("Correo"));
	            cliente.setTelefono(rs.getString("Telefono"));

	            listaClientes.add(cliente);
	        }
	    } catch (Exception e) {
	        // Usar un sistema de logging para registrar el error
	        System.err.println("Error al obtener la lista de clientes: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return listaClientes;
	}



	@Override
	public boolean modificarCliente(Cliente cliente) {
		cn.Open();
		String query = "{SP_ModificarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		boolean exito = false;
		try
		{
			CallableStatement cst = cn.connection.prepareCall(query);
			cst.setInt(1, cliente.getId());
			cst.setString(2, cliente.getDni());
			cst.setString(3, cliente.getCuil());
			cst.setString(4, cliente.getNombre());
			cst.setString(5, cliente.getApellido());
			cst.setString(6, cliente.getSexo());
			cst.setInt(7, cliente.getPaisNacimiento().getId());
			cst.setDate(8, Date.valueOf(cliente.getFechaNacimiento()));
			cst.setString(9, cliente.getDireccion());
			cst.setInt(10, cliente.getLocalidad().getId());
			cst.setInt(11, cliente.getProvincia().getId());
			cst.setString(12, cliente.getCorreo());
			cst.setString(13, cliente.getTelefono());
			exito = cst.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return exito;
	}

}
