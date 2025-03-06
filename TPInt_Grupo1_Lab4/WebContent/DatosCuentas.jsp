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
	List<Prestamo> prestamosPendientes = (List<Prestamo>) request.getAttribute("prestamosPendientes");
	List<Prestamo> prestamosAprobados = (List<Prestamo>) request.getAttribute("prestamosAprobados");
	List<Prestamo> datosPrestamos = (List<Prestamo>) request.getAttribute("datosPrestamos");
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
<link rel="stylesheet" href="path/to/your/styles.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.tailwindcss.com"></script>

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

.estado-vigente {
	color: green;
}

.estado-finalizado {
	color: blue;
}

.estado-rechazado {
	color: red;
}

.estado-en-revision {
	color: orange;
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
			<h2 class="text-lg font-semibold text-gray-800">Mis préstamos</h2>
		</div>
		

		

		<%
			if (prestamosAprobados != null && !prestamosAprobados.isEmpty()) {
				for (Prestamo prestamo : prestamosAprobados) {
		%>

		<div class="bg-gray-50 p-4 rounded-lg mb-4">
			<h3 class="font-semibold text-gray-700">
				Observación:
				<%=prestamo.getObservaciones()%>
			</h3>
			<p class="text-sm text-gray-600">
				ID: <strong><%=prestamo.getId()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				Saldo otorgado: <strong>$ <%=prestamo.getImporte()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				Cuotas pendientes: <strong><%=prestamo.getCuotas()%></strong>
			</p>
			
			<div class="flex justify-between items-center mt-2">
				<p class="text-sm text-gray-600">
					Fecha Prestamo solicitada:
					<%=prestamo.getFechaAlta()%>
				</p>

				<a href="servletPagarPrestamo?idPrestamo=<%=prestamo.getId()%>"
					class="btn-pagar bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-700">
					<i class="fas fa-hand-holding-usd"></i> Pagar
				</a>
			</div>
		</div>

		<%
			}
			} else {
		%>

		<p class="text-gray-600 text-center">No tienes préstamos
			aprobados.</p>

		<%
			}
		%>
	</div>



	<div class="max-w-6xl mx-auto bg-white p-6 rounded-lg shadow-md mt-12">
		<div class="flex justify-between items-center mb-4">
			<h2 class="text-lg font-semibold text-gray-800">Pendientes de
				Aprobación</h2>
		</div>

		<%
			if (prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
				for (Prestamo prestamo : prestamosPendientes) {
		%>

		<div class="bg-gray-50 p-4 rounded-lg mb-4">
			<h3 class="font-semibold text-gray-700">
				Observación:
				<%=prestamo.getObservaciones()%>
			</h3>
			<p class="text-sm text-gray-600">
				Monto Solicitado: <strong>$ <%=prestamo.getImporte()%></strong>
			</p>
			<p class="text-sm text-gray-600">
				Cantidad de cuotas: <strong><%=prestamo.getCuotas() %></strong>
			</p>

			<div class="flex justify-between items-center mt-2">
				<p class="text-sm text-gray-600">Estado:</p>
				<p class="text-sm text-gray-600 ml-auto">
					<%
						String estado = prestamo.getEstado().toLowerCase();
								String estadoClase = "bg-gray-300 text-gray-800"; // Default
								String estadoTexto = "Desconocido";

								if (estado.equals("aprobado")) {
									estadoClase = "bg-blue-500 text-white";
									estadoTexto = "Aprobado";
								} else if (estado.equals("finalizado")) {
									estadoClase = "bg-green-500 text-white";
									estadoTexto = "Finalizado";
								} else if (estado.equals("rechazado")) {
									estadoClase = "bg-red-500 text-white";
									estadoTexto = "Rechazado";
								} else if (estado.equals("pendiente")) {
									estadoClase = "bg-yellow-400 text-gray-800";
									estadoTexto = "En Revisión";
								}
					%>
					<span class="<%=estadoClase%> px-2 py-1 rounded"> <%=estadoTexto%>
					</span>
				</p>
			</div>
		</div>

		<%
			}
			} else {
		%>

		<p class="text-gray-600 text-center">No tienes préstamos
			pendientes de aprobación.</p>

		<%
			}
		%>
	</div>



	<div class="max-w-6xl mx-auto bg-white p-6 rounded-lg shadow-md mt-12">
		<h2 class="text-lg font-semibold text-gray-800 mb-4">Historial de
			Préstamos</h2>

		<div class="overflow-x-auto">
			<!-- Filtros -->
			<div class="flex space-x-4 mb-4">
				<input type="text" id="filterNombre"
					class="form-control w-full p-2 rounded-lg border border-gray-300"
					placeholder="Filtrar por Nombre..." onkeyup="filterTable()">
				<select id="filterEstado"
					class="form-control w-full p-2 rounded-lg border border-gray-300"
					onchange="filterTable()">
					<option value="">Filtrar por Estado</option>
					<option value="aprobado">Aprobado</option>
					<option value="finalizado">Finalizado</option>
					<option value="rechazado">Rechazado</option>
					<option value="pendiente">Pendiente</option>
				</select>
			</div>

			<!-- Tabla de préstamos -->
			<table
				class="table-auto w-full bg-white border-collapse border border-gray-300"
				id="usersTable">
				<thead class="bg-gray-200">
					<tr>
						<th class="px-4 py-2 border">ID</th>
						<th class="px-4 py-2 border">Cuenta</th>
						<th class="px-4 py-2 border">Fecha de Alta</th>
						<th class="px-4 py-2 border">Tipo</th>
						<th class="px-4 py-2 border">Estado</th>
						<th class="px-4 py-2 border">Monto Adeudado</th>
						<th class="px-4 py-2 border">Cuotas Pendientes</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<%
						if (datosPrestamos != null && !datosPrestamos.isEmpty()) {
							try {
								for (Prestamo prestamo : datosPrestamos) {
					%>
					<tr>
						<td class="px-4 py-2 border"><%=prestamo.getId()%></td>
						<td class="px-4 py-2 border"><%=(prestamo.getCuenta().getNroCuenta())%>
						</td>
						<td class="border px-4 py-2"><%=prestamo.getFechaAlta()==null?"": prestamo.getFechaAlta()%></td>
						<td class="border px-4 py-2"><%=prestamo.getObservaciones()%></td>
						<td class="border px-4 py-2">
							<%
								String estado = (prestamo.getEstado() != null)
													? prestamo.getEstado().trim().toLowerCase()
													: "sin estado";
											String estadoClase = "bg-gray-300 text-gray-700"; // Clase por defecto
											String estadoTexto = "Sin Estado";

											switch (estado) {
												case "aprobado" :
													estadoClase = "bg-blue-500 text-white";
													estadoTexto = "Aprobado";
													break;
												case "finalizado" :
													estadoClase = "bg-green-500 text-white";
													estadoTexto = "Finalizado";
													break;
												case "rechazado" :
													estadoClase = "bg-red-500 text-white";
													estadoTexto = "Rechazado";
													break;
												case "pendiente" :
													estadoClase = "bg-yellow-500 text-white";
													estadoTexto = "Pendiente";
													break;
											}
							%> <span
							class="px-2 py-1 rounded <%=estadoClase%>"><%=estadoTexto%></span>
						</td>
						<td class="border px-4 py-2">$ <%=prestamo.getImporte()%></td>
						<td class="border px-4 py-2"><%=prestamo.getCuotas()%></td>
					</tr>
					<%
						}
							} catch (Exception e) {
					%>
					<tr>
						<td colspan="7" class="border px-4 py-2 text-center text-red-500">
							Error al procesar los préstamos: <%=e.getMessage()%>
						</td>
					</tr>
					<%
						}
						} else {
					%>
					<tr>
						<td colspan="7" class="border px-4 py-2 text-center">No hay
							préstamos disponibles.</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>

		<!-- Paginación -->
		<div class="pagination mt-4 flex justify-center space-x-4">
			<button onclick="prevPage()" id="btnPrev"
				class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">
				Anterior</button>
			<span id="pageNumbers" class="text-gray-800 font-semibold"></span>
			<button onclick="nextPage()" id="btnNext"
				class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">
				Siguiente</button>
		</div>
		<br>
		<br>
		<br>
		<br>
	</div>


	<script>
	function filterTable() {  
	    const nombreFiltro = document.getElementById("filterNombre").value.toLowerCase();
	    const estadoFiltro = document.getElementById("filterEstado").value.toLowerCase();
	    const table = document.getElementById("usersTable");  
	    const rows = table.getElementsByTagName("tr");  

	    for (let i = 1; i < rows.length; i++) {  
	        let cells = rows[i].getElementsByTagName("td");  
	        let foundNombre = false;  
	        let foundEstado = false;  

	        for (let j = 0; j < cells.length; j++) {  
	            let text = cells[j].textContent || cells[j].innerText;  

	            if (text.toLowerCase().includes(nombreFiltro)) {  
	                foundNombre = true;  
	            }  
	            if (text.toLowerCase().includes(estadoFiltro)) {  
	                foundEstado = true;  
	            }  
	        }  

	        // Mostrar la fila si coincide con ambos filtros (o si están vacíos)
	        if ((nombreFiltro === "" || foundNombre) && (estadoFiltro === "" || foundEstado)) {  
	            rows[i].style.display = "";  
	        } else {  
	            rows[i].style.display = "none";  
	        }  
	    }  
	}  




		window.onload = function() {
			setTimeout(function() {
				const successMessage = document
						.getElementById('successMessage');
				const errorMessage = document.getElementById('errorMessage');
				if (successMessage) {
					successMessage.style.display = 'none';
				}
				if (errorMessage) {
					errorMessage.style.display = 'none';
				}
			}, 5000);
		};
	</script>





</body>




</body>





<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>



	<script>
		// Llamar a la función mostrarMensaje si se ha definido el mensaje exitoso o de error
	<%if (request.getAttribute("mensajeExito") != null) {%>
		mostrarMensaje("successMessage");
	<%} else if (request.getAttribute("mensajeError") != null) {%>
		mostrarMensaje("errorMessage");
	<%}%>
		function ocultarMensaje() {
			var mensaje = document.getElementById("successMessage");
			if (mensaje) {
				setTimeout(function() {
					mensaje.style.display = "none";
				}, 9000);
			}

			var errorMensaje = document.getElementById("errorMessage");
			if (errorMensaje) {
				setTimeout(function() {
					errorMensaje.style.display = "none";
				}, 9000);
			}
		}

		// Función para mostrar el mensaje y luego ocultarlo  
		function mostrarMensaje(tipo) {
			var mensaje = document.getElementById(tipo);
			if (mensaje) {
				mensaje.style.display = "block"; // Mostrar el mensaje  
				// Ocultar el mensaje después de 3 segundos (3000 milisegundos)  
				setTimeout(function() {
					mensaje.style.display = "none";
				}, 9000);
			}
		}
		

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
