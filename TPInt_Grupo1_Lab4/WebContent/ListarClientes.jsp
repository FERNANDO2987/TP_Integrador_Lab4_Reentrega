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
        String error = (String) request.getAttribute("error");  
   
    %>  

    <% if (error != null) { %>  
        <!-- Mostrar mensaje de error -->  
        <div class="alert alert-danger" role="alert">  
            <i class="fas fa-exclamation-circle"></i> <%= error %>  
        </div>  
    <% } else if (clientes != null && !clientes.isEmpty()) { %>  
        <!-- Tabla de usuarios -->  
        <div class="table-responsive">  
            <table class="table table-hover table-bordered" id="usersTable">  
                <thead class="thead-light">  
                    <tr>  
                        <th>ID</th>  
                        <th>DNI</th>  
                        <th class="cuil-column">Cuil</th>

                        <th>Nombre</th>  
                        <th>Apellido</th>
                        <th>Pais</th>
                        <th>FechaNacimiento</th>
                        <th>Direccion</th>
                        <th>Localidad</th>
                        <th>Provincia</th>
                        <th>Correo</th>
                        <th>Telefono</th>
                        
                    </tr>  
                </thead>  
               <tbody>  
    <% for (Cliente cliente : clientes) { %>  
    <tr>
    	<input type="hidden" name="InputIdCliente" value="<%= cliente.getId() %>">
        <td><%= cliente.getId() %></td>  
        <td><%= cliente.getDni() %></td>  
        <td><%= cliente.getCuil() %></td>  
        <td><%= cliente.getNombre() %></td>  
        <td><%= cliente.getApellido() %></td>  
        <td><%= cliente.getPaisNacimiento().getNombre() %></td>  
        <td><%= cliente.getFechaNacimiento() %></td>  
        <td><%= cliente.getDireccion() %></td>  
        <td><%= cliente.getLocalidad().getNombre() %></td>  
        <td><%= cliente.getProvincia().getNombre() %></td>  
        <td><%= cliente.getCorreo() %></td>  
        <td><%= cliente.getTelefono() %></td>  
        <td>  
            <div class="dropdown">  
                <button class="btn btn-light btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  
                    <i class="fas fa-ellipsis-v"></i>  
                </button>  
                <div class="dropdown-menu dropdown-menu-right">  
                    <a class="dropdown-item" href="modificarCliente?id=<%= cliente.getId() %>">  
                        <i class="fas fa-edit"></i> Modificar  
                    </a>  
                    <a class="dropdown-item text-danger" href="eliminarCliente?id=<%= cliente.getId() %>" 
                        onclick="return confirm('¿Está seguro de que desea eliminar este cliente?');">  
                        <i class="fas fa-trash-alt"></i> Eliminar  
                    </a>
                    <a class="dropdown-item" href="servletGestionarCuentas?id=<%= cliente.getId() %>">  
                        <i class="fas fa-edit"></i> Gestionar Cuentas  
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
</body>  
</html>
