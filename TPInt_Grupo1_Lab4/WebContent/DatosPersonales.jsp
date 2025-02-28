
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Perfil</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-blue-50 flex justify-center items-center min-h-screen">
	<div class="bg-white shadow-lg rounded-lg p-6 w-full max-w-4xl mx-auto">

		<h2 class="text-xl font-semibold mb-4">Mi Perfil</h2>

		<div class="bg-gray-100 p-4 rounded-lg">
			<h3 class="text-lg font-semibold mb-3">Información Personal</h3>
			<div class="grid grid-cols-2 gap-4">

				<div>
					<label class="block text-gray-700">Nombre Completo</label> <input
						type="text"
						value="<%=usuario.getCliente().getNombre() + " " + usuario.getCliente().getApellido()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">DNI</label> <input type="text"
						value="<%=usuario.getCliente().getDni()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">CUIL</label> <input type="text"
						value="<%=usuario.getCliente().getCuil()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Sexo</label> <input type="text"
						value="<%=usuario.getCliente().getSexo()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Fecha de Nacimiento</label> <input
						type="text"
						value="<%=usuario.getCliente().getFechaNacimiento().format(formatter)%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Nacionalidad</label> <input
						type="text"
						value="<%=usuario.getCliente().getPaisNacimiento().getNombre()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Dirección</label> <input
						type="text" value="<%=usuario.getCliente().getDireccion()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Localidad</label> <input
						type="text"
						value="<%=usuario.getCliente().getLocalidad().getNombre()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Provincia</label> <input
						type="text"
						value="<%=usuario.getCliente().getProvincia().getNombre()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Correo</label> <input
						type="text" value="<%=usuario.getCliente().getCorreo()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

				<div>
					<label class="block text-gray-700">Teléfono</label> <input
						type="text" value="<%=usuario.getCliente().getTelefono()%>"
						class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>
				</div>

			</div>
		</div>
	</div>

</body>
</html>