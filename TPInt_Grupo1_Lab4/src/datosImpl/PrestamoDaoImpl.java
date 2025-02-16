package datosImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
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
	
	
	  // Nuevo método para obtener los montos solicitados y adjudicados para estado 'pendiente'
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
            System.err.println("Error al obtener los montos de préstamos pendientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return montos;
    }


    
    public boolean rechazarPrestamo(int idPrestamo) {
        final String query = "{CALL RechazarPrestamo(?)}";
        cn.Open();
        boolean resultado = false;

        try (CallableStatement cst = cn.connection.prepareCall(query)) {
            cst.setInt(1, idPrestamo);
            int filasAfectadas = cst.executeUpdate();

            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (Exception e) {
            System.err.println("Error al rechazar el préstamo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return resultado;
    }



	@Override
	public boolean aprobarPrestamo(int idPrestamo) {
		 final String query = "{CALL AprobarPrestamo(?)}";
	        cn.Open();
	        boolean resultado = false;

	        try (CallableStatement cst = cn.connection.prepareCall(query)) {
	            cst.setInt(1, idPrestamo);
	            int filasAfectadas = cst.executeUpdate();

	            if (filasAfectadas > 0) {
	                resultado = true;
	            }
	        } catch (Exception e) {
	            System.err.println("Error al aprobar el préstamo: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }

	        return resultado;
	}


}
