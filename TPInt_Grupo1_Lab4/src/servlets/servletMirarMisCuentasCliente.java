package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

/**
 * Servlet implementation class servletMirarMisCuentasCliente
 */
@WebServlet("/servletMirarMisCuentasCliente")
public class servletMirarMisCuentasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletMirarMisCuentasCliente() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		//recibir id
		Usuario userLogged =  (Usuario) request.getSession().getAttribute("usuario");	
		//buscar las cuentas referidas al cliente
		List<Cuenta> lista = cuentaNeg.leerLasCuentasDelCliente(userLogged.getCliente().getId());
		//devolver cuentas
		request.setAttribute("cuentas", lista);
		request.getRequestDispatcher("cuentasCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		//recibir numero de cuenta
		if(request.getParameter("btnCuenta") != null)
		{
			int nroCuenta = Integer.parseInt(request.getParameter("btnCuenta"));
			//buscar movimientos de esa cuenta
			List<Movimiento> movimientos = cuentaNeg.leerMovimientosDeCuenta(nroCuenta);
			//mandar lista al jsp
			request.setAttribute("movimientos", movimientos);
			
			Usuario userLogged =  (Usuario) request.getSession().getAttribute("usuario");	
			//buscar las cuentas referidas al cliente
			List<Cuenta> lista = cuentaNeg.leerLasCuentasDelCliente(userLogged.getCliente().getId());
			//devolver cuentas
			request.setAttribute("cuentas", lista);
			request.getRequestDispatcher("cuentasCliente.jsp").forward(request, response);

			
		}
	}

}
