<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Pais" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Provincia" %>
<%@ page import="entidad.Localidad" %>
<%@ page import="java.util.List" %> 
<%@ page import="negocio.PaisNeg" %>
<%@ page import="negocio.ClienteNeg" %>
<%@ page import="negocio.LocalidadNeg" %>
<%@ page import="negocio.ProvinciasNeg" %>
<%@ page import="negocioImpl.PaisNegImpl" %>
<%@ page import="negocioImpl.ClienteNegImpl" %>
<%@ page import="negocioImpl.LocalidadNegImpl" %>
<%@ page import="negocioImpl.ProvinciaNegImpl" %>

<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Cliente</title>
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
        <h2 class="text-primary centered-header">Modificar Cliente</h2>
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

    <!-- Mostrar mensaje de error -->
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
        <%
            // Obtener el ID del cliente desde la solicitud
            int idCliente = Integer.parseInt(request.getParameter("id"));
            ClienteNeg clienteNeg = new ClienteNegImpl();
            
            Cliente cliente = clienteNeg.ListarClientes().stream()
                    .filter(c -> c.getId() == idCliente)
                    .findFirst()
                    .orElse(null);

            if (cliente != null) {
        %>
        <form action="servletModificarCliente" method="post">
            <input type="hidden" name="id" value="<%= cliente.getId() %>"> <!-- Campo oculto para el ID -->

            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="dni" class="form-label">DNI:</label>
                    <input type="text" id="dni" name="dni" class="form-control" placeholder="Ingrese el DNI"   
                              value="<%= cliente.getDni() != null ? cliente.getDni() : "" %>" required>  
                </div>
                <div class="col-12 col-md-6">
                    <label for="cuil" class="form-label">Cuil:</label>
                    <input type="text" id="cuil" name="cuil" class="form-control" placeholder="Ingrese el Cuil"   
                             value="<%= cliente.getCuil() != null ? cliente.getCuil() : "" %>" required>  
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese el Nombre"   
                          value="<%= cliente.getNombre() != null ? cliente.getNombre() : "" %>" required>  
                </div>
                <div class="col-12 col-md-6">
                    <label for="apellido" class="form-label">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Ingrese el Apellido"   
                          value="<%= cliente.getApellido() != null ? cliente.getApellido() : "" %>" required>  
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-12 col-md-6">
                    <label for="sexo" class="form-label">Sexo</label>
                 <select id="sexo" name="sexo" class="form-control">  
                        <option value="masculino" <%= "masculino".equals(cliente.getSexo()) ? "selected" : "" %>>Masculino</option>  
                        <option value="femenino" <%= "femenino".equals(cliente.getSexo()) ? "selected" : "" %>>Femenino</option>  
                    </select>   
                </div>
                <div class="col-12 col-md-6">
                    <label for="pais">Pais:</label>
                    <select class="form-control" id="pais" name="pais" required>
                        <option value="">Seleccionar</option>
                        <%
                            PaisNegImpl paisNeg = new PaisNegImpl();
                            List<Pais> paises = paisNeg.ListarPaises();
                            if (paises != null && !paises.isEmpty()) {
                                for (Pais pais : paises) {
                        %>
                            <option value="<%= pais.getId() %>" <%= pais.getId() == cliente.getPaisNacimiento().getId() ? "selected" : "" %>>
                                <%= pais.getNombre() %>
                            </option>
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

            <!-- Completa con el resto del formulario si es necesario -->
            <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
        <% } else { %>
            <p class="text-danger">Cliente no encontrado.</p>
        <% } %>
    </div>
</div>





<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
