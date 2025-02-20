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
            display: none; /* Quitar el icono del dropdown */  
        }  
        .dropdown-menu {  
            min-width: 0; /* Ajustar el ancho del menu */  
        }  
        .centered-header {
    		text-align: center; /* Centrar el texto horizontalmente */
    		margin: 0 auto;     /* Asegurar que el margen se maneje correctamente */
		}
	   .add-button-container {
    	text-align: center;
		}
		
		     .pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
.pagination button, .pagination span {
    margin: 0 5px;
    padding: 5px 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
}
.pagination button:disabled {
    cursor: not-allowed;
    opacity: 0.5;
}
.pagination .active {
    background-color: #007bff;
    color: white;
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
	                        <th>Acciones</th>
	                        
	                    </tr>  
	                </thead>  
	               <tbody id="tableBody"> 
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
	        
	            <div class="pagination mt-4">
      <button onclick="prevPage()" id="btnPrev" class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Anterior</button>
      <span id="pageNumbers"></span>
      <button onclick="nextPage()" id="btnNext" class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Siguiente</button>
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
	
	<script>
    const rowsPerPage = 5;
    let currentPage = 1;
    const table = document.getElementById("usersTable");
    const tableBody = document.getElementById("tableBody");
    const rows = tableBody.getElementsByTagName("tr");
    const totalRows = rows.length;
    const totalPages = Math.ceil(totalRows / rowsPerPage);

    function displayPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        for (let i = 0; i < totalRows; i++) {
            rows[i].style.display = (i >= start && i < end) ? "" : "none";
        }
        document.getElementById("btnPrev").disabled = page === 1;
        document.getElementById("btnNext").disabled = page === totalPages;
        updatePageNumbers();
    }

    function prevPage() {
        if (currentPage > 1) {
            currentPage--;
            displayPage(currentPage);
        }
    }

    function nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            displayPage(currentPage);
        }
    }

    function goToPage(page) {
        currentPage = page;
        displayPage(currentPage);
    }

    function updatePageNumbers() {
        const pageNumbers = document.getElementById("pageNumbers");
        pageNumbers.innerHTML = "";
        for (let i = 1; i <= totalPages; i++) {
            const span = document.createElement("span");
            span.textContent = i;
            span.className = (i === currentPage) ? "active" : "";
            span.onclick = function() { goToPage(i); };
            pageNumbers.appendChild(span);
        }
    }

    window.onload = function() {
        displayPage(currentPage);
    };
</script>
	
</body>
</html>