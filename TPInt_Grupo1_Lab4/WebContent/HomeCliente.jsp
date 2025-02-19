<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Gestion Bancaria</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    <style>
        body {
            height: 100%;
            margin: 0;
            padding: 0;
            overflow-x: hidden;
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

        /* Icono del men� */
        .menu-icon {
            cursor: pointer;
            font-size: 35px;
            color: white;
            z-index: 2;
            position: fixed;
            top: 10px;
            left: 25px;
        }
        #tituloSistema {
    transition: margin-left 0.3s ease; /* Transici�n suave */
}

#tituloSistema.shift {
    margin-left: 220px; /* Se mueve cuando el men� est� abierto */
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
        String nombreUsuario = "Usuario desconocido";
        if (usuario.getCliente() != null) {
            nombreUsuario = usuario.getCliente().getNombre();
        }
    %>

    <!-- Barra de navegaci�n -->
    <nav class="bg-gray-800 text-white flex justify-between items-center p-4">
        <span class="menu-icon" onclick="toggleSidebar()">&#9776;</span> 
      <div id="tituloSistema" class="transition-all duration-300 ml-12 text-xl">
    Sistema de Gestion Bancaria
</div>

        <div>
           
           <a href="servletLogout?id=id=<%= usuario.getId() %>" class="text-white hover:text-blue-500" onclick="return confirm('¿Estas seguro que quieres Salir??');">Logout</a>
   
 
   
                
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
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('solicitarPrestamo')">Solicitar Prestamo</a>
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
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('transferenciaCuentaPropia')">Transferencia cuenta propia</a>
            </li>
            <li>
                <a class="text-white hover:text-blue-500 block p-2" href="#" onclick="cargarPagina('transferenciaCuentaExterna')">Transferencia cuenta externa</a>
            </li>
           
        </ul>
    </div>

    <!-- Contenido principal -->
    <div class="content" id="contenidoPrincipal">
        <h5>Bienvenido, <%= usuario.getCliente().getNombre() %></h5>
    </div>

     <script>
    function cargarPagina(pagina) {
        let contenido = document.getElementById('contenidoPrincipal');
        contenido.innerHTML = ''; // Limpia el contenido antes de cargar

        switch (pagina) {
            case 'inicio':
                contenido.innerHTML = '<iframe src="servletGraficos" width="90%" height="900px"></iframe>';
                break;
    
         
        }
    }

        function toggleSidebar() {
            var sidebar = document.getElementById('sidebar');
            var content = document.getElementById('contenidoPrincipal');
            var tituloSistema = document.getElementById('tituloSistema');

            sidebar.classList.toggle('show');
            content.classList.toggle('shift');
            tituloSistema.classList.toggle('shift'); // Mueve el t�tulo
        }

    </script>
</body>
</html>