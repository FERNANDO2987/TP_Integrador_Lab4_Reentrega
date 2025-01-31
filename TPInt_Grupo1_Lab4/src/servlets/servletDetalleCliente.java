package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciasNeg;
import negocio.UsuarioNeg;
import entidad.Cliente;
import negocioImpl.ClienteNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletDetalleCliente
 */
@WebServlet("/servletDetalleCliente")
public class servletDetalleCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteNeg clienteNeg = new ClienteNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
       
    public servletDetalleCliente() { 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	int id = Integer.parseInt(request.getParameter("id"));
            Cliente cliente = clienteNeg.obtenerCliente(id);
            Usuario usuario = usuarioNeg.obtenerUsuario(id);
            
            
            
            System.out.println(id);

            if (cliente != null) {
                request.setAttribute("cliente", cliente);
            } else {
                request.setAttribute("error", "Cliente no encontrado");
            }

            request.getRequestDispatcher("DetalleCliente.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejar otras excepciones
            request.setAttribute("error", "Ocurrió un error al procesar la solicitud.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

