<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.List"%>

<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}

	Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
	String error = (String) request.getAttribute("error");
	List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pagar Préstamo</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<style>

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

/* Si prefieres un borde rojo sin mostrar el mensaje de error */
input:required:invalid {
	border-color: red;
}


</style>

</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

	<div class="max-w-2xl w-full bg-white p-6 rounded-lg shadow-md">
		<h2 class="text-xl font-semibold text-gray-800 mb-4">Pagar
			Préstamo</h2>


    		<!-- Mostrar mensaje de éxito -->
		<%
			String mensajeExito = (String) request.getAttribute("mensajeExito");
			if (mensajeExito != null) {
		%>
		<div id="successMessage" class="alert alert-success mb-4">
			<i class="fas fa-check-circle"></i>
			<%=mensajeExito%>
		</div>
		<%
			}
		%>

		<%
			String mensajeError = (String) request.getAttribute("mensajeError");
			if (mensajeError != null) {
		%>
		<div id="errorMessage" class="alert alert-error mb-4">
			<i class="fas fa-exclamation-circle"></i>
			<%=mensajeError%>
		</div>
		<%
			}
		%>


	

		<%
			if (prestamo != null) {
		%>
		<div class="bg-gray-50 p-4 rounded-lg mb-4">
			<div class="flex justify-between">
				<h3 class="font-semibold text-gray-700">Préstamo Personal</h3>
				<p class="text-gray-600 font-semibold">
					Monto total: $
					<%=prestamo.getImporte()%></p>
			</div>

			<div class="mt-2 text-gray-700">
				<p class="font-bold">
					Cuota a pagar:
					<%=prestamo.getCuotas()%></p>
				<p class="font-bold">
					Valor Cuota: $
					<%=prestamo.getValorCuotas()%></p>
				<p class="font-bold">
					Deuda pendiente: $
					<%=prestamo.getImporte()%></p>
			</div>

			
		</div>

		
		<form action="servletProcesarPago" method="post">
		
			<input type="hidden" name="idPrestamo"
				value="<%=prestamo.getId()%>">

			<div class="mt-4">
				<label class="block text-gray-700 font-medium mb-2">Selecciona
					una cuenta</label> <select name="nroCuenta"
					class="w-full p-2 border border-gray-300 rounded" required>
					<option value="">Selecciona una cuenta</option>

					<%
						if (cuentas != null && !cuentas.isEmpty()) {
					%>
					<%
						for (Cuenta cuenta : cuentas) {
					%>
					<option value="<%=cuenta.getNroCuenta()%>">Nº
						<%=cuenta.getNroCuenta()%> - Saldo: $<%=cuenta.getSaldo()%>
					</option>
					<%
						}
					%>
					<%
						} else {
					%>
					<option value="">No tienes cuentas disponibles</option>
					<%
						}
					%>
				</select>
			</div>

		
			
				<div class="mt-6">
					<button type="submit"
						class="w-full bg-blue-600 text-white py-2 rounded-lg font-semibold hover:bg-green-700 transition"
						onclick="return confirm('¿Está seguro de que deseas Pagar la cuota?')">
						Confirmar Pago</button>
				</div>
				
				
				
		</form>

		<%
			} else {
		%>
		<p class="text-gray-600">No se encontraron datos del préstamo.</p>
		<%
			}
		%>
	</div>
	
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
				}, 5000);
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


</body>
</html>
