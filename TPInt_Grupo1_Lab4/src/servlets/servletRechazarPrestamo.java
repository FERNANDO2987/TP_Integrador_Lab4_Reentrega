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
                boolean rechazado = prestamoNeg.RechazarPrestamo(id);  

                // Maneja los mensajes de manera adecuada  
                if (rechazado) {  
                    request.setAttribute("mensajeExito", MENSAJE_EXITO);  
                
                } else {  
                    request.setAttribute("mensajeError", MENSAJE_ERROR);  
                }  
            } catch (NumberFormatException e) {  
                request.setAttribute("mensajeError", "ID del préstamo no válido.");  
            } catch (Exception e) {  
                request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());  
                e.printStackTrace();  
            }  
        } else {  
            request.setAttribute("mensajeError", "No se proporcionó un ID del préstamo.");  
        }  

        // Redirigir al servlet que lista los préstamos; los mensajes se mantienen a través de forward  
        request.getRequestDispatcher("servletPrestamosClientes").forward(request, response);  
    } 

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
