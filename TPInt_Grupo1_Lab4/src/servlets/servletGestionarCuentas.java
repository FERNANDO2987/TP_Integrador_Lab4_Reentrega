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
		try
		{
			//inicializar una variable idCliente
			int idCliente = 0;
			CuentaNeg negocioCuenta = new CuentaNegImpl();
			//detectar si se pulso el boton de agregar
			if(request.getParameter("btnAgregar") != null)
			{
				//traer los datos del form
				int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuentaSelect"));
				idCliente = Integer.parseInt(request.getParameter("InputIdCliente"));
				//pasar los datos a un objeto cuenta
				Cuenta cuenta = new Cuenta();
				cuenta.getCliente().setId(idCliente);
				cuenta.getTipoCuenta().setId(idTipoCuenta);
				
				negocioCuenta.agregarCuenta(cuenta);
			}
			// detectar si se pulso el boton de modificar
			if(request.getParameter("btnModificar") != null)
			{
				//traer los datos del form
				int idSelectTipoCuenta = Integer.parseInt(request.getParameter("selectCuenta"));
				int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
				//pasar los datos a un objeto cuenta
				Cuenta cuenta = new Cuenta();
				cuenta.setNroCuenta(idCuenta);
				cuenta.getTipoCuenta().setId(idSelectTipoCuenta);
				
				negocioCuenta.modificarCuenta(cuenta);
			}
			//actualizar la pagina volviendo a llamar a un doGet
			response.sendRedirect("servletGestionarCuentas?id=" + idCliente);
		}
		catch(Exception e)
		{
			request.setAttribute("excepcionMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}		
		
	}

}
