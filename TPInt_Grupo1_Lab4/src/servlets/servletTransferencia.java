package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Transferencia;
import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.TransferenciaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.TransferenciaNegImpl;

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
		if(request.getParameter("btnTransferir") != null)
		{
			System.out.println("Boton Transferir presionado");
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		BigDecimal monto;
		String detalle;
		cuentaOrigen.setCbu(request.getParameter("cuentaOrigen"));
		cuentaDestino.setCbu(request.getParameter("cuentaDestino"));
		if(request.getParameter("monto") != null)
		{
			monto = new BigDecimal(request.getParameter("monto"));
		}
		else
		{
			monto = new BigDecimal(0);
		}
		detalle = request.getParameter("detalle");
		Transferencia transferencia = new Transferencia(cuentaOrigen, cuentaDestino, monto, detalle);
		String transferenciaInvalida;
		
		
		//chequeo de validaciones
		TransferenciaNeg transferenciaNeg = new TransferenciaNegImpl();
		if (!transferenciaNeg.validarCbuOrigen(transferencia))
		{
			System.out.println("CBU Origen Invalido");
			transferenciaInvalida = "El CBU de Origen no es Valido";
			request.setAttribute("errorTransfer", transferenciaInvalida);
			doGet(request, response);
			return;
		}
		if(!transferenciaNeg.validarCbuDestino(transferencia))
		{
			System.out.println("CBU Destino Invalido");
			transferenciaInvalida = "El CBU de Destino no es Valido";
			request.setAttribute("errorTransfer", transferenciaInvalida);
			request.getRequestDispatcher("Transferencia.jsp").forward(request, response);
			return;
		}
		if(!transferenciaNeg.validarDineroOrigen(transferencia))
		{
			System.out.println("Saldo en cuenta insuficiente");
			transferenciaInvalida = "Saldo en cuenta Insuficiente";
			request.setAttribute("errorTransfer", transferenciaInvalida);
			request.getRequestDispatcher("Transferencia.jsp").forward(request, response);
			return;
		}
		if(!transferenciaNeg.validarDetalle(transferencia))
		{
			transferencia.setDetalle("");
		}
		
		System.out.println("todo logrado");
		transferenciaNeg.agregarTransferencia(transferencia);
		request.setAttribute("errorTransfer", new String("Transferencia Realizada con Exito"));
		request.getRequestDispatcher("Transferencia.jsp").forward(request, response);
		
		}
	}

}
