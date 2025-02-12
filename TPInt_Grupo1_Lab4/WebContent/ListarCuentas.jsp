<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>  
<%@ page import="entidad.Cuenta" %> 
<!DOCTYPE html>  
<html lang="es">  
<head>  
    <meta charset="ISO-8859-1">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Lista de Cuentas</title>  
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
        <h2 class="text-primary">Lista de Cuentas</h2>
    </div>


    <!-- Botón Agregar alineado a la derecha -->
<div class="row mb-4" >
    <div class="col-12" style="text-align: right;">
        <a href="" class="btn btn-success">
            <i class="fas fa-user-plus"></i> Agregar Cuenta
        </a>
    </div>
</div>



      <!-- Buscador alineado a la izquierda -->
    <div class="row mb-4">
        <div class="col-12 col-md-6 search-container">
            <input type="text" id="searchInput" class="form-control" placeholder="Buscar cuenta..." onkeyup="filterTable()">  
        </div>
    </div>
    <%   
        // Obtener lista de usuarios y mensaje de error  
        List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");  
        String error = (String) request.getAttribute("error");  
    %>  

    <% if (error != null) { %>  
        <!-- Mostrar mensaje de error -->  
        <div class="alert alert-danger" role="alert">  
            <i class="fas fa-exclamation-circle"></i> <%= error %>  
        </div>  
    <% } else if (cuentas != null && !cuentas.isEmpty()) { %>  
        <!-- Tabla de usuarios -->  
        <div class="table-responsive">  
            <table class="table table-hover table-bordered" id="cuentasTable">  
                <thead class="thead-light">  
                    <tr>  
                        <th>Nro Cuenta</th>  
                        <th>Id Cliente</th>  
                        <th>Tipo de Cuenta</th>  
                        <th>CBU</th>
                        <th>Saldo</th>
                        <th>Deleted</th>
                    </tr>  
                </thead>  
                <tbody>  
                    <% for (Cuenta cuenta : cuentas) { %>  
                    <tr>  
                        <td><%= cuenta.getNroCuenta() %></td>  
                        <td><%= cuenta.getCliente().getId() %></td>  
                        <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>  
                        <td><%= cuenta.getCbu() %></td>  
                        <td><%= cuenta.getSaldo() %></td>
                        <td><%= cuenta.getDeleted() %></td>       
                    </tr>  
                    <% } %>  
                </tbody>  
            </table>  
        </div>  
    <% } else { %>  
        <!-- Mensaje de no hay usuarios -->  
        <div class="alert alert-info" role="alert">  
            <i class="fas fa-info-circle"></i> No se encontraron cuentas.  
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
        const table = document.getElementById("cuentasTable");
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