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
import entidad.Cuota;
import entidad.Movimiento;
import entidad.Prestamo;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidadDTO.ClienteDTO;
import entidadDTO.CuentaDTO;
import entidadDTO.CuotaDTO;
import entidadDTO.MovimientoDTO;
import entidadDTO.PrestamoDTO;
import entidadDTO.TipoCuentaDTO;
import entidadDTO.TipoMovimientoDTO;

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
	            prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
	            prestamo.setObservaciones(rs.getString("Tipo_Prestamo"));
	            
	            
	            
	          

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
	
	
	  //  para obtener los montos solicitados y adjudicados para estado 'pendiente'
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
            System.err.println("Error al aprobar el prestamo: " + e.getMessage());
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


	
	@Override
	public List<Prestamo> obtenerMovimientosPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) {
	    List<Prestamo> movimientos = new ArrayList<>();
	    cn.Open();
	    
	    String query = "{CALL ObtenerMovimientosPorFecha(?, ?)}"; // Llamado al Stored Procedure

	    try (CallableStatement cst = cn.connection.prepareCall(query)) {
	        cst.setDate(1, java.sql.Date.valueOf(fechaDesde));
	        cst.setDate(2, java.sql.Date.valueOf(fechaHasta));

	        try (ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	                // Datos comunes
	                int id = rs.getInt("id_movimiento");
	                String tipoMovimiento = rs.getString("tipo_movimiento");
	                String nombre = rs.getString("nombre_cliente");
	                String apellido = rs.getString("apellido_cliente");
	                int nroCuenta = rs.getInt("nro_cuenta");
	                BigDecimal importe = rs.getBigDecimal("importe");
	                LocalDate fechaMovimiento = rs.getDate("fecha_movimiento").toLocalDate();
	                String descripcion = rs.getString("descripcion_movimiento");

	                // Crear cliente y cuenta
	                Cliente cliente = new Cliente();
	                cliente.setNombre(nombre);
	                cliente.setApellido(apellido);

	                Cuenta cuenta = new Cuenta();
	                cuenta.setNroCuenta(nroCuenta);
	                cuenta.setCliente(cliente);

	                // Crear objeto de pr�stamo (o movimiento gen�rico)
	                Prestamo movimiento = new Prestamo();
	                movimiento.setId(id);
	                movimiento.setCuenta(cuenta);
	                movimiento.setCliente(cliente);
	                movimiento.setImporte(importe);
	                movimiento.setFechaAlta(fechaMovimiento);
	                movimiento.setObservaciones(descripcion);
	                movimiento.setEstado(tipoMovimiento); // Guardamos el tipo de movimiento en "estado"

	                movimientos.add(movimiento);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return movimientos;
	}


	public List<CuentaDTO> obtenerDatosCliente(int idCliente) {  
	    List<CuentaDTO> cuentas = new ArrayList<>();  
	    cn.Open();  

	    String query = "SELECT " +  
	            "c.nro_cuenta, c.id_cliente, tc.descripcion AS tipo_cuenta, c.cbu, c.saldo, " +  
	            "m.id AS id_movimiento, m.detalle, m.importe, m.nro_cuenta AS cuenta_mov, tm.descripcion AS tipo_movimiento, " +  
	            "p.id AS id_prestamo, p.observaciones, p.importe AS importe_prestamo, p.cuotas, p.valor_cuotas, " +  
	            "p.estado AS estado_prestamo, p.fecha_alta, p.nro_cuenta AS cuenta_prestamo, " +  
	            "cu.id AS id_cuota, cu.id_prestamo, cu.nro_cuota, cu.monto, cu.fecha_pago, cu.estado_pago " +  
	            "FROM cuentas c " +  
	            "LEFT JOIN tipos_cuenta tc ON c.id_tipo_cuenta = tc.id " +  
	            "LEFT JOIN movimientos m ON m.nro_cuenta = c.nro_cuenta AND m.deleted = 0 " +  
	            "LEFT JOIN tipos_movimiento tm ON m.id_tipos_movimiento = tm.id " +  
	            "LEFT JOIN prestamos p ON p.id_cliente = c.id_cliente AND p.deleted = 0 " +  
	            "LEFT JOIN cuotas cu ON cu.id_prestamo = p.id AND cu.deleted = 0 " +  
	            "WHERE c.id_cliente = ? AND c.deleted = 0";  

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {  
	        pst.setInt(1, idCliente);  
	        try (ResultSet rs = pst.executeQuery()) {  
	            
	            Map<Integer, CuentaDTO> cuentaMap = new HashMap<>();  

	            while (rs.next()) {  
	                int nroCuenta = rs.getInt("nro_cuenta");  

	                CuentaDTO cuenta = cuentaMap.get(nroCuenta);  
	                if (cuenta == null) {  
	                    cuenta = new CuentaDTO();  
	                    cuenta.setNroCuenta(nroCuenta);  
	                    cuenta.setCbu(rs.getString("cbu"));  
	                    cuenta.setSaldo(rs.getBigDecimal("saldo"));  

	                    ClienteDTO cliente = new ClienteDTO();  
	                    cliente.setId(rs.getInt("id_cliente"));  
	                    cuenta.setCliente(cliente);  

	                    TipoCuentaDTO tipoCuenta = new TipoCuentaDTO();  
	                    tipoCuenta.setDescripcion(rs.getString("tipo_cuenta"));  
	                    cuenta.setTipoCuenta(tipoCuenta);  

	                    cuentaMap.put(nroCuenta, cuenta);  
	                    cuentas.add(cuenta);  
	                }  

	                // Movimiento  
	                int idMovimiento = rs.getInt("id_movimiento");  
	                if (idMovimiento > 0) {
	                    MovimientoDTO movimiento = new MovimientoDTO();
	                    movimiento.setId(idMovimiento);
	                    movimiento.setDetalle(rs.getString("detalle"));
	                    movimiento.setImporte(rs.getBigDecimal("importe"));
	                    movimiento.setNroCuenta(rs.getInt("cuenta_mov"));

	                    TipoMovimientoDTO tipoMovimiento = new TipoMovimientoDTO();
	                    tipoMovimiento.setDescripcion(rs.getString("tipo_movimiento"));
	                    movimiento.setTipoMovimiento(tipoMovimiento);

	                    cuenta.getMovimientos().add(movimiento); // A�adir movimiento a la cuenta
	                }


	                // Prestamo  
	                int idPrestamo = rs.getInt("id_prestamo");  
	                if (idPrestamo > 0) {  
	                    PrestamoDTO prestamo = new PrestamoDTO();  
	                    prestamo.setId(idPrestamo);  
	                    prestamo.setObservaciones(rs.getString("observaciones"));  
	                    prestamo.setImporte(rs.getBigDecimal("importe_prestamo"));  
	                    prestamo.setCuotas(rs.getInt("cuotas"));  
	                    prestamo.setValorCuotas(rs.getBigDecimal("valor_cuotas"));  
	                    prestamo.setEstado(rs.getString("estado_prestamo"));  
	                    prestamo.setFechaAlta(rs.getDate("fecha_alta").toLocalDate());  
	                    
	                    cuenta.getPrestamos().add(prestamo);

	                }

	                    // Cuota  
	                    int idCuota = rs.getInt("id_cuota");  
	                    if (idCuota > 0) {  
	                        CuotaDTO cuota = new CuotaDTO();  
	                        cuota.setId(idCuota);  
	                        cuota.setNroCuota(rs.getInt("nro_cuota"));  
	                        cuota.setMonto(rs.getBigDecimal("monto"));  
	                        cuota.setFechaPago(rs.getTimestamp("fecha_pago") != null ? rs.getTimestamp("fecha_pago").toLocalDateTime() : null);  
	                        cuota.setEstadoPago(rs.getBoolean("estado_pago"));  
	                        
	                        cuenta.getCuotas().add(cuota);
	                       

	                    } 
	                        
	                    }  
	                }  
	             
	         
	    } catch (SQLException e) {  
	        e.printStackTrace();  
	    } finally {  
	        cn.close();  
	    }  

	    return cuentas;  
	}
        


}
