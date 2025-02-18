<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>  
<html lang="es">  
<head>  
    <meta charset="ISO-8859-1">  
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Login</title>  
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">  
</head>  
<body class="h-screen flex items-center justify-center bg-gradient-to-r from-gray-900 to-gray-800 text-gray-100">  

    <div class="w-full max-w-md bg-gray-850 p-8 rounded-lg shadow-2xl border border-gray-700">  
        <h2 class="text-center text-3xl font-extrabold mb-6">Iniciar Sesi�n</h2>  

        <% String error = request.getParameter("error");  
           if ("true".equals(error)) { %>  
            <div class="mb-4 p-3 bg-red-500 text-white text-sm rounded">  
                Usuario y/o contrase�a no v�lidos. Int�ntalo de nuevo.  
            </div>  
        <% } %>  

        <form action="servletLogin" method="post" class="space-y-4">  
            <div>  
                <label for="usuario" class="block text-sm font-semibold">Usuario</label>  
                <input type="text" id="usuario" name="usuario" class="mt-1 w-full p-3 rounded bg-gray-700 text-white border border-gray-600 focus:ring focus:ring-blue-500 focus:border-blue-500" placeholder="Ingresa tu usuario" required>  
            </div>  

            <div>  
                <label for="contrasenia" class="block text-sm font-semibold">Contrase�a</label>  
                <input type="password" id="contrasenia" name="contrasenia" class="mt-1 w-full p-3 rounded bg-gray-700 text-white border border-gray-600 focus:ring focus:ring-blue-500 focus:border-blue-500" placeholder="Ingresa tu contrase�a" required>  
            </div>  

            <button type="submit" class="w-full bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-200 ease-in-out transform hover:scale-105" name="btnAceptar" id="btnAceptar">  
                Iniciar sesi�n  
            </button>  
        </form>  

        <div class="mt-4 text-center">  
            <a href="#" class="text-blue-400 hover:underline">�Olvidaste tu contrase�a?</a>  
        </div>  
    </div>  

</body>  
</html>