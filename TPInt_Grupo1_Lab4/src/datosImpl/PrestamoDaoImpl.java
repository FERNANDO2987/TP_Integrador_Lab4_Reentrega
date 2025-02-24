package datosImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Prestamo;



public class PrestamoDaoImpl implements PrestamoDao{
	
	private Conexion cn;
	public PrestamoDaoImpl() {
		cn = new Conexion();
	}
	@Override
	public List<Prestamo> ListarPrestamos() {
		List<Prestamo> prestamos = new ArrayList<>();
		cn.Open();
		String query = "{CALL SP_ListarPrestamos()}";
		
		try(CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query);
				ResultSet rs = cst.executeQuery()) {
			
			while (rs.next()) {
				
					//cliente
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String correo = rs.getString("correo");
				String telefono = rs.getString("telefono");
					//cuenta
				String cbu = rs.getString("cbu");
				int idCliente = rs.getInt("id_cliente");
				int nro_cuenta = rs.getInt("nro_cuenta");
					//prestamo
				LocalDate fechaSolicitud = rs.getDate("Fecha_solicitud").toLocalDate();
				BigDecimal importe = rs.getBigDecimal("importe");
				int cuotas = rs.getInt("cuotas");
				String observaciones = rs.getString("observaciones");
				String estado = rs.getString("p.estado");
				
				Prestamo prestamo = new Prestamo();
	            Cliente cliente = new Cliente();
	            cliente.setId(idCliente);
	            cliente.setNombre(nombre);
	            cliente.setApellido(apellido);
	            cliente.setCorreo(correo);
	            cliente.setTelefono(telefono);
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNroCuenta(nro_cuenta);
	            cuenta.setCbu(cbu);
	            cuenta.setCliente(cliente);
	            
	            prestamo.setId(id);
	            prestamo.setCuenta(cuenta);
	            prestamo.setCliente(cliente);
	            prestamo.setImporte(importe);
	            prestamo.setCuotas(cuotas);
	            prestamo.setFechaAlta(fechaSolicitud);
	            prestamo.setEstado(estado);
	            prestamo.setObservaciones(observaciones);
	            
	            prestamos.add(prestamo);

				
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			cn.close();
		}
		
		return prestamos;
	}
	@Override
	public boolean RechazarPrestamo(int idPrestamo, String observacion) {
		boolean estado = true;
		cn.Open();
		
		String query = "{CALL SP_RechazarPrestamo(?,?)}";
		
		try(CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {
			
			cst.setInt(1, idPrestamo);
			cst.setString(2, observacion);
			cst.executeUpdate();
			
		} catch(SQLException e){
			estado = false;
			e.printStackTrace();
		} finally {
			cn.close();
		}

		
		return estado;
	}
	
	@Override
	public boolean AprobarPrestamo(int idPrestamo, String observacion) {
		boolean estado = true;
		cn.Open();
		
		String query = "{CALL SP_AprobarPrestamo(?,?)}";
		
		try(CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {
			
			cst.setInt(1, idPrestamo);
			cst.setString(2, observacion);
			cst.executeUpdate();
			
		} catch(SQLException e){
			estado = false;
			e.printStackTrace();
		} finally {
			cn.close();
		}
		
		return estado;
	}
	
	@Override
	public boolean AgregarPrestamo(Prestamo prestamo) {
		boolean estado = true;
		cn.Open();
		String query = "{CALL SP_AgregarPrestamo(?, ?, ?, ?, ?)}";
		
		try(CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {
			cst.setInt(1, prestamo.getCliente().getId());
			cst.setInt(2, prestamo.getCuenta().getNroCuenta());
			cst.setBigDecimal(3, prestamo.getImporte());
			cst.setInt(4, prestamo.getCuotas());
			cst.setString(5, prestamo.getObservaciones());
			cst.executeUpdate();
			
		} catch(SQLException e){
			estado = false;
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return estado;
	}
	
	@Override
	public boolean ChequearPendiente(int id) {
		boolean estado = false;
	    
	    cn.Open();
	    String query = "SELECT COUNT(id) FROM prestamos WHERE id = ? AND estado = 'pendiente'";
	    
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) { 
	        pst.setInt(1, id);
	        
	        try (ResultSet rs = pst.executeQuery()) { // Ejecutar la consulta correctamente
	            if (rs.next()) { 
	                int count = rs.getInt(1); 
	                estado = (count > 0); // Si count es mayor que 0, el estado ser� true
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al chequear prestamos: " + e.getMessage());	
	    }    
	    return estado;
	}
	
	@Override
	public List<Prestamo> ObtenerPrestamos() {
		List<Prestamo> listaPrestamos = new ArrayList<>();
	    final String query = "{CALL ObtenerPrestamos()}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Prestamo prestamo = new Prestamo();
	            prestamo.setCliente(new Cliente()); // Asegura que Cliente no sea null
	            prestamo.getCliente().setDni(rs.getString("DNI"));
	            prestamo.getCliente().setNombre(rs.getString("Nombre"));
	            prestamo.getCliente().setApellido(rs.getString("Apellido"));
	            prestamo.setId(rs.getInt("ID_Prestamo"));
	            prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
	            prestamo.setCuotas(rs.getInt("Cuotas"));
	            prestamo.setEstado(rs.getString("Estado"));

	            listaPrestamos.add(prestamo);
	        }
	    } catch (Exception e) {
	        System.err.println("Error al obtener la lista de prestamos: " + e.getMessage());

	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	   

	    return listaPrestamos;
	}
	
	@Override
	public Map<String, BigDecimal> obtenerMontosPendientes() {
		Map<String, BigDecimal> montos = new HashMap<>();
        final String query = "SELECT estado, " +
                             "       SUM(importe) AS montoTotalSolicitado, " +
                             "       SUM(importe * valor_cuotas) AS montoTotalAdjudicado " +
                             "FROM bdbanco.prestamos " +
                             "WHERE estado = 'pendiente' " +
                             "GROUP BY estado";
        cn.Open();

        try (CallableStatement cst = cn.connection.prepareCall(query);
             ResultSet rs = cst.executeQuery()) {

            while (rs.next()) {
                BigDecimal montoSolicitado = rs.getBigDecimal("montoTotalSolicitado");
                BigDecimal montoAdjudicado = rs.getBigDecimal("montoTotalAdjudicado");

                montos.put("montoTotalSolicitado", montoSolicitado);
                montos.put("montoTotalAdjudicado", montoAdjudicado);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los montos de pr�stamos pendientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return montos;
	}
	@Override
	public boolean rechazarPrestamo(int idPrestamo) {
		 final String query = "{CALL RechazarPrestamo(?, ?)}"; 
	        cn.Open();
	        boolean resultado = false;

	        try (CallableStatement cst = cn.connection.prepareCall(query)) {
	            cst.setInt(1, idPrestamo);
	            cst.registerOutParameter(2, java.sql.Types.INTEGER);

	            cst.execute();
	            int resultadoSP = cst.getInt(2); // Obtener el valor de retorno

	            resultado = resultadoSP > 0; // Si ROW_COUNT() > 0, el pr�stamo fue aprobado
	        } catch (Exception e) {
	            System.err.println("Error al rechazar el pr�stamo: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }

	        return resultado;
	}
	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		final String query = "{CALL AprobarPrestamo(?, ?)}"; 
        cn.Open();
        boolean resultado = false;

        try (CallableStatement cst = cn.connection.prepareCall(query)) {
            cst.setInt(1, idPrestamo);
            cst.registerOutParameter(2, java.sql.Types.INTEGER);

            cst.execute();
            int resultadoSP = cst.getInt(2); // Obtener el valor de retorno

            resultado = resultadoSP > 0; // Si ROW_COUNT() > 0, el pr�stamo fue aprobado
        } catch (Exception e) {
            System.err.println("Error al aprobar el pr�stamo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return resultado;
	}
	@Override
	public List<Prestamo> ObtenerTodosLosPrestamos() {
		List<Prestamo> listaPrestamos = new ArrayList<>();
	    final String query = "{CALL ObtenerTodosLosPrestamos()}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query);
	         ResultSet rs = cst.executeQuery()) {

	        while (rs.next()) {
	            Prestamo prestamo = new Prestamo();
	            prestamo.setCliente(new Cliente()); // Asegura que Cliente no sea null
	            prestamo.getCliente().setDni(rs.getString("DNI"));
	            prestamo.getCliente().setNombre(rs.getString("Nombre"));
	            prestamo.getCliente().setApellido(rs.getString("Apellido"));
	            prestamo.setId(rs.getInt("ID_Prestamo"));
	            prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
	            prestamo.setCuotas(rs.getInt("Cuotas"));
	            prestamo.setEstado(rs.getString("Estado"));

	            listaPrestamos.add(prestamo);
	        }
	    } catch (Exception e) {
	        System.err.println("Error al obtener la lista de prestamos: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return listaPrestamos;
	}


	



}
