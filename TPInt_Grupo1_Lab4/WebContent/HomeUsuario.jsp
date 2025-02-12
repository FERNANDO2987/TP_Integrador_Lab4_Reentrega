<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema de Gesti�n Bancaria</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
      
      height: 100%;
    margin: 0;
    padding: 0;
     overflow-x: hidden; /* Oculta el scroll horizontal */
            
        }
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            padding: 15px;
            color: #fff;
            position: fixed;
            width: 200px; /* Ancho de la barra lateral */
            top: 0; /* Asegura que la barra lateral est� alineada arriba */
            left: -200px; /* Inicialmente oculta */
            transition: left 0.3s ease; /* Animaci�n para el desplazamiento */
            z-index: 1; /* Asegura que la barra lateral est� detr�s del bot�n */
            overflow-y: auto; /* Permite que la barra lateral tenga su propio scroll si es necesario */
        }
        .sidebar.show {
            left: 0; /* Cuando se muestra, se mueve a la posici�n 0 */
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
            z-index: 2; /* Asegura que el icono est� por encima de la barra lateral */
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
        <!-- Icono para abrir/cerrar el men� -->
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
        <h4>Usuario</h4>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="#" onclick="cargarPagina('inicio')">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Solicitar Prestamo</a>
            </li>
                              <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Pagar prestamo </a>
            </li>
             <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Datos Personales </a>
            </li>
            
                  <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Cuentas Asociadas </a>
            </li>
            
                       <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Transferir cuenta propia </a>
            </li>
            
                              <li class="nav-item">
                <a class="nav-link" href="#" onclick="cargarPagina('clientes')">Transferir cuenta externa </a>
            </li>
            
            
            
        
        </ul>
    </div>

    <div class="content container-fluid" id="contenidoPrincipal">
        <h2>�Bienvenido/a Usuario!</h2>
    </div>

    <script>
        function cargarPagina(pagina) {
            var contenido = document.getElementById('contenidoPrincipal');
            if (pagina === 'inicio') {
                contenido.innerHTML = '<h2>�Bienvenido/a admin!</h2>';
            }  else if (pagina === 'pagarPrestamo') {
                contenido.innerHTML = '<iframe src="servletListarUsuarios" width="90%" height="900px"></iframe>';
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
