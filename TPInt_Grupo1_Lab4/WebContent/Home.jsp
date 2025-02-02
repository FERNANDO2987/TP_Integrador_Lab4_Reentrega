<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema de Gestión Bancaria</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
      
      height: 100%;
    margin: 0;
    padding: 0;
            
        }
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
        .sidebar a {
            color: #fff;
        }
        .sidebar a:hover {
            color: #007bff;
        }
        .content {
            margin-left: 0;
            transition: margin-left 0.3s ease;
            overflow-y: auto; /* Asegura que el contenido se desplace verticalmente sin problemas */
                flex: 1;  
            padding: 1px;  
        }
        .content.shift {
            margin-left: 200px;
        }
        .navbar {
            background-color: #343a40;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: #007bff;
        }
        .navbar-brand, .navbar-nav .nav-link {
            color: #343a40;
        }
        .navbar-nav .nav-link:hover {
            color: #007bff;
        }
        .navbar-nav .nav-item.active .nav-link {
            color: #007bff;
        }
        .menu-icon {
            cursor: pointer;
            font-size: 35px;
            color: white;
            z-index: 2; /* Asegura que el icono esté por encima de la barra lateral */
            position: fixed;
            top: 10px;
            left: 25px;
        }
        
         /* Opcional: si el formulario debe ocupar toda la altura */
    .content form {
      height: 100%;
    }
    </style>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-dark">
        <!-- Icono para abrir/cerrar el menú -->
        <span class="menu-icon" onclick="toggleSidebar()">&#9776;</span> 
        <a class="navbar-brand" href="#"></a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="sidebar" id="sidebar">
        <br>
        <br>
        <h4>Administrador</h4>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="#" onclick="cargarPagina('inicio')">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Clientes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('prestamos')">Préstamos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('listarUsuarios')">Listar Usuarios</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('listarClientes')">Listar Clientes</a>
            </li>
        </ul>
    </div>

    <div class="content container-fluid" id="contenidoPrincipal">
        <h2>¡Bienvenido/a admin!</h2>
    </div>

    <script>
        function cargarPagina(pagina) {
            var contenido = document.getElementById('contenidoPrincipal');
            if (pagina === 'inicio') {
                contenido.innerHTML = '<h2>¡Bienvenido/a admin!</h2>';
            } else if (pagina === 'clientes') {
                contenido.innerHTML = '<h2>Clientes</h2><p>Aquí va la información de los clientes.</p>';
            } else if (pagina === 'prestamos') {
                contenido.innerHTML = '<h2>Préstamos</h2><p>Aquí va la información de los préstamos.</p>';
            } else if (pagina === 'listarUsuarios') {
                contenido.innerHTML = '<iframe src="servletListarUsuarios" width="90%" height="900px"></iframe>';
            } else if (pagina === 'listarClientes') {
                contenido.innerHTML = '<iframe src="servletListarClientes" width="90%" height="900px"></iframe>';
            }
        }

        function toggleSidebar() {
            var sidebar = document.getElementById('sidebar');
            var content = document.getElementById('contenidoPrincipal');
            sidebar.classList.toggle('show');
            content.classList.toggle('shift');
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
