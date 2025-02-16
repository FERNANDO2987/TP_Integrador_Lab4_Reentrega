package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Prestamo;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;



/**
 * Servlet implementation class servletPrestamosClientes
 */
@WebServlet("/servletPrestamosClientes")
public class servletPrestamosClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	

PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	 
    public servletPrestamosClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la lista de prestamos
            List<Prestamo> prestamos = prestamoNeg.ListarPrestamos();
            List<Prestamo> historialPrestamos = prestamoNeg.ListarTodosLosPrestamos();

            // Obtener los montos pendientes
            Map<String, BigDecimal> montosPendientes = prestamoNeg.ObtenerMontosPendientes();
            
            
            
          
            // Verificar si la lista de prestamos no es nula o vacía
            if (historialPrestamos !=null) {
                // Establecer la lista de prestamos como un atributo en el request
                request.setAttribute("historialPrestamos", historialPrestamos);
            } else {
                // Si no hay prestamos, establecer un mensaje de error
                request.setAttribute("error", "No se encontraron Prestamos.");
            }
        
            

            // Verificar si la lista de prestamos no es nula o vacía
            if (prestamos != null && !prestamos.isEmpty()) {
                // Establecer la lista de prestamos como un atributo en el request
                request.setAttribute("prestamos", prestamos);
            } else {
                // Si no hay prestamos, establecer un mensaje de error
                request.setAttribute("error", "No se encontraron Prestamos.");
            }

            // Verificar si los montos pendientes no son nulos
            if (montosPendientes != null && !montosPendientes.isEmpty()) {
                // Establecer los montos como atributos en el request
                request.setAttribute("montoTotalSolicitado", montosPendientes.get("montoTotalSolicitado"));
                request.setAttribute("montoTotalAdjudicado", montosPendientes.get("montoTotalAdjudicado"));
            } else {
                // Si no se pueden obtener los montos, establecer un mensaje de error
                request.setAttribute("errorMontos", "No se pudieron obtener los montos pendientes.");
            }
            
            
        

            // Redirigir a la página JSP para mostrar la lista de prestamos
            request.getRequestDispatcher("PrestamosClientes.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejar excepciones y redirigir a una página de error si es necesario
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al obtener la lista de prestamos.");
            request.getRequestDispatcher("PrestamosClientes.jsp").forward(request, response);
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
