<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Transferencia</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<%
	List<Cuenta> cuentas;
	if(request.getAttribute("listaDeMisCuentas") != null)
	{
		cuentas = (List<Cuenta>)request.getAttribute("listaDeMisCuentas");
	}
	else
	{
		cuentas = new ArrayList<Cuenta>();
	}
	String stringAlert = (String)request.getAttribute("errorTransfer");
	//si no esta nulo
	if(stringAlert != null)
	{
		// si no esta vacio
		if(!stringAlert.isEmpty())
		{
			if (stringAlert.equals("Transferencia Realizada con Exito"))
			{
				%>
					<div class="alert alert-success" role="alert">
  					<%=stringAlert %>
					</div>
				<%
				
			}
			else
			{
				%>
					<div class="alert alert-danger" role="alert">
 					<%=stringAlert %>
					</div>
				<%
			}
		}
	}
%>

<div class="container mt-5">
        <div class="card shadow-lg p-4">
            <h2 class="text-center mb-4">Transferencia Bancaria</h2>
            <form method="post" action="servletTransferencia">
                <div class="mb-3">
                    <label for="cuentaOrigen" class="form-label">Cuenta Origen</label>
                    <select class="form-control" id="cuentaOrigen" name="cuentaOrigen" required>
                        <option selected disabled>Seleccione una cuenta de origen</option>
                        <%
                        for(Cuenta cuenta : cuentas)
                        {
                        %>
                        	<option value="<%= cuenta.getCbu() %>"> Nro Cuenta: <%=cuenta.getNroCuenta() %>  Cbu: <%=cuenta.getCbu() %> </option>
                        <%
                        }
                        %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cuentaDestino" class="form-label">Cuenta Destino</label>
                    <input type="text" class="form-control" name="cuentaDestino" id="cuentaDestino" placeholder="Ingrese el CBU de la cuenta que recibira el dinero" required>
                </div>
                <div class="mb-3">
                    <label for="monto" class="form-label">Monto</label>
                    <input type="number" class="form-control" id="monto" name="monto" placeholder="Ingrese el monto" min="1" step="0.01" required>
                </div>
                <div class="mb-3">
                    <label for="detalle" class="form-label">Detalle</label>
                    <input type="text" class="form-control" id="detalle" name="detalle" placeholder="Ingrese un detalle opcional">
                </div>
                <button type="submit" name="btnTransferir" id="btnTransferir" class="btn btn-primary w-100">Realizar Transferencia</button>
            </form>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>