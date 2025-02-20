<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Gestión Bancaria</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    <style>
     html, body {
    height: 100%;
    margin: 0;
    padding: 0;
    overflow: hidden; /* Oculta el scroll */
}


        /* Barra lateral */
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            padding: 15px;
            color: #fff;
            position: fixed;
            width: 200px;
            top: 0;
            left: -200px;
            transition: left 0.3s ease;
            z-index: 1;
            overflow-y: auto;
            
        }

        .sidebar.show {
            left: 0;
        }

        /* Contenido principal */
        .content {
            margin-left: 0;
            transition: margin-left 0.3s ease;
            overflow-y: auto;
            flex: 1;
            padding: 1rem;
        }

        .content.shift {
            margin-left: 200px;
        }

        /* Icono del menú */
        .menu-icon {
            cursor: pointer;
            font-size: 30px;
            color: white;
            z-index: 2;
            position: absolute;
            left: 20px;
            top: 12px;
        }

        /* Título del sistema */
        #tituloSistema {
            transition: margin-left 0.3s ease;
        }

        #tituloSistema.shift {
            margin-left: 220px;
        }

        /* Contenedor principal */
     #contenidoPrincipal {
    width: calc(100vw - 200px); /* Ajustar al ancho restante */
    height: 100vh;
    overflow: hidden; /* Evita el scroll */
    position: relative;
}


        #contenidoPrincipal.shift {
            width: calc(100vw - 200px);
        }

        /* Iframe dentro del contenido */
     #contenidoPrincipal iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border: none;
    overflow: hidden;
}

/* Estilo del icono cuando el menú está abierto */
/* Estilo del icono cuando el mouse pasa sobre él */
.menu-icon:hover {
    color: #2196F3; /* Color verde al pasar el mouse */
    transform: scale(1.3); /* Hacerlo un poco más grande */
    cursor: pointer; /* Cambiar el cursor a mano */
}


.logout-icon:hover {
    color: #2196F3; /* Color verde al pasar el mouse */
    transform: scale(1.2); /* Hacerlo un poco más grande */
  
     
}


.menu2-icon:hover {
    color: #2196F3; /* Color verde al pasar el mouse */
    transform: scale(1.1); /* Hacerlo un poco más grande */
    cursor: pointer; /* Cambiar el cursor a mano */
}

.tooltip {  
    display: none;  
    position: absolute;  
    background-color: white;  
    color: black;  
    padding: 5px;  
    border-radius: 4px;  
    z-index: 10;  
}  

.logout-icon {  
    position: relative; /* Esto permite que el tooltip se posicione relativo a este div */  
}  

.logout-icon:hover .tooltip {  
    display: block; /* Muestra el tooltip al pasar el mouse por encima */  
}

    </style>
</head>
<body class="bg-gray-100">
    <%
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        String nombreUsuario = (usuario.getCliente() != null) ? usuario.getCliente().getNombre() : "Usuario desconocido";
    %>


<!-- Barra de navegación -->  
<nav class="bg-gray-800 text-white flex items-center justify-between p-4 relative">  
    <!-- Icono del menú -->  
    <span class="menu-icon" onclick="toggleSidebar()">&#9776;</span>   

    <!-- Título centrado -->  
    <div id="tituloSistema" class="text-xl text-center flex-1">  
        Sistema de Gestión Bancaria  
    </div>  

    <!-- Nombre del usuario con imagen -->  
    <div class="mr-4 flex items-center">  
        <img src="data:image/jpeg;base64,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" alt="Usuario" class="h-8 w-8 rounded-full mr-2">  
       <br> <span class="text-white font-semibold"><%= nombreUsuario %></span>  
    </div>  

    <!-- Botón de Logout -->  
<div class="logout-icon">  
    <a href="servletLogout?id=<%= usuario.getId() %>"  
       class="text-white hover:text-blue-500"  
       onclick="return confirm('¿Estás seguro que quieres salir?');"  
       onmouseover="showTooltip(this)"  
       onmouseout="hideTooltip()">  
        Logout  
    </a>  
    <span class="tooltip" id="tooltip">Salir</span>  
</div>
</nav>


    <!-- Barra lateral -->
    <div class="sidebar" id="sidebar">
        <br>
           <br>
        <h4 class="text-white text-xl">Cliente</h4>
       
        
        <ul class="space-y-2">
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('inicio')">Inicio</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('solicitarPrestamo')">Solicitar Prestamo</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('pagarPrestamo')">Pagar Prestamo</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('datosPersonales')">Datos Personales</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('cuentasAsociadas')">Cuentas Asociadas</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('transferenciaCuentaPropia')">Transferencia cuenta propia</a></li>
            <li><a class="menu2-icon hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('transferenciaCuentaExterna')">Transferencia cuenta externa</a></li>
            
            
        </ul>
    </div>

    <!-- Contenido principal -->
    <div class="content" id="contenidoPrincipal">
        <h5>Bienvenido, <%= nombreUsuario %></h5>
    </div>

    <script>
        function cargarPagina(pagina) {
            let contenido = document.getElementById('contenidoPrincipal');
            contenido.innerHTML = ''; // Limpia el contenido antes de cargar

            let iframe = document.createElement('iframe');
            iframe.style.width = '100%';
            iframe.style.height = '100%';
            iframe.style.border = 'none';

            switch (pagina) {
                case 'inicio':
                    iframe.src = 'servletGraficos';
                    break;
            
            }

            contenido.appendChild(iframe);
        }

        function toggleSidebar() {
            var sidebar = document.getElementById('sidebar');
            var contenidoPrincipal = document.getElementById('contenidoPrincipal');
            var tituloSistema = document.getElementById('tituloSistema');
            var menuIcon = document.querySelector('.menu-icon'); // Icono del menú

            // Alternar visibilidad de la barra lateral
            sidebar.classList.toggle('show');
            contenidoPrincipal.classList.toggle('shift');
            tituloSistema.classList.toggle('shift');

            // Alternar la clase "active" para resaltar el icono
            menuIcon.classList.toggle('active');

            if (sidebar.classList.contains('show')) {
                contenidoPrincipal.style.width = 'calc(100vw - 200px)';
                tituloSistema.style.marginLeft = '220px';
            } else {
                contenidoPrincipal.style.width = '100vw';
                tituloSistema.style.marginLeft = '0px';
            }
        }

    </script>
</body>
</html>
