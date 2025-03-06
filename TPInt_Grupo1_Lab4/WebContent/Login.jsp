<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<style>
.eye-icon {
	cursor: pointer;
	
	/* Oculta el mensaje de error por defecto */  
.error-message {  
	display: none; 
}
</style>
<script>
	function togglePasswordVisibility() {
		const passwordInput = document.getElementById("contrasenia");
		const eyeIcon = document.getElementById("eye-icon");
		if (passwordInput.type === "password") {
			passwordInput.type = "text";
			eyeIcon.src = "https://cdn-icons-png.flaticon.com/128/709/709612.png"; // Ojo abierto  
		} else {
			passwordInput.type = "password";
			eyeIcon.src = "https://cdn-icons-png.flaticon.com/128/2767/2767146.png"; // Ojo cerrado  
		}
	}
	
	

</script>


	
</head>
<body
	class="h-screen flex items-center justify-center bg-gradient-to-r from-gray-900 to-gray-800 text-gray-100">

	<div
		class="w-full max-w-md bg-gray-850 p-8 rounded-lg shadow-2xl border border-gray-700">
		<h2 class="text-center text-3xl font-extrabold mb-6">Iniciar
			Sesión</h2>


<% 
   String error = request.getParameter("error");
   String success = request.getParameter("success");
%>

<% 
   if ("true".equals(error)) {
%>
   <div id="errorMessage" class="mb-4 p-3 bg-red-500 text-white text-sm rounded">
      Usuario y/o contraseña no válidos. Inténtalo de nuevo.
   </div>
<% 
   }
%>

<% 
   if ("true".equals(success)) {
%>
   <div id="successMessage" class="mb-4 p-3 bg-green-500 text-white text-sm rounded">
      ¡Inicio de sesión exitoso! Redirigiendo...
   </div>
<% 
   }
%>

		
		<script>
   // Llamar a la función mostrarMensaje si se ha definido el mensaje de error
   <% if ("true".equals(request.getParameter("error"))) { %>
      mostrarMensaje("errorMessage");
   <% } %>

   function ocultarMensaje() {
      // Ocultar el mensaje de éxito
      var mensaje = document.getElementById("successMessage");
      if (mensaje) {
         setTimeout(function() {
            mensaje.style.display = "none";
         }, 4000);
      }

      // Ocultar el mensaje de error
      var errorMensaje = document.getElementById("errorMessage");
      if (errorMensaje) {
         setTimeout(function() {
            errorMensaje.style.display = "none";
         }, 4000);
      }
   }

   // Función para mostrar el mensaje y luego ocultarlo
   function mostrarMensaje(tipo) {
      var mensaje = document.getElementById(tipo);
      if (mensaje) {
         mensaje.style.display = "block"; // Mostrar el mensaje  
         // Ocultar el mensaje después de 9 segundos (9000 milisegundos)  
         setTimeout(function() {
            mensaje.style.display = "none";
         }, 4000);
      }
   }
</script>


<script>
   // Llamar a la función ocultarMensaje cuando se cargue la página.
   window.onload = function() {
      ocultarMensaje(); // Llamamos a la función para ocultar los mensajes después de que la página haya cargado.
   };

   // Función para ocultar los mensajes después de un tiempo.
   function ocultarMensaje() {
      var mensajeExito = document.getElementById("successMessage");
      var mensajeError = document.getElementById("errorMessage");

      // Si el mensaje de éxito existe, ocultarlo después de 4 segundos.
      if (mensajeExito) {
         setTimeout(function() {
            mensajeExito.style.display = "none";
         }, 4000); // El mensaje se ocultará después de 4 segundos.
      }

      // Si el mensaje de error existe, ocultarlo después de 4 segundos.
      if (mensajeError) {
         setTimeout(function() {
            mensajeError.style.display = "none";
         }, 4000); // El mensaje se ocultará después de 4 segundos.
      }
   }
</script>

		<form action="servletLogin" method="post" class="space-y-4">
			<div>
				<label for="usuario" class="block text-sm font-semibold">Usuario</label>
				<input type="text" id="usuario" name="usuario"
					class="mt-1 w-full p-3 rounded bg-gray-700 text-white border border-gray-600 focus:ring focus:ring-blue-500 focus:border-blue-500"
					placeholder="Ingresa tu usuario" required>
			</div>

			<div>
				<label for="contrasenia" class="block text-sm font-semibold">Contraseña</label>
				<div class="relative">
					<input type="password" id="contrasenia" name="contrasenia"
						class="mt-1 w-full p-3 rounded bg-gray-700 text-white border border-gray-600 focus:ring focus:ring-blue-500 focus:border-blue-500"
						placeholder="Ingresa tu contraseña" required> <img
						id="eye-icon"
						src="https://cdn-icons-png.flaticon.com/128/10812/10812267.png"
						alt="Toggle password visibility"
						class="absolute right-3 top-3 eye-icon"
						onclick="togglePasswordVisibility()" width="20" height="20" />
				</div>
			</div>

			<button type="submit"
				class="w-full bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-200 ease-in-out transform hover:scale-105"
				name="btnAceptar" id="btnAceptar">Iniciar sesión</button>
		</form>

		
	</div>
	

	

</body>
</html>