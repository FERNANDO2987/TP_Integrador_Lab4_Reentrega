package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Prestamo;

import entidad.ProvinciaConClientes;
import negocio.PrestamoNeg;
import negocio.ProvinciasNeg;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.ProvinciaNegImpl;

/**
 * Servlet implementation class servletGraficos
 */
@WebServlet("/servletGraficos")
public class servletGraficos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	 ProvinciasNeg provinciaNeg = new ProvinciaNegImpl();
	
    public servletGraficos() {
        super();
        // TODO Auto-generated constructor stub
    }


    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
            // Obtener las fechas desde los parámetros de la solicitud
            String fechaDesdeStr = request.getParameter("fechaDesde");
            String fechaHastaStr = request.getParameter("fechaHasta");
        	
        List<ProvinciaConClientes> provincias = provinciaNeg.ObtenerCantidadClientesPorProvincia();

            // Verificar si la lista no es nula
            if (provincias != null && !provincias.isEmpty()) {
               
                request.setAttribute("provincias", provincias);
            } else {
                // Si no hay usuarios, establecer un mensaje de error
                request.setAttribute("error", "No se encontraron provincias.");
            }
        	
        	
        	
        	
        	
        	
      

            if (fechaDesdeStr == null || fechaHastaStr == null) {
                request.setAttribute("error", "Debe proporcionar ambas fechas.");
                request.getRequestDispatcher("Graficos.jsp").forward(request, response);
                return;
            }

            // Convertir las fechas de String a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaDesde = LocalDate.parse(fechaDesdeStr, formatter);
            LocalDate fechaHasta = LocalDate.parse(fechaHastaStr, formatter);

            // Instanciar el servicio de negocio (asegúrate de tenerlo correctamente inyectado o instanciado)
            PrestamoNeg prestamoNeg = new PrestamoNegImpl();

            // Llamar al método para obtener los movimientos
            List<Prestamo> prestamos = prestamoNeg.ObtenerMovimientosPorFecha(fechaDesde, fechaHasta);

            // Pasar la lista de préstamos a la vista JSP
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("Graficos.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al obtener los movimientos.");
            request.getRequestDispatcher("Graficos.jsp").forward(request, response);
        }
    }



	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
