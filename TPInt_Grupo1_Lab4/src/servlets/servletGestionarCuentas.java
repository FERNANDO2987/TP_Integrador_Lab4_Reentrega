package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;

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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Agregar Cuenta
		if(request.getAttribute("btnAgregar") != null)
		{
			int idTipoCuenta = Integer.parseInt(request.getParameter("tipoCuentaSelect"));
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			CuentaNeg negocioCuenta = new CuentaNegImpl();
			Cuenta cuenta = new Cuenta();
			cuenta.getCliente().setId(idCliente);
			cuenta.getTipoCuenta().setId(idTipoCuenta);
			negocioCuenta.agregarCuenta(cuenta);
		}
		
		
	}

}
