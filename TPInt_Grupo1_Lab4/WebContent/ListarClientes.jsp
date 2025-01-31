<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ page import="java.util.List" %>  
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Pais" %> 

<!DOCTYPE html>  
<html lang="es">  
<head>  
    <meta charset="ISO-8859-1">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Lista de Clientes</title>  
    <!-- Bootstrap CSS -->  
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">  
    <style>  
        .dropdown-toggle::after {  
            display: none; /* Quitar el ícono del dropdown */  
        }  
        .dropdown-menu {  
            min-width: 0; /* Ajustar el ancho del menú */  
        }  
        .centered-header {
    text-align: center; /* Centrar el texto horizontalmente */
    margin: 0 auto;     /* Asegurar que el margen se maneje correctamente */
}
        
       
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
        <h2 class="text-primary">Lista de Clientes</h2>
    </div>


    <!-- Botón Agregar alineado a la derecha -->
<div class="row mb-4" >
    <div class="col-12" style="text-align: right;">
        <a href="AgregarCliente.jsp" class="btn btn-success">
            <i class="fas fa-user-plus"></i> Agregar Cliente
        </a>
    </div>
</div>



      <!-- Buscador alineado a la izquierda -->
    <div class="row mb-4">
        <div class="col-12 col-md-6 search-container">
            <input type="text" id="searchInput" class="form-control" placeholder="Buscar cliente..." onkeyup="filterTable()">  
        </div>
    </div>
    <%   
        // Obtener lista de usuarios y mensaje de error  
        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");  
    String mensajeExito = (String) request.getAttribute("mensajeExito");  
    String mensajeError = (String) request.getAttribute("mensajeError");  
   
    %>  

    <% if (mensajeExito != null) { %>  
    <div class="alert alert-success" role="alert" id="successMessage">
        <i class="fas fa-check-circle"></i> <%= mensajeExito %>
    </div>  
<% } %>  

<% if (mensajeError != null) { %>  
    <div class="alert alert-danger" role="alert" id="errorMessage">
        <i class="fas fa-exclamation-circle"></i> <%= mensajeError %>
    </div>  
<% } %>

    <% if (clientes != null && !clientes.isEmpty()) { %>  
        <!-- Tabla de usuarios -->  
        <div class="table-responsive">  
            <table class="table table-hover table-bordered" id="usersTable">  
                <thead class="thead-light">  
                    <tr>  
                        <th>ID</th>  
                        <th>Nombre</th>  
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>FechaNacimiento</th>
                        <th>Correo</th>
                        <th>Acciones</th>           
                    </tr>  
                </thead>  
               <tbody>  
    <% for (Cliente cliente : clientes) { %>  
    <tr>  
        <td><%= cliente.getId() %></td>  
        <td><%= cliente.getNombre() %></td>  
        <td><%= cliente.getApellido() %></td>  
        <td><%= cliente.getDni() %></td>  
        <td><%= cliente.getFechaNacimiento() %></td>  
        <td><%= cliente.getCorreo() %></td>  
        <td>  
            <div class="dropdown">  
                <button class="btn btn-light btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  
                    <i class="fas fa-ellipsis-v"></i>  
                </button>  
                <div class="dropdown-menu dropdown-menu-right">  
                    <a class="dropdown-item" href="DetalleCliente.jsp?id=<%= cliente.getId() %>">  
                        <i class="fas fa-eye"></i> Ver detalles  
                    </a>  
            
                      <a href="servletEliminarCliente?id=<%= cliente.getId() %>" class="dropdown-item text-danger" title="Eliminar" onclick="return confirm('¿Estas seguro de que deseas eliminar este cliente?');">
                                        <i class="fas fa-trash-alt"></i>Eliminar
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

    // Función para filtrar la tabla en tiempo real
    function filterTable() {
        const input = document.getElementById("searchInput");
        const filter = input.value.toLowerCase();
        const table = document.getElementById("usersTable");
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
    // Mover la llamada de la función al document.ready para asegurar que el DOM esté cargado
    $(document).ready(function() {
        <% if(request.getAttribute("mensajeExito") != null) { %>  
            mostrarMensaje("successMessage");  
        <% } else if(request.getAttribute("mensajeError") != null) { %>  
            mostrarMensaje("errorMessage");  
        <% } %>  
    });

    function ocultarMensaje() {  
        var mensaje = document.getElementById("successMessage");  
        if (mensaje) {  
            setTimeout(function() {  
                mensaje.style.display = "none";  
            }, 9000); 
        }  

        var errorMensaje = document.getElementById("errorMessage");
        if (errorMensaje) {
            setTimeout(function() {
                errorMensaje.style.display = "none";
            }, 9000);
        }
    } 

    function mostrarMensaje(tipo) {  
        var mensaje = document.getElementById(tipo);  
        if (mensaje) {  
            mensaje.style.display = "block"; // Mostrar el mensaje  
            // Ocultar el mensaje después de 9 segundos (9000 milisegundos)  
            setTimeout(function() {  
                mensaje.style.display = "none";  
            }, 9000);  
        }  
    }
</script>

</body>  
</html>
