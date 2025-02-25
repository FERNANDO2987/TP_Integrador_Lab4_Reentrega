<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>  
<%@ page import="java.util.List" %>
<%@ page import="entidad.Usuario" %>
<%@ page import="entidadDTO.CuentaDTO" %>
<%@ page import="entidadDTO.PrestamoDTO" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<CuentaDTO> datosClientes = (List<CuentaDTO>) request.getAttribute("datosClientes");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Pagar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

    <div class="max-w-2xl w-full bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold text-gray-800 mb-4">Pagar Prestamo</h2>

        <% if (error != null) { %>
            <p class="text-red-500"><%= error %></p>
        <% } %>

        <% if (datosClientes != null && !datosClientes.isEmpty()) { %>
            <% for (CuentaDTO cuenta : datosClientes) { %>
                <% for (PrestamoDTO prestamo : cuenta.getPrestamos()) { %>

                    <div class="bg-gray-50 p-4 rounded-lg mb-4">
                        <div class="flex justify-between">
                            <h3 class="font-semibold text-gray-700">Prestamo personal</h3>
                            <p class="text-gray-600 font-semibold">Monto total: $ <%= prestamo.getImporte() %></p>
                        </div>

                        <div class="mt-2 text-gray-700">
                           
                              <p class="font-bold font-semibold">Cuota a pagar: $ <%= prestamo.getValorCuotas() %></p>
                               <p class="font-bold font-semibold">Deuda pendiente: $ <%= prestamo.getImporte() %></p>
                          
                        </div>

                        <div class="mt-2 text-sm text-red-500 font-semibold">
                            Vencimiento: <%= prestamo.getFechaAlta() %> <span class="font-bold">[VENCIDO]</span>
                        </div>
                    </div>

               
                 <!-- Selección de cuenta -->
                  <div class="mt-4">
                     <label class="block text-gray-700 font-medium mb-2">Selecciona una cuenta</label>
                    <select class="w-full p-2 border border-gray-300 rounded">
                   <option value="">Selecciona una cuenta</option>
                
                   <option value="<%= cuenta.getNroCuenta() %>">
                    Nº <%= cuenta.getNroCuenta() %> - Saldo: $<%= cuenta.getSaldo() %>
                  </option>
              
                 </select>
                 </div>


                    <!-- Botón Confirmar Pago -->
                    <div class="mt-6">
                        <button class="w-full bg-red-600 text-white py-2 rounded-lg font-semibold hover:bg-red-700 transition">
                            Confirmar Pago
                        </button>
                    </div>

                <% } %>
            <% } %>
        <% } else { %>
            <p class="text-gray-600">No hay prestamos disponibles.</p>
        <% } %>
    </div>

</body>
</html>
