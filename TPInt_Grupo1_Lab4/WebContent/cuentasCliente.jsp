<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Movimiento" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Cuentas</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    body {
        background-color: #f8f9fa;
    }
    .card {
        margin: 10px;
        transition: 0.3s;
    }
    .card:hover {
        transform: scale(1.05);
    }
    .table th {
        background-color: #343a40 !important;
        color: white;
    }
    .container-custom {
        margin-top: 20px;
        max-width: 900px;
    }
    .table-container {
        margin-top: 20px;
    }
</style>
</head>
<body>

<div class="container container-custom">
    <h2 class="text-center mb-4"> Mis Cuentas</h2>

    <div class="row">
        <% 
            List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
            if (cuentas != null && !cuentas.isEmpty()) {
                for (Cuenta cuenta : cuentas) {
        %>
                <div class="col-md-6">
                    <form action="servletMirarMisCuentasCliente" method="post">
                        <div class="card shadow">
                            <div class="card-body text-center">
                                <h5 class="card-title"> Cuenta Nro <%= cuenta.getNroCuenta() %></h5>
                                <h6 class="card-title">$<%= cuenta.getSaldo() %> </h3>
                                <button name="btnCuenta" type="submit" value="<%= cuenta.getNroCuenta() %>" class="btn btn-primary">
                                    Ver Movimientos
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
        <% 
                }
            } else { 
        %>
            <div class="col-12">
                <div class="alert alert-danger text-center" role="alert">
                    No hay cuentas activas por el momento. Solicite abrir una cuenta al administrador e intentelo nuevamente.
                </div>
            </div>
        <% } %>
    </div>

    <% if (request.getAttribute("movimientos") != null) { %>
    <div class="table-container">
        <h3 class="text-center mt-4"> Movimientos</h3>
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered text-center">
                <thead class="thead-dark">
                    <tr>
                        <th>Id Movimiento</th>
                        <th>Fecha</th>
                        <th>Tipo Movimiento</th>
                        <th>Importe</th>
                        <th>Detalle</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
                        for (Movimiento mov : movimientos) { 
                    %>
                    <tr>
                        <td><%= mov.getId() %></td>
                        <td><%= mov.getCreateDate() %></td>
                        <td><%= mov.getTipoMovimiento().getDescripcion() %></td>
                        <td>$<%= mov.getImporte() %></td>
                        <td><%= mov.getDetalle() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.bootstrap4.js"></script>
<script>
    $(document).ready(function() {
        $('.table').DataTable();
    });
</script>

</body>
</html>
