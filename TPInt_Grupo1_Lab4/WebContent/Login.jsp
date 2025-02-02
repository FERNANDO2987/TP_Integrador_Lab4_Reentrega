<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">  
<style>
body {
     background-color: #343a40;
    background-size: cover; /* Cubrir toda la pantalla */
    background-position: center; /* Centrar la imagen */
    height: 100vh; /* Hacer que el fondo ocupe toda la altura de la pantalla */
    display: flex;
    justify-content: center;
    align-items: center;
    font-color: #fff;
}

/* Contenedor del formulario */
.login-container {
    background-color: rgba(135, 206, 235, 0.2); /* Fondo blanco semi-transparente */
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 100%;
    max-width: 400px; /* Máxima anchura del formulario */
}

h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #fff;
}

.form-group {
    margin-bottom: 15px;
}

input[type="submit"] {
    width: 100%; /* Aseguramos que el botón ocupe todo el ancho */
}

.label{
	color: #fff;
}



</style>
</head>
<body>
<div class="login-container">  
        <h2 class="text-center">Login</h2>  
        <div class="row justify-content-center">  
            <div class="col-md-12">  
                <%   
                    String error = request.getParameter("error");  
                    if ("true".equals(error)) {   
                %>  
                    <div class="alert alert-danger" role="alert">  
                        Usuario y/o contraseña no válidos. Inténtalo de nuevo.  
                    </div>  
                <% } %>  
                <form action="servletLogin" method="post">   
                    <div class="form-group">  
                        <label for="usuario">Usuario</label>  
                        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingresa tu usuario">  
                    </div>  
                    <div class="form-group">  
                        <label for="contrasenia">Contraseña</label>  
                        <input type="password" class="form-control" id="contrasenia" name="contrasenia" placeholder="Ingresa tu contraseña">  
                    </div>  
                    <input type="submit" value="Login" class="btn btn-primary btn-block" name="btnAceptar" id="btnAceptar" disabled>  
                </form>  
            </div>  
        </div>  
    </div>  

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>  
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  
    <script src="js/toggleButtonStateScripts.js"></script>  
</body>
</html>