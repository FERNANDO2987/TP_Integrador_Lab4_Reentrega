<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ page import="entidad.Prestamo" %>  
<%@ page import="java.util.List" %>  
<%@ page import="entidad.ProvinciaConClientes" %>  

<!DOCTYPE html>  
<html lang="es">  
<head>  
    <meta charset="ISO-8859-1">  
    <title>Flujo de Dinero</title>  
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>  
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free/css/all.min.css">  
    <style>  
        .chart-container {  
            position: relative;  
            margin-top: 20px;  
        }  
        .date-pick {  
            display: flex;  
            align-items: center;  
            gap: 10px;  
        }  
        .button {  
            background-color: #eb3b1f;  
            color: white;  
            padding: 0.5rem 1rem;  
            border: none;  
            border-radius: 0.375rem;  
            cursor: pointer;  
            transition: background-color 0.3s;  
        }  
        .button:hover {  
            background-color: #c62c1a;  
        }  
    </style>  
</head>  
<body class="bg-gray-100 flex flex-col p-6">  

    <div class="flex w-full max-w-screen-xl mx-auto">  
        <div class="bg-white p-6 rounded-lg shadow-md w-full max-w-3xl">  
        <h2 class="text-2xl font-bold mb-4 text-center">Flujo de Dinero</h2>  
        
        <form action="servletGraficos" method="GET" class="flex justify-between items-center mb-4">  
            <div class="flex flex-col">  
                <label class="block text-sm font-semibold">Desde:</label>  
                <input type="date" name="fechaDesde" required class="border p-2 rounded">  
            </div>  
            <div class="flex flex-col">  
                <label class="block text-sm font-semibold">Hasta:</label>  
                <input type="date" name="fechaHasta" required class="border p-2 rounded">  
            </div>  
            <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition">  
                Aplicar filtro  
            </button>  
        </form>  
            <div class="chart-container">  
                <canvas id="prestamosChart"></canvas>  
            </div>  
        </div>  

        <div class="bg-white p-6 rounded-lg shadow-md w-1/3 ml-6">  
            <h2 class="text-2xl font-bold mb-4 text-center">Clientes por provincia</h2>  
            <div class="chart-container">  
                <canvas id="clientesPorProvinciaChart"></canvas>  
            </div>  
        </div>  
    </div>  

<script>  
    // Obtener datos de préstamos desde la lista enviada por el servlet  
    var prestamos = [];  
    <% if (request.getAttribute("prestamos") != null) {  
        List<Prestamo> prestamosList = (List<Prestamo>) request.getAttribute("prestamos");  
        for (Prestamo p : prestamosList) { %>  
            prestamos.push({  
                tipoMovimiento: "<%= p.getObservaciones() %>",  
                cantidad: <%= p.getImporte() %>  
            });  
    <% }} %>  

    // Obtener datos de provincias con clientes  
    var provinciasData = [];  
    <% if (request.getAttribute("provincias") != null) {  
        List<ProvinciaConClientes> provinciasList = (List<ProvinciaConClientes>) request.getAttribute("provincias");  
        for (ProvinciaConClientes provincia : provinciasList) { %>  
            provinciasData.push({  
                nombre: "<%= provincia.getProvincia().getNombre() %>",  
                cantidad: <%= provincia.getCantidadClientes() %>  
            });  
    <% }} %>  

    // Definir colores específicos para cada provincia  
    const coloresProvincias = [  
        'rgba(255, 99, 132, 0.6)', // Ejemplo: color para Santa Fe  
        'rgba(54, 162, 235, 0.6)', // Ejemplo: color para Buenos Aires  
        'rgba(255, 206, 86, 0.6)', // Ejemplo: color para Córdoba  
        'rgba(75, 192, 192, 0.6)', // Ejemplo: color para Mendoza  
        // Agrega más colores según la cantidad de provincias  
    ];  

    function getRandomColor() {
        return 'rgba(' + Math.floor(Math.random() * 255) + ',' +
                         Math.floor(Math.random() * 255) + ',' +
                         Math.floor(Math.random() * 255) + ',0.6)';
    }

    
    // Extraer datos para gráficos  
    var tiposMovimiento = prestamos.map(p => p.tipoMovimiento);  
    var cantidades = prestamos.map(p => p.cantidad);  
    var coloresPrestamos = prestamos.map(() => getRandomColor()); // Puedes mantener los colores aleatorios para este gráfico  

    var nombresProvincias = provinciasData.map(p => p.nombre);  
    var cantidadClientes = provinciasData.map(p => p.cantidad);  

    // Gráfico de barras - Flujo de dinero  
    var ctxPrestamos = document.getElementById('prestamosChart').getContext('2d');  
    new Chart(ctxPrestamos, {  
        type: 'bar',  
        data: {  
            labels: tiposMovimiento,  
            datasets: [{  
                label: 'Flujo de Dinero ($ Miles)',  
                data: cantidades,  
                backgroundColor: coloresPrestamos,  
                borderColor: coloresPrestamos.map(color => color.replace("0.6", "1")),  
                borderWidth: 1  
            }]  
        },  
        options: {  
            responsive: true,  
            scales: {  
                x: {  
                    title: { display: true, text: 'Período' },  
                },  
                y: {  
                    beginAtZero: true,  
                    title: { display: true, text: '$ (Miles)' },  
                }  
            }  
        }  
    });  

    // Gráfico de torta - Clientes por provincia  
    var ctxClientes = document.getElementById('clientesPorProvinciaChart').getContext('2d');  
    new Chart(ctxClientes, {  
        type: 'pie',  
        data: {  
            labels: nombresProvincias,  
            datasets: [{  
                data: cantidadClientes,  
                backgroundColor: coloresProvincias.slice(0, nombresProvincias.length), // Usa colores específicos para cada provincia  
                borderColor: coloresProvincias.slice(0, nombresProvincias.length).map(color => color.replace("0.6", "1")),  
                borderWidth: 1  
            }]  
        },  
        options: {  
            responsive: true  
        }  
    });  
</script>  

</body>  
</html>