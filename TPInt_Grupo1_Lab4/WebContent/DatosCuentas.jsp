<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"%>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidadDTO.PrestamoDTO"%>

<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>


<%
	// Obtener la lista de préstamos del request
	List<PrestamoDTO> prestamosPendientes = (List<PrestamoDTO>) request.getAttribute("prestamosPendientes");
	List<PrestamoDTO> prestamosAprobados = (List<PrestamoDTO>) request.getAttribute("prestamosAprobados");
	List<PrestamoDTO> datosPrestamos = (List<PrestamoDTO>) request.getAttribute("datosPrestamos");
	String mensajeExito = (String) request.getAttribute("mensajeExito");
	String mensajeError = (String) request.getAttribute("mensajeError");
%>





<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mis Prestamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<style>
.bg-trigo {
	background-color: #F5DEB3;
	color: #4B5563; /* Color gris oscuro */
	padding: 0.5rem 0.75rem;
	border-radius: 0.25rem;
}

.btn-pagar {
	position: relative;
	z-index: 1000;
	pointer-events: auto;
}

.pagination {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.pagination button, .pagination span {
	margin: 0 5px;
	padding: 5px 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
	cursor: pointer;
}

.pagination button:disabled {
	cursor: not-allowed;
	opacity: 0.5;
}

.pagination .active {
	background-color: #007bff;
	color: white;
}

.alert {
	font-size: 1.2rem;
	padding: 20px;
	border-radius: 8px;
	display: flex;
	align-items: center;
	margin-bottom: 16px;
}

.alert-success {
	background-color: #28a745; /* Color verde */
	color: white;
}

.alert-error {
	background-color: #dc3545; /* Color rojo */
	color: white;
}

input:invalid {
	border-color: red;
}

input:required:invalid {
	border-color: red;
}
</style>
</head>
<body class="bg-gray-100 p-6">



	<div class="max-w-6xl mx-auto bg-white p-6 rounded-lg shadow-md mb-12">




		<div class="flex justify-between items-center mb-4">
			<h2 class="text-lg font-semibold text-gray-800">Mis prestamos</h2>
		</div>


		<%
			if (prestamosAprobados != null && !prestamosAprobados.isEmpty()) {
		%>
		<%
			for (PrestamoDTO prestamo : prestamosAprobados) {
		%>


		<div class="bg-gray-50 p-4 rounded-lg mb-4">
			<h3 class="font-semibold text-gray-700">
				Observacion:
				<%=prestamo.getObservaciones()%></h3>
			<p class="text-sm text-gray-600">
				Saldo otorgado: <strong>$ <%=prestamo.getImporte()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				Cuotas pendientes: <strong><%=prestamo.getCuotas()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				ID: <strong><%=prestamo.getId()%></strong>
			</p>
			<div class="flex justify-between items-center mt-2">
				<p class="text-red-500 text-sm">
					Vencimiento:
					<%=prestamo.getFechaAlta()%></p>


				<a href="servletPagarPrestamo?idPrestamo=<%=prestamo.getId()%>"
					class="btn-pagar bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-green-600">
					<i class="fas fa-hand-holding-usd"></i> Pagar
				</a>



			</div>



		</div>

		<%
			}
		%>
		<%
			}

			else if (mensajeError != null) {
		%>
		<div id="successMessage"
			class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-4">
			<i class="fas fa-check-circle"></i>
			<%=mensajeError%>
		</div>
		<%
			}
		%>

		<%
			if (mensajeExito != null) {
		%>
		<div id="errorMessage"
			class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-4">
			<i class="fas fa-exclamation-circle"></i>
			<%=mensajeExito%>
		</div>
		<%
			}
		%>
	</div>


	<div class="max-w-6xl mx-auto bg-white p-6 rounded-lg shadow-md mt-12">



		<div class="flex justify-between items-center mb-4">
			<h2 class="text-lg font-semibold text-gray-800">Pendientes de
				aprobacion</h2>
		</div>



		<%
			if (prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
		%>
		<%
			for (PrestamoDTO prestamo : prestamosPendientes) {
		%>

		<div class="bg-gray-50 p-4 rounded-lg mb-4">
			<h3 class="font-semibold text-gray-700">
				Observacion:
				<%=prestamo.getObservaciones()%></h3>
			<p class="text-sm text-gray-600">
				Monto Solicitado: <strong>$ <%=prestamo.getImporte()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				Fecha Solicitud: <strong><%=prestamo.getFechaAlta()%></strong>
			</p>
			<div class="flex justify-between">
				<p class="text-sm text-gray-600">Estado:</p>
				<p class="text-sm text-gray-600 ml-auto">
					<%
						if (prestamo.getEstado().equalsIgnoreCase("aprobado")) {
					%>
					<span class="bg-blue-500 text-white px-2 py-1 rounded">Aprobado</span>
					<%
						} else if (prestamo.getEstado().equalsIgnoreCase("finalizado")) {
					%>
					<span class="bg-green-500 text-white px-2 py-1 rounded">Finalizado</span>
					<%
						} else if (prestamo.getEstado().equalsIgnoreCase("rechazado")) {
					%>
					<span class="bg-red-500 text-white px-2 py-1 rounded">Rechazado</span>
					<%
						} else if (prestamo.getEstado().equalsIgnoreCase("pendiente")) {
					%>
					<span
						style="background-color: #F5DEB3; color: #4B5563; padding: 0.5rem 0.75rem; border-radius: 0.25rem;">En
						Revision</span>
					<%
						} else {
					%>
					<span class="bg-gray-300 text-gray-800 px-2 py-1 rounded">Desconocido</span>
					<%
						}
					%>
				</p>

			</div>

		</div>

		<%
			}
		%>
		<%
			}

			else if (mensajeError != null) {
		%>
		<div id="errorMessage"
			class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-4">
			<i class="fas fa-check-circle"></i>
			<%=mensajeError%>
		</div>
		<%
			}
		%>

		<%
			if (mensajeExito != null) {
		%>
		<div id="successMessage"
			class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-4">
			<i class="fas fa-exclamation-circle"></i>
			<%=mensajeExito%>
		</div>
		<%
			}
		%>
	</div>


	<div class="max-w-6xl mx-auto bg-white p-6 rounded-lg shadow-md mt-12">
		<h2 class="text-lg font-semibold text-gray-800 mb-4">Historial de
			préstamos</h2>




		<div class="flex justify-between items-center mb-4">
			<div></div>

			<div class="flex space-x-2">
				<select id="filtroEstado" class="border-gray-300 rounded px-2 py-1">
					<option value="">Cualquier estado</option>
					<option value="aprobado">Aprobado</option>
					<option value="pendiente">Pendiente</option>
					<option value="finalizado">Finalizado</option>
					<option value="rechazado">Rechazado</option>
				</select> <select id="filtroMotivo" class="border-gray-300 rounded px-2 py-1">
					<option value="">Cualquier motivo</option>
					<option value="motivo1">Motivo 1</option>
					<option value="motivo2">Motivo 2</option>
				</select>
			</div>

		</div>

		<table class="w-full border-collapse border border-gray-300">
			<thead class="bg-gray-100">
				<tr>
					<th class="border p-2">ID</th>
					<th class="border p-2">Cuenta</th>
					<th class="border p-2">Fecha Solicitud</th>
					<th class="border p-2">Tipo</th>
					<th class="border p-2">Estado</th>
					<th class="border p-2">Monto</th>
					<th class="border p-2">Cuotas</th>
				</tr>
			</thead>
			<tbody id="tableBody">


				<%
					if (datosPrestamos != null) {
						for (PrestamoDTO prestamo : datosPrestamos) {
							// Verificar que los campos relevantes no sean null o valores no válidos  
							if (prestamo.getFechaAlta() != null && prestamo.getObservaciones() != null
									&& prestamo.getImporte() != null && prestamo.getCuotas() != 0
									&& prestamo.getCuenta().getNroCuenta() != 0) {
				%>
				<tr class="border">
					<td class="border p-2"><%=prestamo.getId()%></td>
					<td class="border p-2"><%=prestamo.getCuenta().getNroCuenta()%></td>
					<td class="border p-2"><%=prestamo.getFechaAlta()%></td>
					<td class="border p-2 motivo"><%=prestamo.getObservaciones()%></td>

					<td class="border p-2 estado">
						<%
							if (prestamo.getEstado() != null) {
											if (prestamo.getEstado().equalsIgnoreCase("pendiente")) {
						%> <span class="bg-blue-500 text-white px-2 py-1 rounded">Pendiente</span>
						<%
							} else if (prestamo.getEstado().equalsIgnoreCase("aprobado")) {
						%> <span class="bg-yellow-500 text-white px-2 py-1 rounded">Aprobado</span>
						<%
							} else if (prestamo.getEstado().equalsIgnoreCase("finalizado")) {
						%> <span class="bg-green-500 text-white px-2 py-1 rounded">Finalizado</span>
						<%
							} else if (prestamo.getEstado().equalsIgnoreCase("rechazado")) {
						%> <span class="bg-red-500 text-white px-2 py-1 rounded">Rechazado</span>
						<%
							} else {
						%> <span class="bg-gray-500 text-white px-2 py-1 rounded">Estado
							desconocido</span> <%
 	}
 				}
 %>
					</td>


					<td class="border p-2">$ <%=prestamo.getImporte()%></td>
					<td class="border p-2"><%=prestamo.getCuotas()%></td>
				</tr>
				<%
					} // Cierre de la verificación de datos del prestamo
						} // Cierre del bucle de prestamos
					} else if (mensajeError != null) {
				%>
				<div id="errorMessage"
					class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4 mb-4">
					<i class="fas fa-check-circle"></i>
					<%=mensajeError%>
				</div>
				<%
					}
				%>

				<%
					if (mensajeExito != null) {
				%>
				<div id="successMessage"
					class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-4">
					<i class="fas fa-exclamation-circle"></i>
					<%=mensajeExito%>
				</div>
				<%
					}
				%>


			</tbody>

		</table>
		<div class="pagination mt-4">
			<button onclick="prevPage()" id="btnPrev"
				class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Anterior</button>
			<span id="pageNumbers"></span>
			<button onclick="nextPage()" id="btnNext"
				class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Siguiente</button>
		</div>

		<br> <br> <br> <br>

	</div>



	<script>
		// Llamar a la función mostrarMensaje si se ha definido el mensaje exitoso o de error
	<%if (request.getAttribute("mensajeError") != null) {%>
		mostrarMensaje("successMessage");
	<%} else if (request.getAttribute("mensajeExito") != null) {%>
		mostrarMensaje("errorMessage");
	<%}%>
		function ocultarMensaje() {
			// Ocultar el mensaje de éxito después de 9000 milisegundos (9 segundos)
			var mensaje = document.getElementById("successMessage");
			if (mensaje) {
				setTimeout(function() {
					mensaje.style.display = "none";
				}, 9000);
			}

			// Ocultar el mensaje de error después de 9000 milisegundos (9 segundos)
			var errorMensaje = document.getElementById("errorMessage");
			if (errorMensaje) {
				setTimeout(function() {
					errorMensaje.style.display = "none";
				}, 9000);
			}
		}

		function mostrarMensaje(tipo) {
			var mensaje = document.getElementById(tipo);
			if (mensaje) {
				mensaje.style.display = "block"; // Mostrar el mensaje
				// Ocultar el mensaje después de 5000 milisegundos (5 segundos)
				setTimeout(function() {
					mensaje.style.display = "none";
				}, 5000);
			}
		}

		window.addEventListener("load", function() {
			// Llamar a la función mostrarMensaje cuando se cargue la página
			if (document.getElementById("successMessage")) {
				mostrarMensaje("successMessage");
			} else if (document.getElementById("errorMessage")) {
				mostrarMensaje("errorMessage");
			}

			// Ocultar mensajes si son visibles
			ocultarMensaje();
		});
	</script>
</body>


</body>





<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>

<script>
	// Llamar a la función mostrarMensaje si se ha definido el mensaje exitoso o de error
<%if (request.getAttribute("mensajeError") != null) {%>
	mostrarMensaje("successMessage");
<%} else if (request.getAttribute("mensajeExito") != null) {%>
	mostrarMensaje("errorMessage");
<%}%>
	function ocultarMensaje() {
		// Ocultar el mensaje de éxito después de 9000 milisegundos (9 segundos)
		var mensaje = document.getElementById("successMessage");
		if (mensaje) {
			setTimeout(function() {
				mensaje.style.display = "none";
			}, 9000);
		}

		// Ocultar el mensaje de error después de 9000 milisegundos (9 segundos)
		var errorMensaje = document.getElementById("errorMessage");
		if (errorMensaje) {
			setTimeout(function() {
				errorMensaje.style.display = "none";
			}, 9000);
		}
	}

	function mostrarMensaje(tipo) {
		var mensaje = document.getElementById(tipo);
		if (mensaje) {
			mensaje.style.display = "block"; // Mostrar el mensaje
			// Ocultar el mensaje después de 5000 milisegundos (5 segundos)
			setTimeout(function() {
				mensaje.style.display = "none";
			}, 5000);
		}
	}

	window.addEventListener("load", function() {
		// Llamar a la función mostrarMensaje cuando se cargue la página
		if (document.getElementById("successMessage")) {
			mostrarMensaje("successMessage");
		} else if (document.getElementById("errorMessage")) {
			mostrarMensaje("errorMessage");
		}

		// Ocultar mensajes si son visibles
		ocultarMensaje();
	});
</script>



<script>
	$('.btn-pagar').click(function() {
		window.location.href = 'PagarPrestamo.jsp';
	});
</script>



<script>
	function filtrarFilas() {
		var estadoSeleccionado = document.getElementById("filtroEstado").value
				.toLowerCase();
		var motivoSeleccionado = document.getElementById("filtroMotivo").value
				.toLowerCase();
		var filas = document.querySelectorAll("tbody tr");

		filas.forEach(function(fila) {
			var estado = fila.querySelector(".estado").textContent.trim()
					.toLowerCase();
			var motivo = fila.querySelector(".motivo").textContent.trim()
					.toLowerCase();

			if ((estadoSeleccionado === "" || estado === estadoSeleccionado)
					&& (motivoSeleccionado === "" || motivo
							.includes(motivoSeleccionado))) {
				fila.style.display = "";
			} else {
				fila.style.display = "none";
			}
		});
	}

	document.getElementById("filtroEstado").addEventListener("change",
			filtrarFilas);
	document.getElementById("filtroMotivo").addEventListener("change",
			filtrarFilas);

	document.getElementById("pageSize").addEventListener("change", function() {
		rowsPerPage = parseInt(this.value, 10); // Obtener el tamaño de página seleccionado
		currentPage = 1; // Reiniciar a la primera página al cambiar el tamaño de página
		filtrarFilas();
	});

	window.addEventListener("load", function() {
		filtrarFilas(); // Aplicar filtros y paginación al cargar la página
	});
</script>

<script>
	const rowsPerPage = 5;
	let currentPage = 1;
	const table = document.getElementById("usersTable");
	const tableBody = document.getElementById("tableBody");
	const rows = tableBody.getElementsByTagName("tr");
	const totalRows = rows.length;
	const totalPages = Math.ceil(totalRows / rowsPerPage);

	function displayPage(page) {
		const start = (page - 1) * rowsPerPage;
		const end = start + rowsPerPage;
		for (let i = 0; i < totalRows; i++) {
			rows[i].style.display = (i >= start && i < end) ? "" : "none";
		}
		document.getElementById("btnPrev").disabled = page === 1;
		document.getElementById("btnNext").disabled = page === totalPages;
		updatePageNumbers();
	}

	function prevPage() {
		if (currentPage > 1) {
			currentPage--;
			displayPage(currentPage);
		}
	}

	function nextPage() {
		if (currentPage < totalPages) {
			currentPage++;
			displayPage(currentPage);
		}
	}

	function goToPage(page) {
		currentPage = page;
		displayPage(currentPage);
	}

	function updatePageNumbers() {
		const pageNumbers = document.getElementById("pageNumbers");
		pageNumbers.innerHTML = "";
		for (let i = 1; i <= totalPages; i++) {
			const span = document.createElement("span");
			span.textContent = i;
			span.className = (i === currentPage) ? "active" : "";
			span.onclick = function() {
				goToPage(i);
			};
			pageNumbers.appendChild(span);
		}
	}

	window.onload = function() {
		displayPage(currentPage);
	};
</script>


</html>
