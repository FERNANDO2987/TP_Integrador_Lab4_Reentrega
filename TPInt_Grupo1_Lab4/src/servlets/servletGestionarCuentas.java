package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.TipoCuenta;
import negocio.CuentaNeg;
import negocio.TipoCuentaNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.TipoCuentaNegImpl;

/**
 * Servlet implementation class servletGestionarCuentas
 */
@WebServlet("/servletGestionarCuentas")
public class servletGestionarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletGestionarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar ID de cliente
		int idCliente = Integer.parseInt(request.getParameter("id"));
		//recolectar las cuentas relacionadas al cliente
		CuentaNeg cuentaNeg = new CuentaNegImpl();
		List<Cuenta> cuentasDelCliente = cuentaNeg.leerLasCuentasDelCliente(idCliente);
		//recolectar los tipos de cuenta existentes
		TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
		List<TipoCuenta> tipoCuenta = tipoCuentaNeg.leerTiposCuenta();
		//comprobar si son mas de 3 para ver si se pone la opcion de agregar cuenta
		boolean opcAgregarDisponible = cuentaNeg.clienteAptoDeAgregarCuenta(idCliente);
		//enviar al jsp
		request.setAttribute("idCliente", idCliente);
		request.setAttribute("tiposCuenta", tipoCuenta);
		request.setAttribute("cuentas", cuentasDelCliente);
		request.setAttribute("opcAgregarDisponible", opcAgregarDisponible);
		request.getRequestDispatcher("GestionarCuentasAdmin.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Agregar Cuenta
		int idCliente = 0;
		if(request.getParameter("btnAgregar") != null)
		{
			int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuentaSelect"));
			idCliente = Integer.parseInt(request.getParameter("InputIdCliente"));
			CuentaNeg negocioCuenta = new CuentaNegImpl();
			Cuenta cuenta = new Cuenta();
			cuenta.getCliente().setId(idCliente);
			cuenta.getTipoCuenta().setId(idTipoCuenta);
			negocioCuenta.agregarCuenta(cuenta);
		}
		request.getRequestDispatcher("servletGestionarCuentas?id="+ idCliente);
		
		
	}

}
