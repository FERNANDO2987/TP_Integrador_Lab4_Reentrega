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
    <link rel="stylesheet" href="path/to/your/styles.css">  
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <title>Historial de Prestamos</title>  
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
            background-color: #28a745; /* Verde */  
            color: white;  
        }  
        .alert-error {  
            background-color: #dc3545; /* Rojo */  
            color: white;  
        }  
        .estado-vigente {  
            color: green;  
        }  
        .estado-finalizado {  
            color: blue;  
        }  
        .estado-rechazado {  
            color: red;  
        }  
        .estado-en-revision {  
            color: orange;  
        }  
    </style>  
</head>  
<body class="bg-gray-100">  
<div class="container mx-auto mt-5">  
    <div class="text-center mb-4">  
        <h2 class="text-2xl text-blue-600">Historial de Prestamos</h2>  
    </div>  

    <%   
        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");  
    %>  

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

    <% if (prestamos != null && !prestamos.isEmpty()) { %>  
        <div class="flex space-x-4 mb-4">  
            <input type="text" id="filterNombre" class="form-control w-full p-2 rounded-lg border-gray-300" placeholder="Filtrar por Nombre..." onkeyup="filterTable()">  
            <select id="filterEstado" class="form-control w-full p-2 rounded-lg border-gray-300" onchange="filterTable()">  
                <option value="">Filtrar por Estado</option>  
                <option value="vigente">Vigente</option>  
                <option value="finalizado">Finalizado</option>  
                <option value="rechazado">Rechazado</option>  
                <option value="revisión">En Revisión</option>  
            </select>  
        </div>  

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
                 <td class="border px-4 py-2">  
                 <% if (prestamo.getEstado().equals("vigente")) { %>  
                 <span class="bg-blue-500 text-white px-2 py-1 rounded">Vigente</span>  
                 <% } else if (prestamo.getEstado().equals("finalizado")) { %>  
                 <span class="bg-green-500 text-white px-2 py-1 rounded">Finalizado</span>  
                  <% } else if (prestamo.getEstado().equals("rechazado")) { %>  
                  <span class="bg-red-500 text-white px-2 py-1 rounded">Rechazado</span>  
                  <% } else if (prestamo.getEstado().equals("revisión")) { %>  
                  <span class="bg-orange-500 text-white px-2 py-1 rounded">En Revisión</span>  
                  <% } else { %>  
                  <span class="bg-gray-300 text-gray-700 px-2 py-1 rounded">Sin Estado</span> <!-- Cambié "Desconocido" por "Sin Estado" -->  
                  <% } %>  
               </td>
        </tr>  
    <% } %>  
</tbody>
            </table>  
        </div>  
    <% } else { %>  
        <div class="bg-blue-100 text-blue-800 p-4 rounded-lg mb-4" role="alert">  
            <i class="fas fa-info-circle"></i> No se encontraron historial de prestamos.  
        </div>  
    <% } %>  
    
    <br>
        <br>
        
        
          <div class="text-center mb-4">  
        <h2 class="text-2xl text-blue-600">Barra de Estados</h2>  
    </div> 
    <!-- Contenedor para el gráfico -->
    
<div class="mt-6 p-4 bg-white shadow rounded-lg">
    <canvas id="prestamosChart"></canvas>
</div>
</div>  

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  

<script>  
function filterTable() {
    var nombreFiltro = document.getElementById("filterNombre").value.toLowerCase();
    var estadoFiltro = document.getElementById("filterEstado").value.toLowerCase();
    var table = document.getElementById("usersTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 1; i < tr.length; i++) { // Comenzamos en 1 para saltar el encabezado
        var tdNombre = tr[i].getElementsByTagName("td")[2]; // Columna de Nombre y Apellido
        var tdEstado = tr[i].getElementsByTagName("td")[5]; // Columna de Estado
        if (tdNombre && tdEstado) {
            var nombreTexto = tdNombre.textContent.toLowerCase();
            var estadoTexto = tdEstado.textContent.toLowerCase();

            var coincideNombre = nombreTexto.includes(nombreFiltro);
            var coincideEstado = estadoFiltro === "" || estadoTexto.includes(estadoFiltro);

            if (coincideNombre && coincideEstado) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


window.onload = function() {  
    setTimeout(function() {  
        const successMessage = document.getElementById('successMessage');  
        const errorMessage = document.getElementById('errorMessage');  
        if (successMessage) {  
            successMessage.style.display = 'none';  
        }  
        if (errorMessage) {  
            errorMessage.style.display = 'none';  
        }  
    }, 5000);  
};  
</script>  

<script>
document.addEventListener("DOMContentLoaded", function() {
    const prestamos = <%= prestamos != null ? prestamos.size() : 0 %>;

    if (prestamos > 0) {
        // Obtener los datos desde la tabla
        let estados = { "vigente": 0, "finalizado": 0, "rechazado": 0, "revisión": 0 };
        
        <% for (Prestamo prestamo : prestamos) { %>
            estados["<%= prestamo.getEstado() %>"]++;
        <% } %>

        // Configuración del gráfico
        const ctx = document.getElementById("prestamosChart").getContext("2d");
        new Chart(ctx, {
            type: "bar",
            data: {
                labels: Object.keys(estados),
                datasets: [{
                    label: "Cantidad de Préstamos",
                    data: Object.values(estados),
                    backgroundColor: ["#3B82F6", "#10B981", "#EF4444", "#F59E0B"],
                    borderColor: ["#1E40AF", "#047857", "#B91C1C", "#B45309"],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { enabled: true }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { stepSize: 1 }
                    }
                }
            }
        });
    }
});
</script>
</body>  
</html>