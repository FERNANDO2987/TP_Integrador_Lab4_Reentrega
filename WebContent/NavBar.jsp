<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sistema Gestión Banco</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
		<link href="css/EstiloNavBar.css" rel="stylesheet">
	</head>
	<body>
		<header>
			<div class= "row">
				<nav class="navbar bg-body-tertiary d-flex justify-content-between">
	        		<img src="/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" />
	        		<span>Sistema Gestión Banco</span>
	        		<div class="d-flex">
	          			<span>Usuario X</span>
	          			<button class="btn btn-outline-primary ms-2">Cerrar Sesión</button>
	        		</div>
	      		</nav>
			</div>
	    </header>
	  	<main>
	  		<div class= "row">
				<div class="col-2">
				  	<div class="d-flex flex-column align-items-start h-100"> 
				    	<div class="nav flex-column nav-pills me-3 w-100" id="v-pills-tab" role="tablist" aria-orientation="vertical">
				      		<button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">Home</button>
				      		<button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Clientes</button>
				      		<button class="nav-link" id="v-pills-disabled-tab" data-bs-toggle="pill" data-bs-target="#v-pills-disabled" type="button" role="tab" aria-controls="v-pills-disabled" aria-selected="false">Cuentas</button>
				      		<button class="nav-link" id="v-pills-messages-tab" data-bs-toggle="pill" data-bs-target="#v-pills-messages" type="button" role="tab" aria-controls="v-pills-messages" aria-selected="false">Prestamos</button>
				      		<button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings" type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">Informes</button>
				    	</div>			   	 	
				  	</div>
				</div>
				<div class= "col-10">
					<div class="tab-content flex-grow-1 overflow-auto" id="v-pills-tabContent"> 
				      	<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab" tabindex="0">...</div>
				      	<div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab" tabindex="0">...</div>
				      	<div class="tab-pane fade" id="v-pills-disabled" role="tabpanel" aria-labelledby="v-pills-disabled-tab" tabindex="0">...</div>
				      	<div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab" tabindex="0">...</div>
				      	<div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab" tabindex="0">...</div>
				    </div>
				</div>
			</div>
	    </main>
	 	<footer>
	 		<div class="container">
        		<span>&copy; 2025 Sistema Gestión Banco</span>
      		</div>
	    </footer>
	</body>
</html>