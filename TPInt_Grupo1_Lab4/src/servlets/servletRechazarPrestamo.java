package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletRechazarPrestamo
 */
@WebServlet("/servletRechazarPrestamo")
public class servletRechazarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
	 private static final String MENSAJE_EXITO = "Prestamo rechazado con exito";
	    private static final String MENSAJE_ERROR = "Error al rechazar prestamo";
  
    public servletRechazarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                boolean eliminado = prestamoNeg.RechazarPrestamo(id);
        
                
                if (eliminado) {
                   
                    request.setAttribute("mensajeExito", MENSAJE_EXITO);  
                

                } else {
                   
                    request.setAttribute("mensajeError", MENSAJE_EXITO);  
                }

            } catch (Exception e) {
                request.getSession().setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
                e.printStackTrace(); // Para depuración
            }
        } else {
            request.getSession().setAttribute("mensajeError", "No se proporcionó un ID del prestamo.");
        }

       // request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
        // En lugar de redirigir al JSP directamente, redirige al servlet que lista los clientes
        request.getRequestDispatcher("servletPrestamosClientes").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
