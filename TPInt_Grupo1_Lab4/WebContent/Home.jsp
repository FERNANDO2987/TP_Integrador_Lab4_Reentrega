<%@ page import="entidad.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Sistema de Gesti�n Bancaria</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    <style>
        body {
            height: 100%;
            margin: 0;
            padding: 0;
            overflow-x: hidden; /* Oculta el scroll horizontal */
        }
        /* Barra lateral */
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            padding: 15px;
            color: #fff;
            position: fixed;
            width: 200px; /* Ancho de la barra lateral */
            top: 0; /* Asegura que la barra lateral esté alineada arriba */
            left: -200px; /* Inicialmente oculta */
            transition: left 0.3s ease; /* Animación para el desplazamiento */
            z-index: 1; /* Asegura que la barra lateral esté detrás del botón */
            overflow-y: auto; /* Permite que la barra lateral tenga su propio scroll si es necesario */
        }
        .sidebar.show {
            left: 0; /* Cuando se muestra, se mueve a la posición 0 */
        }
        /* Contenido */
        .content {
            margin-left: 0;
            transition: margin-left 0.3s ease, margin-top 0.3s ease; /* Se agrega transici�n para el t�tulo */
            overflow-y: auto; /* Asegura que el contenido se desplace verticalmente sin problemas */
            flex: 1;  
            padding: 1px;  
        }
        .content.shift {
            margin-left: 200px;
        }
        /* Icono del men� */
        .menu-icon {
            cursor: pointer;
            font-size: 35px;
            color: white;
            z-index: 2; /* Asegura que el icono esté por encima de la barra lateral */
            position: fixed;
            top: 10px;
            left: 25px;
        }
        /* T�tulo */
        .title {
            transition: margin-left 0.3s ease; /* A�adir transici�n */
        }
        .title.shift {
            margin-left: 200px; /* Mover t�tulo cuando la barra lateral se abre */
        }
        /* Formulario */
        .content form {
            height: 100%;
        }
    </style>
</head>
<body class="bg-gray-100">
	 <%
      Usuario usuario = (Usuario)session.getAttribute("usuario");
      if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
      }
    %>

    <!-- Barra de navegaci�n -->
    <nav class="bg-gray-800 text-white flex justify-between items-center p-4">
        <!-- Icono para abrir/cerrar el men� -->

    <nav class="navbar navbar-expand-lg navbar-dark">
        <!-- Icono para abrir/cerrar el menú -->

        <span class="menu-icon" onclick="toggleSidebar()">&#9776;</span> 
     <div class="flex justify-end items-center text-xl ml-52"> <!-- Aqu� agregamos el margen izquierdo -->
    Sistema de Gestion Bancaria
</div>

        <div>
            <a href="#" class="text-white hover:text-blue-500">Logout</a>
        </div>
    </nav>

    <!-- Barra lateral -->
    <div class="sidebar" id="sidebar">
        <br>
          <br>
        <h4 class="text-white text-xl">Administrador</h4>
        <ul class="space-y-2">
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('inicio')">Inicio</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('clientes')">Clientes</a>
            </li>

            <li>

                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('prestamos')">Prestamos</a>

            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('prestamos')">Préstamos</a>


                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('prestamos')">Listar Pr�stamos</a>

            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('listarUsuarios')">Listar Usuarios</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('listarClientes')">Listar Clientes</a>
            </li>
              <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('historialPrestamos')">Historial Prestamos</a>
            </li>
            
        </ul>
    </div>


    <!-- Contenido principal -->
    <div class="content ml-0 transition-all" id="contenidoPrincipal">
        <h5 class="d-flex"> Bienvenido, <%= usuario.getCliente().getNombre() %></h5>


    <script>
        function cargarPagina(pagina) {
            var contenido = document.getElementById('contenidoPrincipal');
            if (pagina === 'inicio') {
                contenido.innerHTML = '<h2>¡Bienvenido/a admin!</h2>';
            } else if (pagina === 'clientes') {
                contenido.innerHTML = '<h2>Clientes</h2><p>Aquí va la información de los clientes.</p>';
            } else if (pagina === 'prestamos') {

                contenido.innerHTML = '<h2>Préstamos</h2><p>Aquí va la información de los préstamos.</p>';

                contenido.innerHTML = '<iframe src="servletPrestamosClientes" width="90%" height="900px"></iframe>';
                
                
            }
                else if (pagina === 'historialPrestamos') {
                    contenido.innerHTML = '<iframe src="servletListarTodosLosPrestamos" width="90%" height="900px"></iframe>';
  
                

            } else if (pagina === 'listarUsuarios') {
                contenido.innerHTML = '<iframe src="servletListarUsuarios" width="90%" height="900px"></iframe>';
            } else if (pagina === 'listarClientes') {
                contenido.innerHTML = '<iframe src="servletListarClientes" width="90%" height="900px"></iframe>';
            }
            else if (pagina === 'listarCuentas') {
            	// Enlace al servlet que lista los usuarios
                contenido.innerHTML = '<iframe src="servletListarCuentas" width="90%" height="900px"></iframe>';
            }
            
        }

        function toggleSidebar() {
            var sidebar = document.getElementById('sidebar');
            var content = document.getElementById('contenidoPrincipal');
            var title = document.getElementById('title');
            sidebar.classList.toggle('show');
            content.classList.toggle('shift');
            title.classList.toggle('shift');
        }
    </script>

</body>
</html>
