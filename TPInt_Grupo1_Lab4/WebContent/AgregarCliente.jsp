<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Pais" %>
<%@ page import="entidad.Provincia" %>
<%@ page import="entidad.Localidad" %>
<%@ page import="java.util.List" %> 
<%@ page import="negocio.PaisNeg" %>
<%@ page import="negocio.LocalidadNeg" %>
<%@ page import="negocio.ProvinciasNeg" %>
<%@ page import="negocioImpl.PaisNegImpl" %>
<%@ page import="negocioImpl.LocalidadNegImpl" %>
<%@ page import="negocioImpl.ProvinciaNegImpl" %>
<%@ page import="entidad.Usuario" %>
<%
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
%>



<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Cliente</title>
    <!-- Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
   <style>
        .centered-header {
            text-align: center;
        }
        .form-container {
            max-width: 900px;
            margin: 0 auto;
        }
        .form-label {
            font-weight: bold;
        }
       .alert {  
    font-size: 1.2rem;  
    padding: 20px;  
    border-radius: 8px;  
    display: flex;  
    align-items: center;  
    margin-bottom: 16px;  
}  
.alert-success {  
    background-color: #28a745; /* Color verde */  
    color: white;  
}  
.alert-error {  
    background-color: #dc3545; /* Color rojo */  
    color: white;  
}
  input:invalid {
        border-color: red;
    }
    
    /* Si prefieres un borde rojo sin mostrar el mensaje de error */
    input:required:invalid {
        border-color: red;
    }

    </style>
</head>
<body class="bg-gray-100">
<div class="container mx-auto mt-10">
    <!-- Encabezado centrado -->
    <div class="text-center mb-6">
        <h2 class="text-2xl font-semibold text-blue-600">Agregar Cliente</h2>
    </div>

      <!-- Mostrar mensaje de éxito -->
  <%  
    String mensajeExito = (String) request.getAttribute("mensajeExito");  
    if (mensajeExito != null) {  
%>  
    <div id="successMessage" class="alert alert-success mb-4">  
        <i class="fas fa-check-circle"></i> <%= mensajeExito %>  
    </div>  
<%  
    }  
%>   

<%  
    String mensajeError = (String) request.getAttribute("mensajeError");  
    if (mensajeError != null) {  
%>  
    <div id="errorMessage" class="alert alert-error mb-4">  
        <i class="fas fa-exclamation-circle"></i> <%= mensajeError %>  
    </div>  
<%  
    }  
%>

    <!-- Contenedor del formulario -->
    <div class="form-container bg-white p-6 shadow-md rounded-lg">
        <form action="servletAgregarCliente" method="post">
            <!-- DNI y CUIL -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="dni" class="form-label text-lg">DNI:</label>
                    <input type="text" id="dni" name="dni" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese el DNI"   
                           value="<%= request.getAttribute("dni") != null ? request.getAttribute("dni") : "" %>" required>  
                </div>
                <div>
                    <label for="cuil" class="form-label text-lg">Cuil:</label>
                    <input type="text" id="cuil" name="cuil" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese el Cuil"   
                           value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>" required>  
                </div>
            </div>

            <!-- Nombre y Apellido -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="nombre" class="form-label text-lg">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese el Nombre"   
                           value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>  
                </div>
                <div>
                    <label for="apellido" class="form-label text-lg">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese el Apellido"   
                           value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>" required>
                </div>
            </div>

            <!-- Sexo y Pais -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="sexo" class="form-label text-lg">Sexo</label>
                    <select id="sexo" name="sexo" class="form-input mt-2 p-2 border border-gray-300 rounded w-full">  
                        <option value="masculino" <%= "masculino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Masculino</option>  
                        <option value="femenino" <%= "femenino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Femenino</option>  
                    </select>  
                </div>
                <div>
                    <label for="pais" class="form-label text-lg">Pais:</label>
                    <select class="form-input mt-2 p-2 border border-gray-300 rounded w-full" id="pais" name="pais" required>
                        <option value="">Seleccionar</option>
                        <%
                            PaisNegImpl paisNeg = new PaisNegImpl();
                            List<Pais> paises = paisNeg.ListarPaises();
                            if (paises != null && !paises.isEmpty()) {
                                for (Pais pais : paises) {
                        %>
                                    <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                        <%
                                }
                            } else {
                        %>
                                    <option value="">No hay países disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>

            <!-- Fecha de nacimiento y Direccion -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="fechaNacimiento" class="form-label text-lg">Fecha Nacimiento:</label>
                    <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" required   
                           value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : "" %>">  
                </div>
                <div>
                    <label for="direccion" class="form-label text-lg">Direccion:</label>
                    <input type="text" id="direccion" name="direccion" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese la direccion"   
                           value="<%= request.getAttribute("direccion") != null ? request.getAttribute("direccion") : "" %>" required>  
                </div>
            </div>

            <!-- Localidad y Provincia -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="localidad" class="form-label text-lg">Localidad:</label>
                    <select class="form-input mt-2 p-2 border border-gray-300 rounded w-full" id="localidad" name="localidad" required>
                        <option value="">Seleccionar</option>
                        <%
                            LocalidadNegImpl localidadNeg = new LocalidadNegImpl();
                            List<Localidad> localidades = localidadNeg.ListarLocalidades();
                            if (localidades != null && !localidades.isEmpty()) {
                                for (Localidad localidad : localidades) {
                        %>
                                    <option value="<%= localidad.getId() %>"><%= localidad.getNombre() %></option>
                        <%
                                }
                            } else {
                        %>
                                    <option value="">No hay localidades disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div>
                    <label for="provincia" class="form-label text-lg">Provincia:</label>
                    <select class="form-input mt-2 p-2 border border-gray-300 rounded w-full" id="provincia" name="provincia" required>
                        <option value="">Seleccionar</option>
                        <%
                            ProvinciaNegImpl provinciaNeg = new ProvinciaNegImpl();
                            List<Provincia> provincias = provinciaNeg.ListarProvincias();
                            if (provincias != null && !provincias.isEmpty()) {
                                for (Provincia provincia : provincias) {
                        %>
                                    <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                        <%
                                }
                            } else {
                        %>
                                    <option value="">No hay provincias disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>
                
                
            </div>
            
               <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                    <label for="email" class="form-label text-lg">Email:</label>
                    <input type="email" id="email" name="email" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" required   
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">  
                </div>
                <div>
                    <label for="telefono" class="form-label text-lg">Telefono:</label>
                    <input type="text" id="telefono" name="telefono" class="form-input mt-2 p-2 border border-gray-300 rounded w-full" placeholder="Ingrese la direccion"   
                           value="<%= request.getAttribute("telefono") != null ? request.getAttribute("telefono") : "" %>" required>  
                </div>
            </div>

         
            
            <div class="text-center mt-6">
    <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-lg shadow-md hover:bg-blue-700"
        onclick="return confirm('¿Está seguro de que desea agregar un cliente?')">
        Agregar Cliente
    </button>
</div>
        </form>
    </div>
</div>

<script>  
    window.onload = function() {  
        // Esperar a que el DOM esté completamente cargado  
        setTimeout(function() {  
            var successMessage = document.getElementById('successMessage');  
            var errorMessage = document.getElementById('errorMessage');  
            if (successMessage) {  
                successMessage.style.display = 'none'; // Ocultar mensaje de éxito  
            }  
            if (errorMessage) {  
                errorMessage.style.display = 'none'; // Ocultar mensaje de error  
            }  
        }, 5000); // 5000 milisegundos = 5 segundos  
    };  
</script>
</body>
</html>
