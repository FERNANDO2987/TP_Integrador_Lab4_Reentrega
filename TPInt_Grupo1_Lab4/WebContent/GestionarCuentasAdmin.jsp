<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.TipoCuenta"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.Usuario"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestion Cuentas</title>
<!-- Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
.card {
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	transition: transform 0.2s;
}

.card:hover {
	transform: scale(1.02);
}

.form-container {
	background: #f8f9fa;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
}

.card-text {
	font-size: 1.2rem;
	font-weight: bold;
	color: #333;
}

.btn {
	border-radius: 5px;
	padding: 8px 12px;
	font-weight: bold;
}
</style>
</head>
<body>





	<div class="p-2">
		<div class="row justify-content-around">
			<%
				List<TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
				List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
				if (cuentas != null && cuentas.size() > 0) {
					for (Cuenta cuenta : cuentas) {
			%>
			<form action="servletGestionarCuentas" method="post">
				<input type="hidden" name="InputIdCliente"
					value="<%=request.getAttribute("idCliente")%>"> <input
					type="hidden" name="idCuenta" value="<%=cuenta.getNroCuenta()%>">
				<div class="col p-2">
					<div class="card mb-3">
						<div class="card-body">
							<h5 class="card-title">
								CBU:
								<%=cuenta.getCbu()%></h5>
							<select name="selectCuenta" class="form-control">
								<%
									for (TipoCuenta tipo : tiposCuenta) {
								%>
								<option
									<%if (tipo.getId() == cuenta.getTipoCuenta().getId()) {%>
									selected <%}%> value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
								<%
									}
								%>
							</select>
							<h6 class="card-text">
								$<%=cuenta.getSaldo()%></h6>
							<button type="button" class="btn btn-success btn-sm"
								data-toggle="modal"
								data-target="#modalConfirmarModificacion_<%=cuenta.getNroCuenta()%>">Modificar</button>
							<input type="submit" class="btn btn-info btn-sm"
								value="Movimientos" name="btnMovimientos">
							<button type="button" class="btn btn-danger btn-sm"
								data-toggle="modal"
								data-target="#modalConfirmarEliminacion_<%=cuenta.getNroCuenta()%>">Eliminar</button>
						</div>
					</div>
				</div>

				<!-- MODAL CONFIRMAR MODIFICACI�N -->
				<div class="modal fade"
					id="modalConfirmarModificacion_<%=cuenta.getNroCuenta()%>"
					tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Confirmar Modificaci�n</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Cerrar">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">�Est� seguro de que desea modificar
								esta cuenta?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
								<!-- Este bot�n ahora env�a el formulario -->
								<input type="submit" class="btn btn-primary" name="btnModificar"
									value="Confirmar">
							</div>
						</div>
					</div>
				</div>
				<!-- MODAL CONFIRMAR ELIMINACI�N -->
				<div class="modal fade"
					id="modalConfirmarEliminacion_<%=cuenta.getNroCuenta()%>"
					tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Confirmar Eliminaci�n</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Cerrar">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="alert alert-danger">�Est� seguro de que desea
									eliminar esta cuenta? Esta acci�n no se puede deshacer.</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
								<!-- Este bot�n ahora env�a el formulario -->
								<input type="submit" class="btn btn-danger" name="btnEliminar"
									value="Eliminar">
							</div>
						</div>
					</div>
				</div>
			</form>
			<%
				}
				} else {
			%>
			<div class="alert alert-danger w-100 text-center">No se
				encontraron Cuentas.</div>
			<%
				}
			%>
		</div>
		<%
			if (cuentas.size() < 3) {
		%>
		<div class="row form mt-4">
			<form action="servletGestionarCuentas" method="post"
				class="w-100 container-sm border border-secondary shadow p-4 rounded">
				<input type="hidden" name="InputIdCliente" id="InputIdCliente"
					value="<%=request.getAttribute("idCliente")%>">
				<h4 class="text-center">Agregar Nueva Cuenta</h4>
				<div class="form-group">
					<label for="tipoCuentaSelect">Tipo de Cuenta</label> <select
						class="form-control" id="tipoCuentaSelect" name="tipoCuentaSelect">
						<%
							for (TipoCuenta tipo : tiposCuenta) {
						%>
						<option value="<%=tipo.getId()%>"><%=tipo.getDescripcion()%></option>
						<%
							}
						%>
					</select>
				</div>
				<div class="form-group text-center">
					<button type="button" class="btn btn-success btn-sm"
						data-toggle="modal" data-target="#modalConfirmarAgregar">Agregar</button>
				</div>
				<div class="modal fade" id="modalConfirmarAgregar" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Confirmar Nueva Cuenta</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Cerrar">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body text-center">
								<i class="fas fa-exclamation-triangle text-warning"
									style="font-size: 2rem;"></i>
								<p class="mt-3">�Est� seguro de que desea crear esta cuenta?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
								<input class="btn btn-success" type="submit" value="Agregar"
									name="btnAgregar">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<%
			}
		%>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		
	



<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</body>
</html>
