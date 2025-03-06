package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
 * Servlet implementation class servletListarTodosLosPrestamos
 */
@WebServlet("/servletListarTodosLosPrestamos")
public class servletListarTodosLosPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	 
	 

	 
    public servletListarTodosLosPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	if(request.getParameter("btnFiltro") != null)
        	{
        		BigDecimal mayorA, menorA;
        		String mayorAParam = request.getParameter("mayorA");
        	    String menorAParam = request.getParameter("menorA");
        	    mayorA = (mayorAParam != null && !mayorAParam.isEmpty()) ? new BigDecimal(mayorAParam) : BigDecimal.valueOf(0);
        	    menorA = (menorAParam != null && !menorAParam.isEmpty()) ? new BigDecimal(menorAParam) : BigDecimal.valueOf(99999999.99);
        	    
        		
        		if(mayorA.compareTo(BigDecimal.ZERO) < 0)
        		{
        			request.setAttribute("error", "No se aceptan valores menores a 0.");
        			request.setAttribute("mayorA", "");
        		}
        		if(menorA.compareTo(BigDecimal.ZERO) < 0)
        		{
        			request.setAttribute("error", "No se aceptan valores menores a 0.");
        			request.setAttribute("menorA", "");
        		}
        		if( mayorA.compareTo(menorA) >= 0)
        		{
        			request.setAttribute("error", "No se encontraron Historiales de Prestamos.");
        			request.setAttribute("menorA", "");
        		}
        		
        		
        		List<Prestamo> prestamos = prestamoNeg.ListarPrestamosFiltrados(mayorA, menorA);
        		  // Asegurar que prestamos no sea null
                if (prestamos == null) {
                    prestamos = new ArrayList<>();
                }
                request.setAttribute("prestamos", prestamos);

                if (prestamos.isEmpty()) {
                    request.setAttribute("error", "No se encontraron Historiales de Préstamos.");
                }
        		request.getRequestDispatcher("HistorialPrestamos.jsp").forward(request, response);
        	}
        	else{	
        		// Obtener la lista de prestamos
	            List<Prestamo> prestamos = prestamoNeg.ListarTodosLosPrestamos();
	
	            // Verificar si la lista de prestamos no es nula o vacía
	            if (prestamos == null) {
	                prestamos = new ArrayList<>();
	            }
	            request.setAttribute("prestamos", prestamos);

	            if (prestamos.isEmpty()) {
	                request.setAttribute("error", "No se encontraron Historiales de Préstamos.");
	            }

	            // Redirigir a la página JSP para mostrar la lista de prestamos
	            request.getRequestDispatcher("HistorialPrestamos.jsp").forward(request, response);
        	}
        	
        } catch (Exception e) {
            // Manejar excepciones y redirigir a una página de error si es necesario
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al obtener la lista de historial de prestamos.");
            request.getRequestDispatcher("HistorialPrestamos.jsp").forward(request, response);
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
