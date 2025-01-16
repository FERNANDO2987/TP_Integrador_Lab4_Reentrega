package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.UsuarioNeg;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletListarUsuarios
 */
@WebServlet("/servletListarUsuarios")
public class servletListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	   
    public servletListarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	          try {
	              // Llamar al método ListarUsuarios
	              List<Usuario> usuarios = usuarioNeg.ListarUsuarios();

	              // Verificar si la lista no es nula
	              if (usuarios != null && !usuarios.isEmpty()) {
	                  // Establecer la lista de usuarios como un atributo en el request
	                  request.setAttribute("usuarios", usuarios);
	              } else {
	                  // Si no hay usuarios, establecer un mensaje de error
	                  request.setAttribute("error", "No se encontraron usuarios.");
	              }

	              // Redirigir a la página JSP para mostrar la lista de usuarios
	              request.getRequestDispatcher("ListarUsuarios.jsp").forward(request, response);
	          } catch (Exception e) {
	              // Manejar excepciones y redirigir a una página de error si es necesario
	              e.printStackTrace();
	              request.setAttribute("error", "Ocurrió un error al obtener la lista de usuarios.");
	              request.getRequestDispatcher("ListarUsuarios.jsp").forward(request, response);
	          }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
