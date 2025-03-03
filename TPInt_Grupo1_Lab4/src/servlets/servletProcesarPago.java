package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;


@WebServlet("/servletProcesarPago")
public class servletProcesarPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	
	 private static final String MENSAJE_EXITO = "La cuota fue pagada con exito";
	private static final String MENSAJE_ERROR = "Error al pagar la cuota";
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
    public servletProcesarPago() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idParam = request.getParameter("idPrestamo");

	    if (idParam == null || idParam.isEmpty()) {
	        request.setAttribute("mensajeError", "No se proporcionó un ID de préstamo.");
	        request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
	        return;
	    }

	    try {
	        int idPrestamo = Integer.parseInt(idParam);

	        // Llamada al método que paga la cuota
	        String mensaje = prestamoNeg.PagarCuota(idPrestamo);

	        if (mensaje != null && !mensaje.trim().isEmpty()) {
                request.setAttribute("mensajeExito", MENSAJE_EXITO);
              
            } else {
                request.setAttribute("mensajeError", MENSAJE_ERROR);
            }


	    } catch (NumberFormatException e) {
	        request.setAttribute("mensajeError", "El ID del préstamo no es válido.");
	    } catch (Exception e) {
	        request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
	        e.printStackTrace();
	    }

	    //request.getRequestDispatcher("DatosCuentas.jsp").forward(request, response);
	    request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
	}

	
	}


