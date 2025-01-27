package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PrestamoDaoImpl;
import entidad.Prestamo;

/**
 * Servlet implementation class servletListarPrestamos
 */
@WebServlet("/servletListarPrestamos")
public class servletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrestamoDaoImpl prestamoNeg = new PrestamoDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 // Llamar al m�todo ListarPrestamos
            List<Prestamo> prestamos = prestamoNeg.ListarPrestamos();

            // Verificar si la lista no es nula
            if (prestamos != null && !prestamos.isEmpty()) {
                // Establecer la lista de prestamos como un atributo en el request
                request.setAttribute("prestamos", prestamos);
            } else {
                // Si no hay prestamos, establecer un mensaje de error
                request.setAttribute("error", "No se encontraron prestamos.");
            }
            
            // Redirigir a la p�gina JSP para mostrar la lista de prestamos
            request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);
		 } catch (Exception e) {
			// Manejar excepciones y redirigir a una p�gina de error si es necesario
            e.printStackTrace();
            request.setAttribute("error", "Ocurri� un error al obtener la lista de prestamos.");
            request.getRequestDispatcher("ListarPrestamos.jsp").forward(request, response);
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
