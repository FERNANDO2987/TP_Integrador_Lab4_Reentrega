<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="negocio.CuentaNeg" %>
<%@ page import="negocioImpl.CuentaNegImpl" %> 
<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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
        var mensajeExito = '<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>';  
        if (mensajeExito) {  
            $('#successMessage').show(); // Mostrar el mensaje  
            ocultarMensaje(); // Llama a la función para ocultar el mensaje  
        }  
        
        // Mostrar el mensaje de error si existe  
        var mensajeError = '<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>';  
        if (mensajeError) {  
            $('#errorMessage').show(); // Mostrar el mensaje de error  
            ocultarMensaje("errorMessage"); // Llama a la función para ocultar el mensaje  
        }  
    });  
    </script>
    <script type="text/javascript"></script> 

</head>
<body>
    <!-- Mensaje de éxito -->
    <div class="alert alert-success" role="alert" id="successMessage" style="display:none;">
        ${successMessage}
    </div>

    <!-- Mensaje de error -->
    <div class="alert alert-danger" role="alert" id="errorMessage" style="display:none;">
        ${errorMessage}
    </div>

    <!-- Uso de session
    <%
      Cliente usuario = (Cliente)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
      
      List<Cuenta> listaDeOrigen = new ArrayList<Cuenta>();;
      if(request.getAttribute("listaDeMisCuentas") != null)
      {
    	  listaDeOrigen = (List<Cuenta>) request.getAttribute("listaDeMisCuentas");
      }
    %>
    -->


    <div class="container mt-5">
        <div class="row">
            <div class="col-6 mx-auto">
                <h2 class="text-center mb-4">Solicitar Préstamo</h2>
                <form action="servletAgregarPrestamo" method="post">
                    <div class="form-group">
                        <label for="cuentaDestino">Cuenta</label>

                        <select class="form-control" id="cuentaDestino" name="cuentaDestino" required>
	                    	<option value="">Seleccionar</option>
                            <option value="">No hay cuentas disponibles</option>
                        </select>
                    </div>
                    <div class="form-group">
		                <label for="monto">Monto Solicitado:</label>
		                <input type="number" min="1" class="form-control" id="monto" name="monto" placeholder="Ingrese el monto a solicitar" required>
		            </div>
		            <div class="form-group">
						<label for="cuotas">Cuotas:</label>
		                <input type="number"  min="1" max="24"  class="form-control" id="cuotas" name="cuotas" placeholder="Ingrese la cantidad de cuotas" required>
		            </div>
		            <input type="hidden" name="usuarioID" value="<%=usuario.getId() %>">
					<input type="submit" class="btn btn-primary btn-block" name=btnSubmit value="Solicitar Préstamo">
					<a class="btn btn-secondary w-100 mt-1" href="Home.jsp" > 	Volver al Home </a>
                </form>
            </div>
        </div>
    </div>


     <!-- Bootstrap JS and dependencies -->
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>