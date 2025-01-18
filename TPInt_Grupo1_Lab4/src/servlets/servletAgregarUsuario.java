package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Usuario;
import negocio.UsuarioNeg;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletAgregarUsuario
 */
@WebServlet("/servletAgregarUsuario")
public class servletAgregarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servletAgregarUsuario() {
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
	            // Recibir los datos del formulario
	            String usuario = request.getParameter("usuario");
	            String password = request.getParameter("password");
	            boolean isAdmin = request.getParameter("isAdmin") != null; // Checkbox, true si est� marcado

	            // Validar los par�metros recibidos
	            if (usuario == null || usuario.isEmpty() || 
	                password == null || password.isEmpty())
	                {
	                request.setAttribute("error", "Todos los campos son obligatorios.");
	                request.getRequestDispatcher("AgregarUsuario.jsp").forward(request, response);
	                return;
	            }

	          

	            // Crear el objeto Usuario
	            Usuario newUser = new Usuario();
	            newUser.setUsuario(usuario);
	            newUser.setPassword(password);
	            newUser.setAdmin(isAdmin);

	      

	            // Llamar a la l�gica de negocio para guardar el usuario
	            UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	            boolean success = usuarioNeg.AgregarUsuario(newUser);

	            // Redirigir seg�n el resultado
	            if (success) {
	                response.sendRedirect("ListarUsuarios.jsp"); // P�gina de �xito
	            } else {
	                request.setAttribute("error", "No se pudo agregar el usuario.");
	                request.getRequestDispatcher("AgregarUsuario.jsp").forward(request, response);
	            }
	        } catch (NumberFormatException e) {
	            // Manejo de error si clienteId no es un n�mero v�lido
	            request.setAttribute("error", "El ID del cliente debe ser un n�mero v�lido.");
	            request.getRequestDispatcher("AgregarUsuario.jsp").forward(request, response);
	        } catch (Exception e) {
	            // Manejo de errores generales
	            request.setAttribute("error", "Ocurri� un error inesperado: " + e.getMessage());
	            request.getRequestDispatcher("AgregarUsuario.jsp").forward(request, response);
	        }

	}

}
