<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="entidad.Pais" %>
<%@ page import="java.util.List" %> 
<%@ page import="negocio.PaisNeg" %> 
<%@ page import="negocioImpl.PaisNegImpl" %> 
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
            max-width: 500px;
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

    <!-- Mostrar mensajes de error o éxito -->
    <div>
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="alert alert-danger" role="alert">
                <%= error %>
            </div>
        <% 
            }
        %>
    </div>

    <!-- Contenedor del formulario -->
    <div class="form-container">
        <form action="servletAgregarCliente" method="post">
           
            <div class="form-group">
                <label for="dni" class="form-label">DNI:</label>
                <input type="text" id="dni" name="dni" class="form-control" placeholder="Ingrese el DNI" required>
            </div>

            
            <div class="form-group">
                <label for="cuil" class="form-label">Cuil:</label>
                <input type="text" id="cuil" name="cuil" class="form-control" placeholder="Ingrese el Cuil" required>
            </div>
            
             <div class="form-group">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese el Nombre" required>
            </div>
            
              <div class="form-group">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Ingrese el Apellido" required>
            </div>
            
               <div class="form-group">
                <label for="sexo" class="form-label">Sexo</label>
                <select id="sexo" name="sexo" class="form-control">
                    <option value="masculino">Masculino</option>
                    <option value="femenino">Femenino</option>
                </select>
            </div>
            
                <div class="form-group">
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

            
             <div class="form-group">
                <label for="fechaNacimiento" class="form-label">Fecha Nacimiento:</label>
                <input type="text" id="fechaNacimiento" name="fechaNacimiento" class="form-control" placeholder="Ingrese la fecha Nacimeinto" required>
            </div>
            
              <div class="form-group">
                <label for="direccion" class="form-label">Direccion:</label>
                <input type="text" id="direccion" name="direccion" class="form-control" placeholder="Ingrese la direccion" required>
            </div>
            
              <div class="form-group">
                <label for="localidad" class="form-label">Localidad:</label>
                <input type="text" id="localidad" name="localidad" class="form-control" placeholder="Ingrese la Localidad" required>
            </div>
            
                <div class="form-group">
                <label for="provincia" class="form-label">Provincia:</label>
                <input type="text" id="provincia" name="provincia" class="form-control" placeholder="Ingrese la Provincia" required>
            </div>
            
              <div class="form-group">
                <label for="email" class="form-label">Email:</label>
                <input type="text" id="email" name="email" class="form-control" placeholder="Ingrese el email" required>
            </div>
            
            <div class="form-group">
                <label for="telefono" class="form-label">Telefono:</label>
                <input type="text" id="telefono" name="telefono" class="form-control" placeholder="Ingrese el telefono" required>
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

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
