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
import entidad.TipoCuenta;

import entidadDTO.PrestamoDTO;

import java.util.HashMap;

import java.util.Map;

public class PrestamoDaoImpl implements PrestamoDao {

	private Conexion cn;

	public PrestamoDaoImpl() {
		cn = new Conexion();
	}

	@Override
	public List<Prestamo> ListarPrestamos() {
		List<Prestamo> prestamos = new ArrayList<>();
		cn.Open();
		String query = "{CALL SP_ListarPrestamos()}";

		try (CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query);
				ResultSet rs = cst.executeQuery()) {

			while (rs.next()) {

				// cliente
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String correo = rs.getString("correo");
				String telefono = rs.getString("telefono");
				// cuenta
				String cbu = rs.getString("cbu");
				int idCliente = rs.getInt("id_cliente");
				int nro_cuenta = rs.getInt("nro_cuenta");
				// prestamo
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return prestamos;
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		Prestamo prestamo = null;
		cn.Open();
		String query = "{CALL ObtenerPrestamoPorId(?)}";

		try (CallableStatement cst = cn.connection.prepareCall(query)) {
			cst.setInt(1, idPrestamo);
			System.out.println("Ejecutando SP con ID: " + idPrestamo);

			try (ResultSet rs = cst.executeQuery()) {
				if (rs.next()) {
					System.out.println("Datos encontrados para ID: " + idPrestamo);

					// Cliente
					Cliente cliente = new Cliente();
					cliente.setId(rs.getInt("ID_Cliente"));
					cliente.setDni(rs.getString("DNI"));
					cliente.setNombre(rs.getString("Nombre"));
					cliente.setApellido(rs.getString("Apellido"));

					// Cuenta
					Cuenta cuenta = new Cuenta();
					cuenta.setNroCuenta(rs.getInt("Nro_Cuenta")); // Dejarlo como String
					cuenta.setSaldo(rs.getBigDecimal("Saldo_Cuenta") != null ? rs.getBigDecimal("Saldo_Cuenta")
							: BigDecimal.ZERO);

					// Prï¿½stamo
					prestamo = new Prestamo();
					prestamo.setId(idPrestamo);
					prestamo.setObservaciones(rs.getString("Tipo_Prestamo"));
					prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
					prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
					prestamo.setCuotas(rs.getInt("Cuotas"));
					prestamo.setValorCuotas(rs.getBigDecimal("Valor_Cuotas") != null ? rs.getBigDecimal("Valor_Cuotas")
							: BigDecimal.ZERO);
					prestamo.setEstado(rs.getString("Estado"));

					// Asignar Cliente y Cuenta
					prestamo.setCliente(cliente);
					prestamo.setCuenta(cuenta);
				} else {
					System.out.println("No se encontraron ningun prestamo con ID: " + idPrestamo);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error SQL: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Error al obtener prestamo por ID", e);
		} finally {
			cn.close();
		}

		return prestamo;
	}

	@Override
	public boolean RechazarPrestamo(int idPrestamo, String observacion) {
		boolean estado = true;
		cn.Open();

		String query = "{CALL SP_RechazarPrestamo(?,?)}";

		try (CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {

			cst.setInt(1, idPrestamo);
			cst.setString(2, observacion);
			cst.executeUpdate();

		} catch (SQLException e) {
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

		try (CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {

			cst.setInt(1, idPrestamo);
			cst.setString(2, observacion);
			cst.executeUpdate();

		} catch (SQLException e) {
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

		try (CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {
			cst.setInt(1, prestamo.getCliente().getId());
			cst.setInt(2, prestamo.getCuenta().getNroCuenta());
			cst.setBigDecimal(3, prestamo.getImporte());
			cst.setInt(4, prestamo.getCuotas());
			cst.setString(5, prestamo.getObservaciones());
			cst.executeUpdate();

		} catch (SQLException e) {
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
					estado = (count > 0); // Si count es mayor que 0, el estado serï¿½ true
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

		try {
			if (cn == null) {
				throw new IllegalStateException("La conexión 'cn' es nula.");
			}

			cn.Open();

			if (cn.connection == null) {
				throw new IllegalStateException("La conexión a la base de datos no se ha establecido correctamente.");
			}

			try (CallableStatement cst = cn.connection.prepareCall(query); ResultSet rs = cst.executeQuery()) {

				while (rs.next()) {
					Prestamo prestamo = new Prestamo();
					prestamo.setCliente(new Cliente()); // Asegura que Cliente no sea null

					prestamo.getCliente().setDni(rs.getString("DNI") != null ? rs.getString("DNI") : "Desconocido");
					prestamo.getCliente().setNombre(rs.getString("Nombre") != null ? rs.getString("Nombre") : "N/A");
					prestamo.getCliente()
							.setApellido(rs.getString("Apellido") != null ? rs.getString("Apellido") : "N/A");

					prestamo.setId(rs.getInt("ID_Prestamo"));
					prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
					prestamo.setCuotas(rs.getInt("Cuotas"));
					prestamo.setEstado(rs.getString("Estado") != null ? rs.getString("Estado") : "Desconocido");

					if (rs.getDate("Fecha_Solicitud") != null) {
						prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
					} else {
						prestamo.setFechaAlta(null); // O algún valor por defecto
					}

					prestamo.setObservaciones(rs.getString("Tipo_Prestamo") != null ? rs.getString("Tipo_Prestamo")
							: "Sin observaciones");

					listaPrestamos.add(prestamo);
				}

			}
		} catch (Exception e) {
			System.err.println("Error al obtener la lista de prestamos: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (cn != null) {
				cn.close();
			}
		}

		return listaPrestamos;
	}

	// para obtener los montos solicitados y adjudicados para estado 'pendiente'
	public Map<String, BigDecimal> obtenerMontosPendientes() {
		Map<String, BigDecimal> montos = new HashMap<>();
		final String query = "SELECT estado, " + "       SUM(importe) AS montoTotalSolicitado, "
				+ "       SUM(importe * valor_cuotas) AS montoTotalAdjudicado " + "FROM bdbanco.prestamos "
				+ "WHERE estado = 'pendiente' " + "GROUP BY estado";
		cn.Open();

		try (CallableStatement cst = cn.connection.prepareCall(query); ResultSet rs = cst.executeQuery()) {

			while (rs.next()) {
				BigDecimal montoSolicitado = rs.getBigDecimal("montoTotalSolicitado");
				BigDecimal montoAdjudicado = rs.getBigDecimal("montoTotalAdjudicado");

				montos.put("montoTotalSolicitado", montoSolicitado);
				montos.put("montoTotalAdjudicado", montoAdjudicado);
			}
		} catch (Exception e) {
			System.err.println("Error al obtener los montos de prï¿½stamos pendientes: " + e.getMessage());
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

			resultado = resultadoSP > 0; // Si ROW_COUNT() > 0, el prï¿½stamo fue aprobado
		} catch (Exception e) {
			System.err.println("Error al rechazar el prï¿½stamo: " + e.getMessage());
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

			resultado = resultadoSP > 0; // Si ROW_COUNT() > 0, el prï¿½stamo fue aprobado
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

		try (CallableStatement cst = cn.connection.prepareCall(query); ResultSet rs = cst.executeQuery()) {

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

					// Crear objeto de prï¿½stamo (o movimiento genï¿½rico)
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


	public List<PrestamoDTO> listarPrestamosPorCliente(int clienteId) {
	    List<PrestamoDTO> prestamos = new ArrayList<>();

	    cn.Open(); // Abrimos la conexión
	    String query = "{CALL ObtenerPrestamosPorCliente(?)}";

	    try (CallableStatement cst = cn.connection.prepareCall(query); ResultSet rs = cst.executeQuery()) {
	            while (rs.next()) {
	                // Datos del préstamo
	                int idPrestamo = rs.getInt("id");
	                String tipoMovimiento = rs.getString("Tipo_Prestamo");
	                int idCliente = rs.getInt("ID_Cliente");
	                int nroCuenta = rs.getInt("Nro_Cuenta"); // ✅ Corregido: Se obtiene como int

	                LocalDate fechaSolicitud = (rs.getDate("Fecha_Solicitada_Prestamo") != null)
	                        ? rs.getDate("Fecha_Solicitada_Prestamo").toLocalDate()
	                        : null;

	                BigDecimal importe = rs.getBigDecimal("Importe_Prestamo_Solicitado");
	                int cuotas = rs.getInt("Cantidad_Cuotas_APagar");
	                BigDecimal valorCuotas = rs.getBigDecimal("Valor_Cuota_Prestamo");
	                String estado = rs.getString("Estado_Prestamo");

	                // Datos de la cuenta
	                int nroCuentaAsociada = rs.getInt("Cuenta_Asociada"); // ✅ Corregido: Se obtiene como int
	                String tipoCuentaDescripcion = rs.getString("Tipo_Cuenta");
	                String cbu = rs.getString("CBU");
	                BigDecimal saldo = rs.getBigDecimal("Saldo");

	                // Creación de objetos
	                TipoCuenta tipoCuenta = new TipoCuenta();
	                tipoCuenta.setDescripcion(tipoCuentaDescripcion);

	                Cuenta cuenta = new Cuenta();
	                cuenta.setNroCuenta(nroCuentaAsociada); // ✅ Corregido: Ahora es int
	                cuenta.setCbu(cbu);
	                cuenta.setSaldo(saldo);
	                cuenta.setTipoCuenta(tipoCuenta);

	                Cliente cliente = new Cliente();
	                cliente.setId(idCliente); // ✅ Correcto

	                // Asignar valores a PrestamoDTO
	                PrestamoDTO prestamoDTO = new PrestamoDTO();
	                prestamoDTO.setId(idPrestamo);
	                prestamoDTO.setCliente(cliente);
	                prestamoDTO.setObservaciones(tipoMovimiento);
	                prestamoDTO.setFechaAlta(fechaSolicitud);
	                prestamoDTO.setImporte(importe);
	                prestamoDTO.setCuotas(cuotas);
	                prestamoDTO.setValorCuotas(valorCuotas);
	                prestamoDTO.setEstado(estado);
	                prestamoDTO.setCuenta(cuenta);

	                // Agregar a la lista
	                prestamos.add(prestamoDTO);
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return prestamos;
	}




	// Método para pagar una cuota del préstamo
	public String pagarCuota(int idPrestamo) {
		String mensaje = null;
		cn.Open();
		String query = "{CALL PagarCuota(?)}";

		try (CallableStatement cst = (CallableStatement) cn.connection.prepareCall(query)) {
			cst.setInt(1, idPrestamo);

			boolean hasResults = cst.execute();

			if (hasResults) {
				try (ResultSet rs = cst.getResultSet()) {
					if (rs.next()) {
						mensaje = rs.getString("mensaje");
					}
				}
			}
		} catch (SQLException e) {
			mensaje = "Error en la base de datos: " + e.getMessage();
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return mensaje;
	}

	@Override
	public List<Prestamo> listarPrestamosPorClientesAprobados(int clienteId) {
	    List<Prestamo> listaPrestamos = new ArrayList<>();
	    final String query = "{CALL ListarPrestamosPorCliente(?)}"; // Llamada al SP

	    try {
	        if (cn == null) {
	            throw new IllegalStateException("La conexión 'cn' es nula.");
	        }

	        cn.Open();

	        if (cn.connection == null) {
	            throw new IllegalStateException("La conexión a la base de datos no se ha establecido correctamente.");
	        }

	        try (CallableStatement cst = cn.connection.prepareCall(query)) {
	            cst.setInt(1, clienteId); // Asigna el parámetro de entrada al SP

	            try (ResultSet rs = cst.executeQuery()) {
	                while (rs.next()) {
	                    Prestamo prestamo = new Prestamo();
	                    prestamo.setCliente(new Cliente()); // Inicializa Cliente para evitar NullPointerException

	                    // Manejo de valores nulos en columnas de Cliente
	                    prestamo.getCliente().setId(rs.getInt("ID_Cliente"));

	                    // Manejo de valores de Prestamo
	                    prestamo.setId(rs.getInt("ID_Prestamo"));
	                    prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
	                    prestamo.setCuotas(rs.getInt("Cuotas"));
	                    prestamo.setEstado(rs.getString("Estado") != null ? rs.getString("Estado") : "Desconocido");
	                    prestamo.setValorCuotas(rs.getBigDecimal("Valor_Cuota"));

	                    if (rs.getDate("Fecha_Solicitud") != null) {
	                        prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
	                    } else {
	                        prestamo.setFechaAlta(null); // Permite valores NULL
	                    }

	                    prestamo.setObservaciones(rs.getString("Tipo_Prestamo") != null ? rs.getString("Tipo_Prestamo") : "Sin observaciones");

	                    listaPrestamos.add(prestamo);
	                }
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Error al obtener los préstamos del cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (cn != null) {
	            cn.close();
	        }
	    }

	    return listaPrestamos;
	}

	@Override
	public List<Prestamo> listarPrestamosPorClientesPendientes(int clienteId) {
		   List<Prestamo> listaPrestamos = new ArrayList<>();
		    final String query = "{CALL ListarPrestamosPorClientesPendientes(?)}"; // Llamada al SP

		    try {
		        if (cn == null) {
		            throw new IllegalStateException("La conexión 'cn' es nula.");
		        }

		        cn.Open();

		        if (cn.connection == null) {
		            throw new IllegalStateException("La conexión a la base de datos no se ha establecido correctamente.");
		        }

		        try (CallableStatement cst = cn.connection.prepareCall(query)) {
		            cst.setInt(1, clienteId); // Asigna el parámetro de entrada al SP

		            try (ResultSet rs = cst.executeQuery()) {
		                while (rs.next()) {
		                    Prestamo prestamo = new Prestamo();
		                    prestamo.setCliente(new Cliente()); // Inicializa Cliente para evitar NullPointerException

		                    // Manejo de valores nulos en columnas de Cliente
		                    prestamo.getCliente().setId(rs.getInt("ID_Cliente"));

		                    // Manejo de valores de Prestamo
		                    prestamo.setId(rs.getInt("ID_Prestamo"));
		                    prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
		                    prestamo.setCuotas(rs.getInt("Cuotas"));
		                    prestamo.setEstado(rs.getString("Estado") != null ? rs.getString("Estado") : "Desconocido");
		                    prestamo.setValorCuotas(rs.getBigDecimal("Valor_Cuota"));

		                    if (rs.getDate("Fecha_Solicitud") != null) {
		                        prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
		                    } else {
		                        prestamo.setFechaAlta(null); // Permite valores NULL
		                    }

		                    prestamo.setObservaciones(rs.getString("Tipo_Prestamo") != null ? rs.getString("Tipo_Prestamo") : "Sin observaciones");

		                    listaPrestamos.add(prestamo);
		                }
		            }
		        }
		    } catch (Exception e) {
		        System.err.println("Error al obtener los préstamos del cliente: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        if (cn != null) {
		            cn.close();
		        }
		    }

		    return listaPrestamos;
	}

	@Override
	public List<Prestamo> listarPrestamosDeClientesPorEstados(int clienteId){
		   List<Prestamo> listaPrestamos = new ArrayList<>();
		    final String query = "{CALL ListarPrestamosDeClientesPorEstados(?)}"; // Llamada al SP

		    try {
		        if (cn == null) {
		            throw new IllegalStateException("La conexión 'cn' es nula.");
		        }

		        cn.Open();

		        if (cn.connection == null) {
		            throw new IllegalStateException("La conexión a la base de datos no se ha establecido correctamente.");
		        }

		        try (CallableStatement cst = cn.connection.prepareCall(query)) {
		            cst.setInt(1, clienteId); // Asigna el parámetro de entrada al SP

		            try (ResultSet rs = cst.executeQuery()) {
		                while (rs.next()) {
		                    Prestamo prestamo = new Prestamo();
		                    prestamo.setCliente(new Cliente()); // Inicializa Cliente para evitar NullPointerException
		                    prestamo.setCuenta(new Cuenta());

		                    // Manejo de valores nulos en columnas de Cliente
		                    prestamo.getCliente().setId(rs.getInt("ID_Cliente"));
		                    
		                    prestamo.getCuenta().setNroCuenta(rs.getInt("Nro_Cuenta"));

		                    // Manejo de valores de Prestamo
		                    prestamo.setId(rs.getInt("ID_Prestamo"));
		                    prestamo.setImporte(rs.getBigDecimal("Monto_Solicitado"));
		               
		                    prestamo.setCuotas(rs.getInt("Cuotas"));
		                    prestamo.setEstado(rs.getString("Estado") != null ? rs.getString("Estado") : "Desconocido");
		                    prestamo.setValorCuotas(rs.getBigDecimal("Valor_Cuota"));

		                    if (rs.getDate("Fecha_Solicitud") != null) {
		                        prestamo.setFechaAlta(rs.getDate("Fecha_Solicitud").toLocalDate());
		                    } else {
		                        prestamo.setFechaAlta(null); // Permite valores NULL
		                    }

		                    prestamo.setObservaciones(rs.getString("Tipo_Prestamo") != null ? rs.getString("Tipo_Prestamo") : "Sin observaciones");

		                    listaPrestamos.add(prestamo);
		                }
		            }
		        }
		    } catch (Exception e) {
		        System.err.println("Error al obtener los préstamos del cliente: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        if (cn != null) {
		            cn.close();
		        }
		    }

		    return listaPrestamos;
	}
	@Override
	public List<Prestamo> ObtenerPrestamosFiltrados(BigDecimal mayorA, BigDecimal menorA) {
	    List<Prestamo> listaPrestamos = new ArrayList<>();
	    final String query = "{CALL ObtenerPrestamosFiltrados(?,?)}";
	    cn.Open();

	    try (CallableStatement cst = cn.connection.prepareCall(query)){
	        
	        cst.setBigDecimal(1, mayorA);
	        cst.setBigDecimal(2, menorA);
	        
	        try (ResultSet rs = cst.executeQuery()) {
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
