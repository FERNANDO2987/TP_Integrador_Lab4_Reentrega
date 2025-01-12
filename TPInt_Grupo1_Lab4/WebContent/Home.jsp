<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
    <title>Sistema de Gestión Bancariasssssssss</title>  
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">  
    <style>  
        body {  
            display: flex;  
            min-height: 100vh;  
            flex-direction: column;  
        }  
        .sidebar {  
            height: 100vh;  
            background-color: #343a40;  
            padding: 15px;  
            color: #fff;  
        }  
        .sidebar a {  
            color: #fff;  
        }  
        .sidebar a:hover {  
            color: #007bff;  
        }  
        .content {  
            flex: 1;  
            padding: 20px;  
        }  
        .navbar {  
            background-color: #007bff; /* Cambia el color de la franja superior */  
        }  
        .navbar-brand, .navbar-nav .nav-link {  
            color: #fff;  
        }  
        .navbar-nav .nav-link:hover {  
            color: #ffdd57; /* Cambia el color al pasar el ratón */  
        }  
        .navbar-nav .nav-item.active .nav-link {  
            color: #ffdd57; /* Cambia el color del enlace activo */  
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
                    <a class="nav-link" href="#">Logout</a>  
                </li>  
            </ul>  
        </div>  
    </nav>  

    <div class="d-flex">  
        <div class="sidebar">  
            <h4>Administrador</h4>  
            <ul class="nav flex-column">  
                <li class="nav-item">  
                    <a class="nav-link active" href="#">Inicio</a>  
                </li>  
                <li class="nav-item">  
                    <a class="nav-link" href="#">Clientes</a>  
                </li>  
                <li class="nav-item">  
                    <a class="nav-link" href="#">Préstamos</a>  
                </li>  
            </ul>  
        </div>  

        <div class="content container-fluid">  
            <h2>¡Bienvenido/a admin!</h2>  
            <!-- Aquí va el contenido principal de la página -->  
        </div>  
    </div>  

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>  
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
</body>  
</html>  







