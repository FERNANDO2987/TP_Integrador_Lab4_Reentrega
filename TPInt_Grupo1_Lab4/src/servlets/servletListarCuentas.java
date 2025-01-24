package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class servletListarCuentas
 */
@WebServlet("/servletListarCuentas")
public class servletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	
    public servletListarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
             // Llamar al método ListarUsuarios
             List<Cuenta> cuentas = cuentaNeg.leerTodasLasCuentas();

             // Verificar si la lista no es nula
             if (cuentas != null && !cuentas.isEmpty()) {
                 // Establecer la lista de usuarios como un atributo en el request
                 request.setAttribute("cuentas", cuentas);
             } else {
                 // Si no hay usuarios, establecer un mensaje de error
                 request.setAttribute("error", "No se encontraron cuentas.");
             }

             // Redirigir a la página JSP para mostrar la lista de usuarios
             request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
         } catch (Exception e) {
             // Manejar excepciones y redirigir a una página de error si es necesario
             e.printStackTrace();
             request.setAttribute("error", "Ocurrió un error al obtener la lista de usuarios.");
             request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
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
