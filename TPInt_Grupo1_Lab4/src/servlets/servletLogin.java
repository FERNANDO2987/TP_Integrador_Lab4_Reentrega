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
       
    UsuarioNegImpl usuarioNegocio = new UsuarioNegImpl();  
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


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    if (request.getParameter("btnAceptar") != null) {  
	        String usuario = request.getParameter("usuario");  
	        String contrasenia = request.getParameter("contrasenia");  

	        if (usuario != null && contrasenia != null) {  
	        
	            Usuario usuarioSesion = usuarioNegocio.iniciarSesion(usuario, contrasenia);  

	            if (usuarioSesion != null) {  // Asegúrate de que la sesión sea válida  
	                HttpSession session = request.getSession();  
	                session.setAttribute("usuario", usuarioSesion);  

	                // Redirige dependiendo del tipo de usuario  
	                if (usuarioSesion.isAdmin()== true) {  
	                    RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");  
	                    rd.forward(request, response);  
	                } 
	                
	                // Redirige dependiendo del tipo de usuario  
	                if (usuarioSesion.isAdmin()== false) {  
	                    RequestDispatcher rd = request.getRequestDispatcher("HomeCliente.jsp");  
	                    rd.forward(request, response);  
	                } 
	                
	                
	            } else {  
	                // Si el usuario no existe o las credenciales son incorrectas  
	                response.sendRedirect("Login.jsp?error=true");  
	            }  
	        } else {  
	            response.sendRedirect("Login.jsp?error=true");  
	        }  
	    }  
	}

}