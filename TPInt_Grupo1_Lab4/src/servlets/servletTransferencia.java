package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		
		Usuario userLogged =  (Usuario) request.getSession().getAttribute("usuario");
		List<Cuenta> lista = cuentaNeg.leerLasCuentasDelCliente(userLogged.getCliente().getId());
		
		
		request.setAttribute("listaDeMisCuentas", lista);
		request.getRequestDispatcher("Transferencia.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
