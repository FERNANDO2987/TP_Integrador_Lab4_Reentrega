<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Transferencia</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<div class="container mt-5">
        <div class="card shadow-lg p-4">
            <h2 class="text-center mb-4">Transferencia Bancaria</h2>
            <form>
                <div class="mb-3">
                    <label for="cuentaOrigen" class="form-label">Cuenta Origen</label>
                    <select class="form-select" id="cuentaOrigen" required>
                        <option selected disabled>Seleccione una cuenta</option>
                        <option value="1">Cuenta 1234 - Banco A</option>
                        <option value="2">Cuenta 5678 - Banco B</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cuentaDestino" class="form-label">Cuenta Destino</label>
                    <input type="text" class="form-control" id="cuentaDestino" placeholder="Ingrese el número de cuenta" required>
                </div>
                <div class="mb-3">
                    <label for="monto" class="form-label">Monto</label>
                    <input type="number" class="form-control" id="monto" placeholder="Ingrese el monto" min="1" required>
                </div>
                <div class="mb-3">
                    <label for="detalle" class="form-label">Detalle</label>
                    <input type="text" class="form-control" id="detalle" placeholder="Ingrese un detalle opcional">
                </div>
                <button type="submit" class="btn btn-primary w-100">Realizar Transferencia</button>
            </form>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>