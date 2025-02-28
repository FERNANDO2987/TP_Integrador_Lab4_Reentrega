
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Pais"%>
<%@ page import="entidad.Usuario"%>
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

<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="path/to/your/styles.css">
<!-- Personaliza esta l�nea -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>

<title>Lista de Clientes</title>

<style>
.custom-container {
	width: 95%;
	max-width: 2000px; /* Ajusta el ancho m�ximo seg�n sea necesario */
	margin: auto;
}

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

	<div class="custom-container mt-5">


		<!-- Contenedor centrado para el encabezado -->
		<div class="text-center mb-4">
			<h2 class="text-2xl text-blue-600">Lista de Clientes</h2>
		</div>

		<!-- Bot�n Agregar alineado a la derecha -->
		<div class="mb-4 text-right">
			<a href="AgregarCliente.jsp"
				class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600">
				<i class="fas fa-user-plus"></i> Agregar Cliente
			</a>
		</div>

		<!-- Buscador alineado a la izquierda -->
		<div class="mb-4">
			<input type="text" id="searchInput"
				class="form-control w-full p-2 rounded-lg border-gray-300"
				placeholder="Buscar cliente..." onkeyup="filterTable()">
		</div>

		<%
			// Obtener lista de usuarios y mensaje de error  
			List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
			String mensajeExito = (String) request.getAttribute("mensajeExito");
			String mensajeError = (String) request.getAttribute("mensajeError");
		%>

		<!-- Mostrar mensaje de �xito -->
		<%
			if (mensajeExito != null) {
		%>
		<div id="successMessage" class="alert alert-success mb-4">
			<i class="fas fa-check-circle"></i>
			<%=mensajeExito%>
		</div>
		<%
			}
		%>

		<%
			if (mensajeError != null) {
		%>
		<div id="errorMessage" class="alert alert-error mb-4">
			<i class="fas fa-exclamation-circle"></i>
			<%=mensajeError%>
		</div>
		<%
			}
		%>

		<%
			if (clientes != null && !clientes.isEmpty()) {
		%>
		<!-- Tabla de clientes -->
		<div class="overflow-x-auto">
			<table
				class="table-auto w-full bg-white border-collapse border border-gray-300"
				id="usersTable">
				<thead class="bg-gray-200">
					<tr>
						<th class="px-4 py-2 border">ID</th>
						<th class="px-4 py-2 border">DNI</th>
						<th class="px-4 py-2 border">Cuil</th>
						<th class="px-4 py-2 border">Nombre</th>
						<th class="px-4 py-2 border">Apellido</th>
						<th class="px-4 py-2 border">Sexo</th>
						<th class="px-4 py-2 border">Pa�s</th>
						<th class="px-4 py-2 border">Fecha Nacimiento</th>
						<th class="px-4 py-2 border">Direcci�n</th>
						<th class="px-4 py-2 border">Localidad</th>
						<th class="px-4 py-2 border">Provincia</th>
						<th class="px-4 py-2 border">Correo</th>
						<th class="px-4 py-2 border">Tel�fono</th>
						<th class="px-4 py-2 border">Acciones</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<%
						for (Cliente cliente : clientes) {
					%>
					<tr>
						<td class="px-4 py-2 border"><%=cliente.getId()%></td>
						<td class="px-4 py-2 border"><%=cliente.getDni()%></td>
						<td class="px-4 py-2 border"><%=cliente.getCuil()%></td>
						<td class="px-4 py-2 border"><%=cliente.getNombre()%></td>
						<td class="px-4 py-2 border"><%=cliente.getApellido()%></td>
						<td class="px-4 py-2 border"><%=cliente.getSexo()%></td>
						<td class="px-4 py-2 border"><%=cliente.getPaisNacimiento().getNombre()%></td>
						<td class="px-4 py-2 border"><%=cliente.getFechaNacimiento()%></td>
						<td class="px-4 py-2 border"><%=cliente.getDireccion()%></td>
						<td class="px-4 py-2 border"><%=cliente.getLocalidad().getNombre()%></td>
						<td class="px-4 py-2 border"><%=cliente.getProvincia().getNombre()%></td>
						<td class="px-4 py-2 border"><%=cliente.getCorreo()%></td>
						<td class="px-4 py-2 border"><%=cliente.getTelefono()%></td>
						<td class="px-4 py-2 border">
							<div class="relative inline-block text-rihtg">
								<!-- Bot�n para abrir el men� con el icono de tres puntos -->
								<button type="button"
									class="bg-transparent text-gray-600 hover:text-gray-900 px-3 py-2 rounded-full"
									id="dropdownButton<%=cliente.getId()%>">
									<i class="fas fa-ellipsis-v"></i>
									<!-- Icono de tres puntos -->
								</button>
								<!-- Men� desplegable oculto por defecto -->
								<div
									class="dropdown-menu absolute right-0 hidden bg-white border border-gray-200 rounded-md shadow-lg top-full mb-1 z-10"
									id="dropdown<%=cliente.getId()%>">



									<!-- Opci�n para Modificar -->
									<a
										class="dropdown-item flex items-center px-8 py-2 text-sm text-gray-700 hover:bg-gray-100"
										href="ModificarCliente.jsp?id=<%=cliente.getId()%>"> <i
										class="fas fa-edit mr-5"></i> <!-- �cono con margen a la derecha -->
										Modificar
									</a> <a href="servletGestionarCuentas?id=<%=cliente.getId()%>"
										class="dropdown-item flex items-center px-8 py-2 text-sm text-gray-700 hover:bg-gray-100">
										<i class="fas fa-cogs mr-5"></i> <!-- �cono con margen a la derecha -->
										Gestionar Cuentas
									</a>
									<!-- Opci�n para Eliminar -->
									<a href="servletEliminarCliente?id=<%=cliente.getId()%>"
										class="dropdown-item flex items-center px-8 py-4 text-sm text-red-500 hover:bg-red-100"
										onclick="return confirm('�Est�s seguro de que deseas eliminar este cliente?');">
										<i class="fas fa-trash-alt mr-5"></i> <!-- �cono con margen a la derecha -->
										Eliminar
									</a>
								</div>

							</div>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<div class="pagination mt-4">
				<button onclick="prevPage()" id="btnPrev"
					class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Anterior</button>
				<span id="pageNumbers"></span>
				<button onclick="nextPage()" id="btnNext"
					class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Siguiente</button>
			</div>
			<%
				} else {
			%>
			<div class="bg-blue-100 text-blue-800 p-4 rounded-lg mb-4"
				role="alert">
				<i class="fas fa-info-circle"></i> No se encontraron clientes.
			</div>
			<%
				}
			%>
		</div>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>




<script>  
// Manejo del men� desplegable  
document.querySelectorAll('[id^="dropdownButton"]').forEach(button => {  
    button.addEventListener('click', function(event) {  
        event.stopPropagation(); // Evita que el evento burbujee al documento  
        const menu = document.getElementById('dropdown' + this.id.replace('dropdownButton', ''));  
        // Cerrar otros men�s si est�n abiertos  
        document.querySelectorAll('.dropdown-menu').forEach(dropdown => {  
            if (dropdown !== menu) {  
                dropdown.classList.add('hidden');  
            }  
        });  
        menu.classList.toggle('hidden'); // Alterna la visibilidad del men�  
    });  
});  

// Cerrar el men� si se hace clic fuera de �l  
document.addEventListener('click', function(event) {  
    const dropdowns = document.querySelectorAll('.dropdown-menu');  
    dropdowns.forEach(dropdown => {  
        if (!dropdown.classList.contains('hidden') && !dropdown.contains(event.target)) {  
            dropdown.classList.add('hidden'); // Ocultar el men�  
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
        // Esperar a que el DOM est� completamente cargado  
        setTimeout(function() {  
            var successMessage = document.getElementById('successMessage');  
            var errorMessage = document.getElementById('errorMessage');  
            if (successMessage) {  
                successMessage.style.display = 'none'; // Ocultar mensaje de �xito  
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
