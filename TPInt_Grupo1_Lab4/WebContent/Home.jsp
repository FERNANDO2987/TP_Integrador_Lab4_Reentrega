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
            display: flex;  
        }  
        .sidebar {  
            height: 100vh;  
            background-color: #f8f9fa;  
            padding: 15px;  
        }  
        .sidebar a {  
            color: #333;  
        }  
        .sidebar a:hover {  
            color: #007bff;  
        }  
    </style>  
</head>  
<body>  

    <div class="sidebar">  
        <h4>Administración</h4>  
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

    <div class="container-fluid">  
        <h2>¡Bienvenido/a admin!</h2>  
        <!-- Aquí va el contenido principal de la página -->  
    </div>  

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>  
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
</body>  
</html>