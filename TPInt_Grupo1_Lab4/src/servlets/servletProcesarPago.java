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
       
  
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
	 private static final String MENSAJE_EXITO = "La cuota fue pagada con exito";
	private static final String MENSAJE_ERROR = "Error al pagar la cuota";
	
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
	    String idParam = request.getParameter("id");
	    if (idParam != null && !idParam.isEmpty()) {
	        try {
	            int idPrestamo = Integer.parseInt(idParam);

	            // Llamada al m�todo que paga la cuota y recibe un mensaje
	            String mensaje = prestamoNeg.PagarCuota(idPrestamo);

	            if (mensaje != null && !mensaje.isEmpty()) {
	                // Si el mensaje indica �xito
	                if (mensaje.contains("exito")) { // O cualquier l�gica de �xito que definas en el mensaje
	                    request.setAttribute("mensajeExito", MENSAJE_EXITO);  
	                } else {
	                    // Si el mensaje indica alg�n error
	                    request.setAttribute("mensajeError", mensaje);  
	                }
	            } else {
	                // En caso de que no se reciba un mensaje
	                request.setAttribute("mensajeError", "Error desconocido.");
	            }

	        } catch (Exception e) {
	            // Captura cualquier excepci�n y establece un mensaje de error general
	            request.getSession().setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
	            e.printStackTrace(); // Para depuraci�n
	        }
	    } else {
	        request.getSession().setAttribute("mensajeError", "No se proporcion� un ID de pr�stamo.");
	    }

	    // Redirige al servlet que lista los clientes
	    request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
	}

	
	
	}


