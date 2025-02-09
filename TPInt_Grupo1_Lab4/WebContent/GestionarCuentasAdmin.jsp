<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="java.util.List" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Cuentas</title>
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
<div class="container mt-5">
	<div class = "row">
	<%
		List<TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
		List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
		if(cuentas != null && cuentas.size() > 0)
		{
			for(Cuenta cuenta : cuentas)
			{
				
	%>			<!--  CARTA DE CUENTA -->
				<form action="servletGestionarCuentas" method="post">
				<div class="container-fluid">
			    <div class="row justify-content-center">
			        <div class="col">
			            <div class="card w-100">
			                <div class="card-body">
			                    <input type="hidden" id="idCuenta" name="idCuenta" value="<%=cuenta.getNroCuenta()%>">
			                    <h5 class="card-title text-truncate">CBU: <%=cuenta.getCbu() %></h5>
			                    <select name="selectCuenta" id="selectCuenta" class="form-control">
			                        <%
			                        if (tiposCuenta != null && tiposCuenta.size() > 0) {
			                            for(TipoCuenta tipo : tiposCuenta) { %>
			                                <option <%if (tipo.getId() == cuenta.getTipoCuenta().getId()) {%> selected<%}%>
			                                    value="<%=tipo.getId()%>"><%=tipo.getDescripcion() %></option>
			                        <% } } %>
			                    </select>
			                    <h6 class="card-text">$<%=cuenta.getSaldo() %></h6>
			                    <input type="submit" class="btn btn-success btn-sm" value="Modificar" id="btnModificar" name="btnModificar">
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			</form>
				<!-- FIN CARTA DE CUENTA -->
	<% 		}	
		}
		else
		{
	%>		<div class="alert alert-danger" role="alert">
                <h3>No se encontraron Cuentas.</h3>
            </div>
	
	<%  }%>
	</div>
	<% if(cuentas.size() < 3){ %>
	<!-- FORMULARIO DE NUEVA CUENTA -->
	<div class = "row card">
		<form action="servletGestionarCuentas" method="post" class = "card-body m-4">
		<input type="hidden" name="InputIdCliente" id="InputIdCliente" value="<%=request.getAttribute("idCliente") %>">
		  <div class="form-group">
		    <h4>Agregar Nueva Cuenta</h4>
		  </div>
		  <div class="form-group">
		    <label for="tipoCuentaSelect">Tipo de Cuenta</label>
		    <select class="form-control" id="tipoCuentaSelect" name="tipoCuentaSelect">
		    <%
		    		if (tiposCuenta != null && tiposCuenta.size() > 0)
		    		{
		    			for(TipoCuenta tipo : tiposCuenta)
		    			{%>
		    				<option value= "<%=tipo.getId()%>"><%=tipo.getDescripcion() %></option>
		    
		    			 <%}
		    		} %>
		    </select>
		  </div>
		  <div class="form-group">
		    <input class="btn btn-success" type="submit" value="Agregar" id="btnAgregar" name="btnAgregar">
		  </div>
		</form>
	</div>
	<%} %>
	
</div>	
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>