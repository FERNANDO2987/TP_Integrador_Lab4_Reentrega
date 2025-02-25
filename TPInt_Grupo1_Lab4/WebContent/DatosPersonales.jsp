<%@ page contentType="text/html; charset=UTF-8" language="java" %>  
<%@ page import="entidad.Usuario" %>  
<%@ page import="java.time.format.DateTimeFormatter" %>  
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
    <title>Perfil - <%= nombreUsuario %></title>  
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">  
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">  
    <link rel="stylesheet" href="path/to/your/styles.css"> <!-- Personaliza esta línea -->  
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script src="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.js"></script>  
</head>  
<body class="bg-blue-50 flex justify-center items-center min-h-screen">  
    <div class="bg-white shadow-lg rounded-lg p-8 w-full max-w-3xl mx-auto">  

         <div class="text-center mb-4">
        <h2 class="text-2xl text-blue-600">Datos Personales</h2>
    </div>

        <div class="bg-gray-100 p-6 rounded-lg shadow-inner">  
            <h3 class="text-2xl text-blue-600">Información Personal</h3>  
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">  

                <div>  
                    <label class="block text-gray-700 font-medium">Nombre Completo</label>  
                    <input type="text" value="<%= usuario.getCliente().getNombre() + " " + usuario.getCliente().getApellido() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">DNI</label>  
                    <input type="text" value="<%= usuario.getCliente().getDni() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">CUIL</label>  
                    <input type="text" value="<%= usuario.getCliente().getCuil() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Sexo</label>  
                    <input type="text" value="<%= usuario.getCliente().getSexo() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Fecha de Nacimiento</label>  
                    <input type="text" value="<%= usuario.getCliente().getFechaNacimiento().format(formatter) %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Nacionalidad</label>  
                    <input type="text" value="<%= usuario.getCliente().getPaisNacimiento().getNombre() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Dirección</label>  
                    <input type="text" value="<%= usuario.getCliente().getDireccion() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Localidad</label>  
                    <input type="text" value="<%= usuario.getCliente().getLocalidad().getNombre() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Provincia</label>  
                    <input type="text" value="<%= usuario.getCliente().getProvincia().getNombre() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Correo</label>  
                    <input type="text" value="<%= usuario.getCliente().getCorreo() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

                <div>  
                    <label class="block text-gray-700 font-medium">Teléfono</label>  
                    <input type="text" value="<%= usuario.getCliente().getTelefono() %>"   
                           class="w-full bg-gray-200 text-gray-700 p-2 rounded-md" disabled>  
                </div>  

            </div>  
        </div>  
    </div>  
</body>  
</html>