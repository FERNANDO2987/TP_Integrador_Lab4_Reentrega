<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Usuario"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agregar Usuario</title>
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
.centered-header {
	text-align: center;
}

.form-container {
	max-width: 500px;
	margin: 0 auto;
}

.form-label {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<!-- Encabezado centrado -->
		<div class="row justify-content-center mb-4">
			<h2 class="text-primary centered-header">Agregar Usuario</h2>
		</div>

		<!-- Mostrar mensajes de error o éxito -->
		<div>
			<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<%=error%>
			</div>
			<%
				}
			%>
		</div>

		<!-- Contenedor del formulario -->
		<div class="form-container">
			<form action="servletAgregarUsuario" method="post">
				<!-- Campo Usuario -->
				<div class="form-group">
					<label for="usuario" class="form-label">Usuario:</label> <input
						type="text" id="usuario" name="usuario" class="form-control"
						placeholder="Ingrese el usuario" required>
				</div>

				<!-- Campo Password -->
				<div class="form-group">
					<label for="password" class="form-label">Password:</label> <input
						type="password" id="password" name="password" class="form-control"
						placeholder="Ingrese el password" required>
				</div>



				<!-- Campo Admin -->
				<div class="form-group">
					<label for="admin" class="form-label">¿Es Admin?</label> <select
						id="admin" name="isAdmin" class="form-control">
						<option value="true">Sí</option>
						<option value="false">No</option>
					</select>
				</div>

				<!-- Botones -->
				<div class="form-group text-center">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-save"></i> Guardar
					</button>
					<a href="ListaUsuarios.jsp" class="btn btn-secondary"> <i
						class="fas fa-times"></i> Cancelar
					</a>
				</div>
			</form>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
