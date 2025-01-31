<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>  
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Pais" %> 
<%@ page import="negocio.ClienteNeg" %>
<%@ page import="negocioImpl.ClienteNegImpl" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Detalles</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <%
    int idCliente = Integer.parseInt(request.getParameter("id"));
    ClienteNeg clienteNeg = new ClienteNegImpl();
    
    Cliente cliente = clienteNeg.ListarClientes().stream()
            .filter(c -> c.getId() == idCliente)
            .findFirst()
            .orElse(null);

    if (cliente != null) {
	%>
	<form action="servletModificarCliente" method="get">
		<div class="container">
			<div class="row" style="padding: 2rem;">
	        	<h2 class= "text-secondary text-uppercase"><%= cliente.getNombre() %> <%= cliente.getApellido() %> <%= cliente.getCuil() %></h2>	
	        </div>
	        <div class="row" style="padding: 0rem 1rem;">
	            <div class="col-md-6">
	                <div class="card" style="height: 25rem;">
	                    <div class="card-header">
	                        <strong>Datos Personales</strong>
	                    </div>
	                    <div class="card-body">
	                    	<div class="row"> 
	                    		<div class="col">
	                    			<p><strong>ID:</strong></p>
			                        <p><strong>Nombre:</strong></p>
			                        <p><strong>Apellido:</strong></p>
			                        <p><strong>DNI:</strong></p>
			                        <p><strong>CUIL:</strong></p>
			                        <p><strong>Sexo:</strong></p>
			                        <p><strong>Fecha de nacimiento:</strong></p>
			                        <p><strong>Nacionalidad:</strong></p>
	                    		</div>
	                    		<div class="col">
	                    			<p><%= cliente.getId() %></p>
			                        <p><%= cliente.getNombre() %></p>
			                        <p><%= cliente.getApellido() %></p>
			                        <p><%= cliente.getDni() %></p>
			                        <p><%= cliente.getCuil() %></p>
			                        <p><%= cliente.getSexo() %></p>
			                        <p><%= cliente.getFechaNacimiento() %></p>
			                        <p><%= cliente.getPaisNacimiento().getNombre() %></p>
	                    		</div>
	                    	</div>
	                        
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6">
	            	<div class="card mb-3" style="height: 13rem;">
	                    <div class="card-header">
	                        <strong>Datos de Domicilio</strong>
	                    </div>
	                    <div class="card-body">
	                    	<div class="row">
	                    		<div class="col">
	                    			<p><strong>Dirección:</strong></p>
	                        		<p><strong>Localidad:</strong></p>
	                        		<p><strong>Provincia:</strong></p>  
	                    		</div>
	                    		<div class="col">
	                    			<p><%= cliente.getDireccion() %></p>
	                        		<p><%= cliente.getLocalidad().getNombre() %></p>  
	                        		<p><%= cliente.getProvincia().getNombre() %></p>
	                    		</div>
	                    	</div>                             
	                    </div>
	                </div>
	                <div class="card" style="height: 11rem;">
	                    <div class="card-header">
	                        <strong>Datos de Contacto</strong>
	                    </div>
	                    <div class="card-body">
	                    	<div class="row">
	                    		<div class="col">
	                    			<p><strong>Correo:</strong></p>
	                        		<p><strong>Teléfono:</strong></p>
	                    		</div>
	                    		<div class="col">
	                    			<p><%= cliente.getCorreo() %></p>
	                        		<p><%= cliente.getTelefono() %></p>
	                    		</div>
	                    	</div>                                          
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="row" style="padding: 2rem;">
	        	<div class="card w-100">
	                <div class="card-header">
	                    <strong>Datos de Usuario</strong>
	                </div>
	                <div class="card-body">
	                	<div class="row">
	                    		<div class="col">
	                    			<div class="flex-grow-1 px-2">
	    								<p><strong>Usuario:</strong> Usuario123</p>
	  								</div>
	                    		</div>
	                    			<div class="col">
	                    				<div class="flex-grow-1 px-2">
	    							<p><strong>Contraseña:</strong> *************</p>
	  								</div>
	                    		</div>
	                    	</div> 
					</div>
	            </div>
	        </div>
	        <div class="row d-flex justify-content-between" style="padding: 2rem;">
	  			<div class="d-flex">
	    			<a href="servletListarClientes" class="btn btn-danger">Volver</a>
	    			<a href="DetalleCliente.jsp?id=<%= cliente.getId() %>" class="btn btn-primary mx-3">Modificar Detalles</a>
	  			</div>
	  			<a href="#" class="btn btn-secondary">Ver Cuenta</a>
			</div>
	    </div>
	</form>
	<% } else { %>
        <p>Cliente no encontrado.</p>
	<% } %>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>