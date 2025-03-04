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
import excepciones.UsuarioNoLogueadoException;
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


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UsuarioNoLogueadoException {  
	    if (request.getParameter("btnAceptar") != null) {  
	        String usuario = request.getParameter("usuario");  
	        String contrasenia = request.getParameter("contrasenia");  

	        if (usuario != null && !usuario.isEmpty() && contrasenia != null && !contrasenia.isEmpty()) {  
	        	try {
	            	UsuarioNegImpl clienteNegocio = new UsuarioNegImpl();
	            	Usuario usuarioSesion = clienteNegocio.iniciarSesion(usuario, contrasenia);
	            	HttpSession session = request.getSession();
	                session.setAttribute("usuario", usuarioSesion);
	             // Redirige dependiendo del tipo de usuario  
	                String destino = usuarioSesion.isAdmin() ? "Home.jsp" : "HomeCliente.jsp";
	                RequestDispatcher rd = request.getRequestDispatcher(destino);  
	                rd.forward(request, response);
	            }catch(UsuarioNoLogueadoException e) {
	            	System.out.println(e.getMessage());
	            	e.printStackTrace();
	            	response.sendRedirect("Login.jsp?error=true");
	            }catch(Exception e) {
	            	e.printStackTrace();
	            	response.sendRedirect("Login.jsp?error=true");
	            }
	        } else {  
	            response.sendRedirect("Login.jsp?error=true");  
	        }  
	    }  
	}


}