<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.Prestamo" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
    String error = (String) request.getAttribute("error");
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas"); 
    

%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>Pagar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

    <div class="max-w-2xl w-full bg-white p-6 rounded-lg shadow-md">
        <h2 class="text-xl font-semibold text-gray-800 mb-4">Pagar Préstamo</h2>

        <% if (error != null) { %>
            <p class="text-red-500"><%= error %></p>
        <% } %>

        <% if (prestamo != null) { %>
            <div class="bg-gray-50 p-4 rounded-lg mb-4">
                <div class="flex justify-between">
                    <h3 class="font-semibold text-gray-700">Préstamo Personal</h3>
                    <p class="text-gray-600 font-semibold">Monto total: $ <%= prestamo.getImporte() %></p>
                </div>

                <div class="mt-2 text-gray-700">
                    <p class="font-bold">Cuota a pagar: $ <%= prestamo.getCuotas() %></p>
                    <p class="font-bold">Deuda pendiente: $ <%= prestamo.getImporte() %></p>
                </div>

                <div class="mt-2 text-sm text-red-500 font-semibold">
                    Vencimiento: <%= prestamo.getFechaAlta() %> <span class="font-bold">[VENCIDO]</span>
                </div>
            </div>

            <!-- Selección de cuenta -->
            <form action="ProcesarPagoServlet" method="post">
                <input type="hidden" name="idPrestamo" value="<%= prestamo.getId() %>">

                <div class="mt-4">
                    <label class="block text-gray-700 font-medium mb-2">Selecciona una cuenta</label>
                    <select name="nroCuenta" class="w-full p-2 border border-gray-300 rounded">
                        <option value="">Selecciona una cuenta</option>

                        <% if (cuentas != null && !cuentas.isEmpty()) { %>
                            <% for (Cuenta cuenta : cuentas) { %>
                                <option value="<%= cuenta.getNroCuenta() %>">
                                    Nº <%= cuenta.getNroCuenta() %> - Saldo: $<%= cuenta.getSaldo() %>
                                </option>
                            <% } %>
                        <% } else { %>
                            <option value="">No tienes cuentas disponibles</option>
                        <% } %>
                    </select>
                </div>

                <div class="mt-6">
                    <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg font-semibold hover:bg-red-700 transition">
                        Confirmar Pago
                    </button>
                </div>
            </form>

        <% } else { %>
            <p class="text-gray-600">No se encontraron datos del préstamo.</p>
        <% } %>
    </div>

</body>
</html>
