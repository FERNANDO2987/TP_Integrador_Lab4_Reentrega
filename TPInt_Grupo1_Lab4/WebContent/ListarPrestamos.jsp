<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>  
<%@ page import="entidad.Prestamo" %>
<%@ page import="entidad.Pais" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap CSS -->  
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">  
    <style>  
        .dropdown-toggle::after {  
            display: none; /* Quitar el �cono del dropdown */  
        }  
        .dropdown-menu {  
            min-width: 0; /* Ajustar el ancho del men� */  
        }  
        .centered-header {
    		text-align: center; /* Centrar el texto horizontalmente */
    		margin: 0 auto;     /* Asegurar que el margen se maneje correctamente */
		}
	   .add-button-container {
    	text-align: center;
		}
	</style>  
</head>
<body>
	
	<div class="container mt-5">  
   <!-- Contenedor centrado para el encabezado -->
    <div class="row justify-content-center mb-4">
        <h2 class="text-primary">Lista de Prestamos</h2>
    </div>

	      <!-- Buscador alineado a la izquierda -->
	    <div class="row mb-4">
	        <div class="col-12 col-md-6 search-container">
	            <input type="text" id="searchInput" class="form-control" placeholder="Buscar prestamo..." onkeyup="filterTable()">  
	        </div>
	    </div>
	    <%   
	        // Obtener lista de usuarios y mensaje de error  
	        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");  
	        String error = (String) request.getAttribute("error");  
	   
	    %>  
	
	    <% if (error != null) { %>  
	        <!-- Mostrar mensaje de error -->  
	        <div class="alert alert-danger" role="alert">  
	            <i class="fas fa-exclamation-circle"></i> <%= error %>  
	        </div>  
	    <% } else if (prestamos != null && !prestamos.isEmpty()) { %>  
	        <!-- Tabla de usuarios -->  
	        <div class="table-responsive">  
	            <table class="table table-hover table-bordered" id="prestamosTable">  
	                <thead class="thead-light">  
	                    <tr>  
	                        <th>Id Prestamo</th>  	
	                        <th>CBU</th>
	                        <th>Nro.Cuenta</th>
	                        <th>Id Cliente</th>
	                        <th>Nombre</th>  
	                        <th>Apellido</th>
	                        <th>Monto</th>
	                        <th>Cuotas</th>
	                        <th>Fecha de Solicitud</th>
	                        <th>Estado</th>
	                        
	                    </tr>  
	                </thead>  
	               <tbody>  
	    <% for (Prestamo p: prestamos) { %>  
	    <tr>  
	        <td><%= p.getId() %></td>  
	        <td><%= p.getCuenta().getCbu() %></td>  
	        <td><%= p.getCuenta().getNroCuenta() %></td>  
	        <td><%= p.getCliente().getId() %></td>  
	        <td><%= p.getCliente().getNombre() %></td>  
	        <td><%= p.getCliente().getApellido() %></td>  
	        <td><%= p.getImporte() %></td>  
	        <td><%= p.getCuotas() %></td>  
	        <td><%= p.getFechaAlta() %></td>  
	        <td><%= p.getEstado() %></td>  
	        <td>  
	            <div class="dropdown">  
	                <button class="btn btn-light btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  
	                    <i class="fas fa-ellipsis-v"></i>  
	                </button>  
	                <div class="dropdown-menu dropdown-menu-right" onclick="return confirm('¿Quiere aprobar el préstamo?');">  
	                    <a class="dropdown-item" href="#">  
	                    	
	                        <i class="fas fa-check"></i> Aprobar  
	                    </a>  
	                    <a class="dropdown-item text-danger" href="#" 
	                        onclick="return confirm('¿Quiere rechazar el préstamo?');">  
	                        <i class="fas fa-times"></i>  Rechazar
	                    </a>  
	                </div>  
	            </div>  
	        </td>  
	    </tr>  
	    <% } %>  
	</tbody>  
	
	            </table>  
	        </div>  
	    <% } else { %>  
	        <!-- Mensaje de no hay usuarios -->  
	        <div class="alert alert-info" role="alert">  
	            <i class="fas fa-info-circle"></i> No se encontraron clientes.  
	        </div>  
	    <% } %>  
	</div>  

	<!-- Bootstrap JS and dependencies -->  
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>  
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
	
	<script>
	    // Inicializar dropdowns de Bootstrap
	    $(document).ready(function () {
	        $('.dropdown-toggle').dropdown();
	    });
	
	    // Funci�n para filtrar la tabla en tiempo real
	    function filterTable() {
	        const input = document.getElementById("searchInput");
	        const filter = input.value.toLowerCase();
	        const table = document.getElementById("prestamosTable");
	        const rows = table.getElementsByTagName("tr");
	
	        for (let i = 1; i < rows.length; i++) {
	            const cells = rows[i].getElementsByTagName("td");
	            let match = false;
	
	            for (let j = 0; j < cells.length; j++) {
	                if (cells[j]) {
	                    const cellText = cells[j].textContent || cells[j].innerText;
	                    if (cellText.toLowerCase().indexOf(filter) > -1) {
	                        match = true;
	                        break;
	                    }
	                }
	            }
	
	            rows[i].style.display = match ? "" : "none";
	        }
	    }
	</script>
</body>
</html>