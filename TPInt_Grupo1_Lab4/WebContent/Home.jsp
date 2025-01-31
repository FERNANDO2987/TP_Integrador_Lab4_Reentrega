<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
    <title>Sistema de Gestión Bancaria</title>  
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">  
    <style>  
 body {
    overflow-x: hidden; /* Desactiva el scroll horizontal */
}
    .sidebar {  
        height: 100vh;  
        background-color: #343a40;  
        padding: 15px;  
        color: #fff;  
        position: fixed;  
        width: 200px; /* Ancho de la barra lateral */  
        top: 0;  
        left: 0;  
    }  
    .sidebar a {  
        color: #fff;  
    }  
    .sidebar a:hover {  
        color: #007bff;  
    }  
    .content {
        flex: 1;
        padding: 0.1px;
        margin-left: calc(150px + 1rem); /* Ajusta dinámicamente según el ancho de la barra lateral */
        margin-right: 0; /* No es necesario cambiar este valor */
        
    }

    .navbar {  
        background-color: #343a40; /* Color de la franja superior */
        display: flex; /* Para usar flexbox */
        justify-content: space-between; /* Esto asegura que los elementos se distribuyan de manera automática */
        align-items: center; /* Centra los elementos verticalmente */
         color: #007bff;  
    }  
    .navbar-brand, .navbar-nav .nav-link {  
        color: #343a40;  
    }  
    .navbar-nav .nav-link:hover {  
        color: #007bff; /* Color al pasar el ratón */  
    }  
    .navbar-nav .nav-item.active .nav-link {  
        color: #007bff; /* Color del enlace activo */  
    }  
</style>

    
</head>  
<body>  

    <nav class="navbar navbar-expand-lg navbar-dark">  
        <a class="navbar-brand" href="#">Gestión Bancaria</a>  
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">  
            <span class="navbar-toggler-icon"></span>  
        </button>  
        <div class="collapse navbar-collapse" id="navbarNav">  
            <ul class="navbar-nav ml-auto">  
                <li class="nav-item">  
                	<form action="<%= request.getContextPath() %>/servletLogout" method="post">
                	   <button class="btn btn-link" href="#">Logout</button> 
                	</form>
                </li>  
            </ul>  
        </div>  
    </nav>  

    <div class="sidebar">  
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
                // Enlace al servlet que lista los usuarios
                contenido.innerHTML = '<iframe src="servletListarUsuarios" width="90%" height="900px"></iframe>';
            }
            
            else if (pagina === 'listarClientes') {
                // Enlace al servlet que lista los usuarios
                contenido.innerHTML = '<iframe src="servletListarClientes" width="90%" height="900px"></iframe>';
            }
            
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>  
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
</body>  
</html>
