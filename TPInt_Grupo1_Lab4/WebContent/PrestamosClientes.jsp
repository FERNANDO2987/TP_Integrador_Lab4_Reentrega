

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<meta charset="UTF-8">

<%@ page import="java.util.List" %>  
<%@ page import="entidad.Cliente" %> 
<%@ page import="entidad.Prestamo" %> 
<%@ page import="entidad.Pais" %>  
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="entidad.Usuario" %>
<%
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
%>
<!DOCTYPE html>  
<html lang="es">  
<head>  
  
    <meta charset="ISO-8859-1">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">  
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
      
          <link rel="stylesheet" href="path/to/your/styles.css"> <!-- Personaliza esta línea -->  
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  
      
    <title>Listar Prestamos</title>  
    
       <style>
      
       .alert {  
    font-size: 1.2rem;  
    padding: 20px;  
    border-radius: 8px;  
    display: flex;  
    align-items: center;  
    margin-bottom: 16px;  
}  
.alert-success {  
    background-color: #28a745; /* Color verde */  
    color: white;  
}  
.alert-error {  
    background-color: #dc3545; /* Color rojo */  
    color: white;  
}
    </style>
</head>  
<body class="bg-gray-100">  

<div class="container mx-auto mt-5">  
    <!-- Contenedor centrado para el encabezado -->
    <div class="text-center mb-4">
        <h2 class="text-2xl text-blue-600">Listar Prestamos</h2>
    </div>


    <%   
    // Recuperar los valores de los montos desde el request
    BigDecimal montoTotalSolicitado = (BigDecimal) request.getAttribute("montoTotalSolicitado");
    BigDecimal montoTotalAdjudicado = (BigDecimal) request.getAttribute("montoTotalAdjudicado");
        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");  
     
    %>  
    
    

      <!-- Mostrar mensaje de éxito -->
  <%  
    String mensajeExito = (String) request.getAttribute("mensajeExito");  
    if (mensajeExito != null) {  
%>  
    <div id="successMessage" class="alert alert-success mb-4">  
        <i class="fas fa-check-circle"></i> <%= mensajeExito %>  
    </div>  
<%  
    }  
%>   

<%  
    String mensajeError = (String) request.getAttribute("mensajeError");  
    if (mensajeError != null) {  
%>  
    <div id="errorMessage" class="alert alert-error mb-4">  
        <i class="fas fa-exclamation-circle"></i> <%= mensajeError %>  
    </div>  
<%  
    }  
%>


<h2 class="text-xl font-semibold mt-5">Resumen de préstamos:</h2>  
<div class="bg-white shadow-sm rounded-lg p-4 mt-2">  
    <p class="text-lg">Monto total solicitado (En revisión):   
        <strong class="text-red-600">
            $ <%= montoTotalSolicitado != null ? new DecimalFormat("#,##0.00").format(montoTotalSolicitado) : "0.00" %>
        </strong>  
    </p>  
    <p class="text-lg">Monto total adjudicado (Vigente):   
        <strong class="text-green-600">
            $ <%= montoTotalAdjudicado != null ? new DecimalFormat("#,##0.00").format(montoTotalAdjudicado) : "0.00" %>
        </strong>  
        </p>  
