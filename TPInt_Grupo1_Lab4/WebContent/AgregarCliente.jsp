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


<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Cliente</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    </style>
</head>
<body>
<div class="container mt-5">
    <!-- Encabezado centrado -->
    <div class="row justify-content-center mb-4">
        <h2 class="text-primary centered-header">Agregar Cliente</h2>
    </div>

    <!-- Mostrar mensaje de éxito -->  
        <%  
            String mensajeExito = (String) request.getAttribute("mensajeExito");  
            if (mensajeExito != null) {  
        %>  
            <div id="successMessage" class="alert alert-success">  
                <%= mensajeExito %>  
            </div>  
        <%  
            }  
        %> 
        
        <%  
    String mensajeError = (String) request.getAttribute("mensajeError");  
    if (mensajeError != null) {  
%>  
    <div id="errorMessage" class="alert alert-danger">  
        <%= mensajeError %>  
    </div>  
<%  
    }  
%>  
        
    <!-- Contenedor del formulario -->
    <div class="form-container">
        <hr>
        <form action="servletAgregarCliente" method="post">
      
            

            
            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="dni" class="form-label">DNI:</label>
                   
                    <input type="text" id="dni" name="dni" class="form-control" placeholder="Ingrese el DNI"   
                           value="<%= request.getAttribute("dni") != null ? request.getAttribute("dni") : "" %>" required>  
                </div>
                <div class="col-12 col-md-6">
                    <label for="cuil" class="form-label">Cuil:</label>
                    <input type="text" id="cuil" name="cuil" class="form-control" placeholder="Ingrese el Cuil"   
                           value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>" required>  
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese el Nombre"   
                           value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>  
                </div>
                <div class="col-12 col-md-6">
                    <label for="apellido" class="form-label">Apellido:</label>
                       <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Ingrese el Apellido"   
                           value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>" required>
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="sexo" class="form-label">Sexo</label>
                     <select id="sexo" name="sexo" class="form-control">  
                        <option value="masculino" <%= "masculino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Masculino</option>  
                        <option value="femenino" <%= "femenino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Femenino</option>  
                    </select>  
                </div>
                <div class="col-12 col-md-6">
                    <label for="pais">Pais:</label>
                    <select class="form-control" id="pais" name="pais" required>
                        <option value="">Seleccionar</option>
                        <%
                            // Obtener la lista de países desde la base de datos
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

            <div class="row mb-4">
              <div class="col-12 col-md-6">
    <label for="fechaNacimiento" class="form-label">Fecha Nacimiento:</label>
   <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required   
                           value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : "" %>">  
</div>

                <div class="col-12 col-md-6">  
                    <label for="direccion" class="form-label">Direccion:</label>  
                    <input type="text" id="direccion" name="direccion" class="form-control" placeholder="Ingrese la direccion"   
                           value="<%= request.getAttribute("direccion") != null ? request.getAttribute("direccion") : "" %>" required>  
                </div> 
            </div>

            <div class="row mb-4">
             
                
                   <div class="col-12 col-md-6">
                    <label for="localidad">Localidad:</label>
                    <select class="form-control" id="localidad" name="localidad" required>
                        <option value="">Seleccionar</option>
                        <%
                            // Obtener la lista de países desde la base de datos
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
             
                
                
                  <div class="col-12 col-md-6">
                    <label for="provincia">Provincia:</label>
                    <select class="form-control" id="provincia" name="provincia" required>
                        <option value="">Seleccionar</option>
                        <%
                            // Obtener la lista de países desde la base de datos
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
                                    <option value="">No hay provincia disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>
                
                
            </div>

            <div class="row mb-4">
                    <div class="col-12 col-md-6">  
                    <label for="email" class="form-label">Email:</label>  
                    <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese el email"   
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>  
                </div> 
                  <div class="col-12 col-md-6">  
                    <label for="telefono" class="form-label">Telefono:</label>  
                    <input type="text" id="telefono" name="telefono" class="form-control" placeholder="Ingrese el telefono"   
                           value="<%= request.getAttribute("telefono") != null ? request.getAttribute("telefono") : "" %>" required>  
                </div> 
            </div>

            <!-- Botones -->
            <div class="form-group text-center">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save"></i> Guardar
                </button>
                <a href="ListaUsuarios.jsp" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</div>

  <script>  
        // Llamar a la función mostrarMensaje si se ha definido el mensaje exitoso o de error  
        <% if(request.getAttribute("mensajeExito") != null) { %>  
            mostrarMensaje("successMessage");  
        <% } else if(request.getAttribute("mensajeError") != null) { %>  
            mostrarMensaje("errorMessage");  
        <% } %>  
        
        function ocultarMensaje() {  
            var mensaje = document.getElementById("successMessage");  
            if (mensaje) {  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 9000); 
            }  

            var errorMensaje = document.getElementById("errorMessage");
            if (errorMensaje) {
                setTimeout(function() {
                    errorMensaje.style.display = "none";
                }, 9000);
            }
        } 

        // Función para mostrar el mensaje y luego ocultarlo  
        function mostrarMensaje(tipo) {  
            var mensaje = document.getElementById(tipo);  
            if (mensaje) {  
                mensaje.style.display = "block"; // Mostrar el mensaje  
                // Ocultar el mensaje después de 3 segundos (3000 milisegundos)  
                setTimeout(function() {  
                    mensaje.style.display = "none";  
                }, 9000);  
            }  
        } 
        
    </script>  



<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
