<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="negocio.CuentaNeg"%>
<%@ page import="negocioImpl.CuentaNegImpl"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Prestamo</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
.dropdown-toggle::after {
	display: none; /* Quitar el icono del dropdown */
}

.dropdown-menu {
	min-width: 0; /* Ajustar el ancho del menu */
}

.centered-header {
	text-align: center; /* Centrar el texto horizontalmente */
	margin: 0 auto; /* Asegurar que el margen se maneje correctamente */
}

.add-button-container {
	text-align: center;
}
</style>

    <!-- jQuery and DataTables -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script>  
        function ocultarMensaje() {  
            var mensaje = document.getElementById("successMessage");  
            if (mensaje) {  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 3000);  
            }  
        }  
    </script>
    <script>
    $(document).ready(function() {  
        // Mostrar el mensaje si existe  
        var mensajeExito = '<%= session.getAttribute("successMessage") != null ? session.getAttribute("successMessage") : "" %>';  
        if (mensajeExito) {  
            $('#successMessage').show(); // Mostrar el mensaje  
            ocultarMensaje(); // Llama a la funciÃ³n para ocultar el mensaje  
        }  
        
        // Mostrar el mensaje de error si existe  
        var mensajeError = '<%= session.getAttribute("errorMessage") != null ? session.getAttribute("errorMessage") : "" %>';  
        if (mensajeError) {  
            $('#errorMessage').show(); // Mostrar el mensaje de error  
            ocultarMensaje("errorMessage"); // Llama a la funciÃ³n para ocultar el mensaje  
        }  
    });  
    </script>
    <script type="text/javascript"></script> 

</head>
<body>
	<%
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		String nombreUsuario = "Usuario desconocido";
		if (usuario.getCliente() != null) {
			nombreUsuario = usuario.getCliente().getNombre();
		}
	%>

	<!-- Lista de cuentas -->
	<%
		if (request.getAttribute("listaDeMisCuentas") == null) {
			response.sendRedirect("servletAgregarPrestamo");
		}

		List<Cuenta> listaDeOrigen = new ArrayList<Cuenta>();;
		if (request.getAttribute("listaDeMisCuentas") != null) {
			listaDeOrigen = (List<Cuenta>) request.getAttribute("listaDeMisCuentas");
		}
	%>


 	<!-- Mensaje de exito -->
    <div class="alert alert-success" role="alert" id="successMessage" style="display:none;">
        ${successMessage}
    </div>

    <!-- Mensaje de error -->
    <div class="alert alert-danger" role="alert" id="errorMessage" style="display:none;">
        ${errorMessage}
    </div>


	<div class="container mt-5">
		<div class="row">
			<div class="col-6 mx-auto">
				<h2 class="text-center mb-4">Solicitar Prestamo</h2>
				<form action="servletAgregarPrestamo" method="post"
					onsubmit="return confirmarEnvio()">
					<div class="form-group">
						<label for="cuentaDestino">Cuenta</label> <select
							class="form-control" id="cuentaDestino" name="cuentaDestino"
							required>
							<option value="">Seleccionar</option>
							<%
								if (listaDeOrigen != null && !listaDeOrigen.isEmpty()) {
									for (Cuenta c : listaDeOrigen) {
							%>
							<option value="<%=c.getNroCuenta()%>">Nro de cuenta: <%=c.getNroCuenta()%> - CBU: <%=c.getCbu()%> - Tipo de cuenta: <%=c.getTipoCuenta().getDescripcion()%></option>
							<%
								}
								} else {
							%>
							<option value="">No hay cuentas disponibles</option>
							<%
								}
							%>
						</select> <label class="mt-2" for="tipoPrestamo">Tipo de prestamo</label> <select
							class="form-control" id="tipoPrestamo" name="tipoPrestamo"
							required>
							<option value="">Seleccionar</option>
							<option value="Adelanto de sueldo">Adelanto de sueldo</option>
							<option value="Prestamo personal">Prestamo personal</option>
							<option value="Prestamo prendario">Prestamo prendario</option>
							<option value="Prestamo hipotecario">Prestamo
								hipotecario</option>
						</select>
					</div>


					<div class="form-group">
						<label for="monto">Monto Solicitado:</label> <input type="number"
							min="1" class="form-control" id="monto" name="monto"
							placeholder="Ingrese el monto a solicitar" required>
					</div>


					<div class="form-group">
						<label for="cuotas">Cuotas:</label> <input type="number" min="1"
							max="100" class="form-control" id="cuotas" name="cuotas"
							placeholder="Ingrese la cantidad de cuotas" required>
					</div>

					<input type="hidden" name="usuarioID"
						value="<%=usuario.getCliente().getId()%>"> <input
						type="submit" class="btn btn-primary btn-block" name=btnSubmit
						value="Solicitar Prestamo"> <a
						class="btn btn-secondary w-100 mt-1" href="HomeUsuario.jsp">
						Volver al Home </a>
				</form>
			</div>
		</div>
	</div>

	<script>
		function confirmarEnvio() {
			return confirm("¿Estás seguro de que quieres solicitar el prestamo?");
		}
	</script>

	<!-- Bootstrap JS and dependencies -->
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>