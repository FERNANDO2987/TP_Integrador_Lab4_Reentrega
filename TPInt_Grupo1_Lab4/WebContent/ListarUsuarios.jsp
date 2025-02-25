
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ page import="java.util.List" %>  
<%@ page import="entidad.Usuario" %>
<%
        Usuario usuarioSession = (Usuario) session.getAttribute("usuario");
        if (usuarioSession == null) {
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
    <title>Lista de Clientes</title>  
    
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
<body class="bg-gray-100">  

<div class="container mx-auto mt-5">  
    <!-- Contenedor centrado para el encabezado -->
    <div class="text-center mb-4">
        <h2 class="text-2xl text-blue-600">Lista de Usuarios</h2>
    </div>




    <!-- Buscador alineado a la izquierda -->
    <div class="mb-4">
        <input type="text" id="searchInput" class="form-control w-full p-2 rounded-lg border-gray-300" placeholder="Buscar cliente..." onkeyup="filterTable()">  
    </div>

    <%   
    // Obtener lista de usuarios y mensaje de error  
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");  
        String mensajeExito = (String) request.getAttribute("mensajeExito");  
        String mensajeError = (String) request.getAttribute("mensajeError");  
    %>  

      <!-- Mostrar mensaje de éxito -->
  <%  

    if (mensajeExito != null) {  
%>  
    <div id="successMessage" class="alert alert-success mb-4">  
        <i class="fas fa-check-circle"></i> <%= mensajeExito %>  
    </div>  
<%  
    }  
%>   

<%  
  
    if (mensajeError != null) {  
%>  
    <div id="errorMessage" class="alert alert-error mb-4">  
        <i class="fas fa-exclamation-circle"></i> <%= mensajeError %>  
    </div>  
<%  
    }  
%>

    <% if (mensajeError != null) { %>  
        <!-- Mostrar mensaje de error -->  
        <div class="alert alert-danger" role="alert">  
            <i class="fas fa-exclamation-circle"></i> <%= mensajeError %>  
        </div>  
    <% } else if (usuarios != null && !usuarios.isEmpty()) { %>   
        <!-- Tabla de clientes -->  
        <div class="overflow-x-auto">  
            <table class="table-auto w-full bg-white border-collapse border border-gray-300" id="usersTable">  
                <thead class="bg-gray-200">  
                    <tr>  
                        <th class="px-4 py-2 border">ID</th>  
                        <th class="px-4 py-2 border">Usuario</th>  
                        <th class="px-4 py-2 border">Cliente</th>
                        <th class="px-4 py-2 border">Admin</th>  
                        
                        <th class="px-4 py-2 border">Acciones</th>  
                    </tr>  
                </thead>  
                 <tbody id="tableBody"> 
                    <% for (Usuario usuario : usuarios) { %>  
                        <tr>  
                            <td class="px-4 py-2 border"><%= usuario.getId() %></td>  
                            <td class="px-4 py-2 border"><%= usuario.getUsuario() %></td>  
                            <td class="px-4 py-2 border"><%= usuario.getCliente() != null ? usuario.getCliente().getId() : "N/A" %></td>    
                                  <td class="px-4 py-2 border">  
                    <span class="badge <%= usuario.isAdmin() ? "bg-green-500 text-white" : "bg-red-500 text-white" %> px-2 py-1 rounded-full">  
                        <i class="<%= usuario.isAdmin() ? "fas fa-check" : "fas fa-times" %> mr-1"></i> <!-- Ícono de verificación o cruz -->  
                    <%= usuario.isAdmin() ? "Sí" : "No" %>  
                   </span>  
</td>
                         
                            <td class="px-4 py-2 border">  
                                <div class="relative inline-block text-left">
                                    <!-- Botón para abrir el menú con el icono de tres puntos -->
                                    <button type="button" class="bg-transparent text-gray-600 hover:text-gray-900 px-3 py-2 rounded-full" id="dropdownButton<%= usuario.getId() %>">
                                        <i class="fas fa-ellipsis-v"></i> <!-- Icono de tres puntos -->
                                    </button>
                                    <!-- Menú desplegable oculto por defecto -->
                                 <div class="dropdown-menu absolute right-0 hidden bg-white border border-gray-200 rounded-md shadow-lg mt-1 z-10" id="dropdown<%= usuario.getId() %>">
    <!-- Opción para Modificar -->
 
    <!-- Opción para Eliminar -->
    <a href="servletEliminarUsuario?id=<%= usuario.getId() %>" class="dropdown-item flex items-center px-4 py-2 text-sm text-red-500 hover:bg-red-100" onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?');">
        <i class="fas fa-trash-alt mr-2"></i> <!-- Ícono con margen a la derecha -->
        Eliminar
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
        <div class="bg-blue-100 text-blue-800 p-4 rounded-lg mb-4" role="alert">  
            <i class="fas fa-info-circle"></i> No se encontraron usuarios.  
        </div>  
    <% } %>  
</div>  


</body>  

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
<script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  

<script>  
// Manejo del menú desplegable  
document.querySelectorAll('[id^="dropdownButton"]').forEach(button => {  
    button.addEventListener('click', function(event) {  
        event.stopPropagation(); // Evita que el evento burbujee al documento  
        const menu = document.getElementById('dropdown' + this.id.replace('dropdownButton', ''));  
        // Cerrar otros menús si están abiertos  
        document.querySelectorAll('.dropdown-menu').forEach(dropdown => {  
            if (dropdown !== menu) {  
                dropdown.classList.add('hidden');  
            }  
        });  
        menu.classList.toggle('hidden'); // Alterna la visibilidad del menú  
    });  
});  

// Cerrar el menú si se hace clic fuera de él  
document.addEventListener('click', function(event) {  
    const dropdowns = document.querySelectorAll('.dropdown-menu');  
    dropdowns.forEach(dropdown => {  
        if (!dropdown.classList.contains('hidden') && !dropdown.contains(event.target)) {  
            dropdown.classList.add('hidden'); // Ocultar el menú  
        }  
    });  
});  

function filterTable() {  
    const input = document.getElementById("searchInput");  
    const filter = input.value.toLowerCase();  
    const table = document.getElementById("usersTable");  
    const rows = table.getElementsByTagName("tr");  

    for (let i = 1; i < rows.length; i++) {  
        let cells = rows[i].getElementsByTagName("td");  
        let found = false;  

        for (let j = 0; j < cells.length; j++) {  
            if (cells[j]) {  
                let text = cells[j].textContent || cells[j].innerText;  
                if (text.toLowerCase().includes(filter)) {  
                    found = true;  
                    break;  
                }  
            }  
        }  

        rows[i].style.display = found ? "" : "none";  
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

</html>
