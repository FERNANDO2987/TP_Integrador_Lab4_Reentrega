<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Movimiento" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos de Cuenta</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
<%
	List<Movimiento> movimientos = (List<Movimiento>)request.getAttribute("movimientos");
%>
<div class="container mt-5">
<table>
<tr>
<th>Id</th>
<th>Fecha</th>
<th>TipoMovimiento</th>
<th>Importe</th>
<th>Detalle</th>
</tr>
<%
for(Movimiento mov: movimientos)
{
%>
	<tr>
	<td><%= mov.getId() %></td>
	<td><%= mov.getCreateDate() %></td>
	<td><%= mov.getTipoMovimiento().getDescripcion() %></td>
	<td><%= mov.getImporte() %></td>
	<td><%= mov.getDetalle() %></td>
	</tr>
<%
}
%>
</table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>