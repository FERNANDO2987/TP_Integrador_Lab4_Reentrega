package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletLogin
 */
@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLogin() {
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
		if (request.getParameter("btnAceptar") != null) {
	        String usuario = request.getParameter("usuario");
	        String contrasenia = request.getParameter("contrasenia");

	        if (usuario != null && contrasenia != null) {
	            UsuarioNegImpl usuarioNegocio = new UsuarioNegImpl();
	            Usuario usuarioSesion = new Usuario();
	            usuarioSesion = usuarioNegocio.iniciarSesion(usuario, contrasenia);

	            if (usuarioSesion != null) {
	            	//completar con cliente el usuario
	                HttpSession session = request.getSession();
	                session.setAttribute("usuario", usuarioSesion);
	                RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
	                rd.forward(request, response);
	            } else {
	                response.sendRedirect("Login.jsp?error=true");
	            }
	        } else {
	            response.sendRedirect("Login.jsp?error=true");
	        }
	    }
	}

}