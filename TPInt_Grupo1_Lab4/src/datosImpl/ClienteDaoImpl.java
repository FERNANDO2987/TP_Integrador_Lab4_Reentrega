package datosImpl;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public boolean agregarOmodifcarCliente(Cliente cliente) {
	    boolean resultado = false;
	    final String query = "{CALL AgregarOModifcarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
	    
	    System.out.println("Conectando a la base de datos...");
	    cn.Open();
	    
	    try (CallableStatement cst = cn.connection.prepareCall(query)) {
	        // Verificación de parámetros
	        if (cliente.getId() != 0) {
	            System.out.println("Cliente con ID existente: " + cliente.getId());
	            cst.setInt(1, cliente.getId());
	        } else {
	            System.out.println("Nuevo cliente, ID es 0 (pasando NULL).");
	            cst.setNull(1, java.sql.Types.INTEGER); // Pasar NULL si es un nuevo cliente
	        }
	        
	        System.out.println("Estableciendo los otros parámetros...");
	        // Establecer los otros parámetros de entrada
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
	        
	        // Ejecutar la consulta y verificar si el resultado fue exitoso
	        resultado = cst.executeUpdate() > 0;
	        System.out.println("Resultado de la ejecución: " + resultado);
	        
	    } catch (SQLException e) {
	        System.err.println("Error al agregar o modificar el cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return resultado;
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		cn.Open();
		String query = "CALL SP_ModificarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