</div>
<br>



    <% if (prestamos != null && !prestamos.isEmpty()) { %>  
    
    
    <!-- Filtros para la tabla de préstamos -->  
<div class="flex space-x-4 mb-4">  
    <input type="text" id="filterNombre" class="form-control w-full p-2 rounded-lg border-gray-300" placeholder="Filtrar por Nombre..." onkeyup="filterTable()">  
    <select id="filterEstado" class="form-control w-full p-2 rounded-lg border-gray-300" onchange="filterTable()">  
        <option value="">Filtrar por Estado</option>  
        <option value="Vigente">Vigente</option>  
        <option value="Finalizado">Finalizado</option>  
        <option value="Rechazado">Rechazado</option>  
        <option value="En Revisión">DNI</option>  
    </select>  
</div>
    
        <!-- Tabla de clientes -->  
        <div class="overflow-x-auto">  
            <table class="table-auto w-full bg-white border-collapse border border-gray-300" id="usersTable">  
                <thead class="bg-gray-200">  
                    <tr>  
                        <th class="px-4 py-2 border">ID</th>  
                        <th class="px-4 py-2 border">DNI</th>  
                        <th class="px-4 py-2 border">Nombre y Apellido</th>  

                        <th class="px-4 py-2 border">Monto Solicitado</th>
                        <th class="px-4 py-2 border">Cuotas</th> 
                         <th class="px-4 py-2 border">Estado</th> 
                        <th class="px-4 py-2 border">Acciones</th>  
                    </tr>  
                </thead>  
                <tbody>  
                    <% for (Prestamo prestamo : prestamos) { %>  
                        <tr>  
                            <td class="px-4 py-2 border"><%= prestamo.getId() %></td>  
                            <td class="px-4 py-2 border"><%= prestamo.getCliente().getDni() %></td>  
                             <td class="border px-4 py-2"><%= prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido() %></td> 
                             <td class="border px-4 py-2">$ <%= prestamo.getImporte() %></td>  
                              <td class="border px-4 py-2"><%= prestamo.getCuotas() %></td>  
                              <td class="border px-4 py-2"><%= prestamo.getEstado() %></td>  
                              <td class="border px-4 py-2 flex space-x-2">  
                    
                         
                            <a href="servletAprobarPrestamo?id=<%= prestamo.getId() %>" 
                               class="bg-green-500 text-white p-1 hover:bg-green-200"      
                               onclick="return confirm('¿Estás seguro de que deseas aprobar este préstamo?');">
                         <i class="fas fa-check transition-all duration-200 hover:text-blue-300"></i> 
                         </a>
                         
                           
                           <a href="servletRechazarPrestamo?id=<%= prestamo.getId() %>" 
                              class="bg-red-500 text-white p-1 hover:bg-red-600 flex items-center gap-1"    
                               onclick="return confirm('¿Estás seguro de que deseas rechazar este préstamo?');">
                         <i class="fas fa-ban transition-all duration-200 hover:text-blue-300"></i> 
                         </a>
                           
                             
                      </td>
                        </tr>  
                    <% } %>  
                </tbody>  
            </table>  
        </div>  
        
        
        
    <% } else { %>  
        <div class="bg-blue-100 text-blue-800 p-4 rounded-lg mb-4" role="alert">  
            <i class="fas fa-info-circle"></i> No se encontraron prestamos.  
        </div>  
    <% } %>  
    





</body>  

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  




<script>  


function filterTable() {  
    const inputNombre = document.getElementById("filterNombre");  
    const filterNombre = inputNombre.value.toLowerCase();  
    
    const selectEstado = document.getElementById("filterEstado");  
    const filterEstado = "pendiente"  

    const table = document.getElementById("usersTable");  
    const rows = table.getElementsByTagName("tr");  

    for (let i = 1; i < rows.length; i++) {  
        const cells = rows[i].getElementsByTagName("td");  
        let nombreFound = false;  
        let estadoFound = false;  

        // Filtrar por Nombre  
        if (cells[2]) { // Suponiendo que el Nombre y Apellido están en la tercera columna (índice 2)  
            const nombreText = cells[2].textContent || cells[2].innerText;  
            nombreFound = nombreText.toLowerCase().includes(filterNombre);  
        }  

        // Filtrar por Estado  
        if (cells[5]) { // Suponiendo que el Estado está en la sexta columna (índice 5)  
            const estadoText = cells[5].textContent || cells[5].innerText;  
            estadoFound = estadoText === filterEstado || filterEstado === "";  
        }  

        // Mostrar fila si coincide con ambos criterios de filtro  
        if (nombreFound && estadoFound) {  
            rows[i].style.display = "";  
        } else {  
            rows[i].style.display = "none";  
        }  
    }  
} 
</script>
<script>  
    window.onload = function() {  
        // Esperar a que el DOM esté completamente cargado  
        setTimeout(function() {  
            var successMessage = document.getElementById('successMessage');  
            var errorMessage = document.getElementById('errorMessage');  
            if (successMessage) {  
                successMessage.style.display = 'none'; // Ocultar mensaje de éxito  
            }  
            if (errorMessage) {  
                errorMessage.style.display = 'none'; // Ocultar mensaje de error  
            }  
        }, 5000); // 5000 milisegundos = 5 segundos  
    };  
</script>
</html>
