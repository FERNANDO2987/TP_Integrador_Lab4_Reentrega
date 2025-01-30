<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="entidad.Cuenta" %>
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
		List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
		if(cuentas != null && cuentas.size() > 0)
		{
			for(Cuenta cuenta : cuentas)
			{
	%>			<div class="col-md-4 d-flex align-items-stretch mb-4">
				<!-- Card -->
				<div class="card" style="width: 18rem;">
			  	<div class="card-body">
			    <h5 class="card-title">CBU: <%=cuenta.getCbu() %></h5>
			    <h6 class="card-subtitle mb-2 text-muted"><%=cuenta.getTipoCuenta().getDescripcion() %></h6>
			    <p class="card-text">$<%=cuenta.getSaldo() %></p>
			    <a href="#" class="card-link"><i class="fas fa-edit"></i></a>
			    <a href="#" class="card-link"><i class="fas fa-trash-alt"></i></a>
			    <a href="#" class="card-link">Movimientos</a>
			  	</div>
				</div>
				</div>
	<% 		}	
		}
		else
		{
	%>		<div class="alert alert-danger" role="alert">
                <h3>No se encontraron Cuentas.</h3>
            </div>
	
	<%  }%>
	</div>
	
	<div class = "row card">
		<form class = "card-body m-4">
		  <div class="form-group">
		    <h4>Agregar Nueva Cuenta</h4>
		  </div>
		  <div class="form-group">
		    <label for="tipoCuentaSelect">Tipo de Cuenta</label>
		    <select class="form-control" id="tipoCuentaSelect" name="tipoCuentaSelect">
		    
		    </select>
		  </div>
		  <div class="form-group">
		    <input class="btn-success" type="submit" value="Agregar" id="btnAgregar" name="btnAgregar">
		  </div>
		</form>
	</div>
	
	
</div>	
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>