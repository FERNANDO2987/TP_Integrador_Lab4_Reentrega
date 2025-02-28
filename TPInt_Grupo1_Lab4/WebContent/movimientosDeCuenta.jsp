<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Movimiento"%>
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
<title>Movimientos de Cuenta</title>

<!-- Bootstrap y DataTables CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.bootstrap4.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
body {
	background-color: #f8f9fa;
	font-family: 'Arial', sans-serif;
}

h2 {
	color: #343a40;
}

.table {
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	overflow: hidden;
}

.btn-success {
	transition: all 0.3s ease;
}

.btn-success:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<form action="servletMovimientosCuenta" method="post">
			<button type="submit" id="btnBack" name="btnBack"
				class="btn btn-success btn-lg mb-3">
				<i class="fas fa-arrow-left"></i> Volver
			</button>
		</form>

		<h2 class="text-center mb-4">
			<i class="fas fa-list-alt"></i> Historial de Movimientos
		</h2>

		<div class="table-responsive">
			<table border="1" id="movimientos"
				class="table table-striped table-hover table-bordered text-center">
				<thead class="thead-dark">
					<tr>
						<th>Id</th>
						<th>Fecha</th>
						<th>TipoMovimiento</th>
						<th>Importe</th>
						<th>Detalle</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
					%>
					<%
						for (Movimiento mov : movimientos) {
					%>
					<tr>
						<td><%=mov.getId()%></td>
						<td><%=mov.getCreateDate()%></td>
						<td><%=mov.getTipoMovimiento().getDescripcion()%></td>
						<td><%=mov.getImporte()%></td>
						<td><%=mov.getDetalle()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>

	<!-- jQuery, Bootstrap y DataTables JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/2.1.8/js/dataTables.bootstrap4.js"></script>
	<script>
		$(document).ready(function() {
			$('#movimientos').DataTable();
		});
	</script>
</body>
</html>