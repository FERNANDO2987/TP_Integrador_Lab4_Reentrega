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
            top: 0; /* Asegura que la barra lateral est� alineada arriba */
            left: -200px; /* Inicialmente oculta */
            transition: left 0.3s ease; /* Animaci�n para el desplazamiento */
            z-index: 1; /* Asegura que la barra lateral est� detr�s del bot�n */
            overflow-y: auto; /* Permite que la barra lateral tenga su propio scroll si es necesario */
        }
        .sidebar.show {
            left: 0; /* Cuando se muestra, se mueve a la posici�n 0 */
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
            z-index: 2; /* Asegura que el icono est� por encima de la barra lateral */
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

    <!-- Barra de navegaci�n -->
    <nav class="bg-gray-800 text-white flex justify-between items-center p-4">
        <!-- Icono para abrir/cerrar el men� -->
        <span class="menu-icon" onclick="toggleSidebar()">&#9776;</span> 
<div class="flex justify-end items-center text-xl title relative left-16" id="title">
    Sistema de Gesti�n Bancaria
</div>


        <div>
            <a href="#" class="text-white hover:text-blue-500">Logout</a>
        </div>
    </nav>

    <!-- Barra lateral -->
    <div class="sidebar" id="sidebar">
        <br>
          <br>
        <h4 class="text-white text-xl">Cliente</h4>
        <ul class="space-y-2">
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('inicio')">Inicio</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="SolicitarPrestamo.jsp" onclick="cargarPagina('solicitarPrestamo')">Solicitar Prestamo</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('pagarPrestamo')">Pagar Prestamo</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('datosPersonales')">Datos Personales</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('cuentasAsociadas')">Cuentas Asociadas</a>
            </li>
            
             <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('transferencia')">Transferencia</a>
            </li>

        </ul>
    </div>

    <!-- Contenido principal -->
    <div class="content ml-0 transition-all" id="contenidoPrincipal">
        <h2 class="text-3xl font-bold p-6">�Bienvenido/a Cliente!</h2>
    </div>

    <script>
        function cargarPagina(pagina) {
            var contenido = document.getElementById('contenidoPrincipal');
            if (pagina === 'inicio') {
                contenido.innerHTML = '<h2>�Bienvenido/a admin!</h2>';
            } else if (pagina === 'clientes') {
                contenido.innerHTML = '<h2>Clientes</h2><p>Aqu� va la informaci�n de los clientes.</p>';
            } else if (pagina === 'solicitarPrestamo') {
                contenido.innerHTML = 'servletAgregarPrestamo';
            } else if (pagina === 'listarUsuarios') {
                contenido.innerHTML = '<iframe src="servletListarUsuarios" width="90%" height="900px"></iframe>';
            } else if (pagina === 'transferencia'){
            	contenido.innerHTML = '<iframe src="servletTransferencia" width="90%" height="900px"></iframe>';
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
