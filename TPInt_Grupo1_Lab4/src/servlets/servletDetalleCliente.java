package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.UsuarioNeg;
import entidad.Cliente;
import negocioImpl.ClienteNegImpl;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletDetalleCliente
 */
@WebServlet("/servletDetalleCliente")
public class servletDetalleCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	ClienteNeg clienteNeg = new ClienteNegImpl();
       
    public servletDetalleCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
