<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Movimiento" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Cuentas</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<div class="row">
	<% 
           List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
			if(cuentas != null && cuentas.size() > 0){
				for(Cuenta cuenta : cuentas)
				{
	%>
				<form action="servletMirarMisCuentasCliente" method="post">
				<button name="btnCuenta" type="submit" value="<%=cuenta.getNroCuenta()%>" class="btn btn-primary">Cuenta Nro <%=cuenta.getNroCuenta() %></button>
				</form>	
		<%} %>
	</div>
	
	
	<%if(request.getAttribute("movimientos") != null)
		{%>
	<div class="table-responsive">
        <table border="1" id="movimientos" class="table table-striped table-hover table-bordered text-center">
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
                <% List<Movimiento> movimientos = (List<Movimiento>)request.getAttribute("movimientos"); %>
                <% for(Movimiento mov: movimientos) { %>
                <tr>
                    <td><%= mov.getId() %></td>
                    <td><%= mov.getCreateDate() %></td>
                    <td><%= mov.getTipoMovimiento().getDescripcion() %></td>
                    <td><%= mov.getImporte() %></td>
                    <td><%= mov.getDetalle() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <%} %>
	<%}
	else
	{
	%>
		<div class="alert alert-danger" role="alert">
		No hay cuentas activas por el momento. Solicite abrir una cuenta e intentelo nuevamente
					</div>
	<%} %>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.bootstrap4.js"></script>
	<script>
	    $(document).ready(function() {
	        $('#movimientos').DataTable();
	    });
	</script>
</body>
</html>