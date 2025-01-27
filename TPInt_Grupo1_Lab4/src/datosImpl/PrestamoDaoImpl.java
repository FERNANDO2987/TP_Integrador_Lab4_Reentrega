package datosImpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import datos.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
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

}
