package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNeg;
import negocioImpl.ClienteNegImpl;

/**
 * Servlet implementation class servletEliminarCliente
 */
@WebServlet("/servletEliminarCliente")
public class servletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	 ClienteNeg clienteNeg = new ClienteNegImpl();
	 private static final String MENSAJE_EXITO = "Cliente eliminado con exito";
	    private static final String MENSAJE_ERROR = "Error al eliminar Cliente";
	
    public servletEliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                boolean eliminado = clienteNeg.EliminarCliente(id);

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
            request.getSession().setAttribute("mensajeError", "No se proporcionó un ID de cliente para eliminar.");
        }

       // request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
        // En lugar de redirigir al JSP directamente, redirige al servlet que lista los clientes
        request.getRequestDispatcher("servletListarClientes").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
