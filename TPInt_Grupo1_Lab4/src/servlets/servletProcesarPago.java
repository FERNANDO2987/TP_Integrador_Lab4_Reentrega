package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletProcesarPago
 */
@WebServlet("/servletProcesarPago")
public class servletProcesarPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  try {
	            // Obtener parámetros enviados desde el formulario
	            String idPrestamoStr = request.getParameter("idPrestamo");
	            String nroCuentaStr = request.getParameter("nroCuenta");

	            // Validar que los parámetros no sean nulos o vacíos
	            if (idPrestamoStr == null || nroCuentaStr == null || idPrestamoStr.isEmpty() || nroCuentaStr.isEmpty()) {
	                request.setAttribute("error", "Debe seleccionar una cuenta.");
	                request.getRequestDispatcher("pagarPrestamo.jsp").forward(request, response);
	                return;
	            }

	            // Convertir valores a tipos adecuados
	            int idPrestamo = Integer.parseInt(idPrestamoStr);
	            int nroCuenta = Integer.parseInt(nroCuentaStr);

	            // Aquí iría la lógica de negocio para procesar el pago del préstamo
	            boolean pagoExitoso = true;

	            if (pagoExitoso) {
	                response.sendRedirect("confirmacion.jsp");
	            } else {
	                request.setAttribute("error", "No se pudo procesar el pago.");
	                request.getRequestDispatcher("pagarPrestamo.jsp").forward(request, response);
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Datos inválidos.");
	            request.getRequestDispatcher("pagarPrestamo.jsp").forward(request, response);
	        }
	}

}
