package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;
import negocio.ClienteNeg;
import negocio.CuentaNeg;
import negocio.PaisNeg;
import negocio.TipoCuentaNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.TipoCuentaNegImpl;


/**
 * Servlet implementation class servletListarClientes
 */
@WebServlet("/servletListarClientes")
public class servletListarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	 ClienteNeg clienteNeg = new ClienteNegImpl();
    
	
    public servletListarClientes() {
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 try {
             // Llamar al método ListarUsuarios
             List<Cliente> clientes = clienteNeg.ListarClientes();

             // Verificar si la lista no es nula
             if (clientes != null && !clientes.isEmpty()) {
                 // Establecer la lista de usuarios como un atributo en el request
                 request.setAttribute("clientes", clientes);
             } else {
                 // Si no hay usuarios, establecer un mensaje de error
                 request.setAttribute("error", "No se encontraron clientes.");
             }

       
             
             // Redirigir a la página JSP para mostrar la lista de usuarios
             request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
         } catch (Exception e) {
             // Manejar excepciones y redirigir a una página de error si es necesario
             e.printStackTrace();
             request.setAttribute("error", "Ocurrió un error al obtener la lista de clientes.");
             request.getRequestDispatcher("ListarClientes.jsp").forward(request, response);
         }
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnGestionCuenta") != null)
		{
			
			//recuperar ID de cliente
			int idCliente = Integer.parseInt(request.getParameter("InputIdCliente"));
			//recolectar las cuentas relacionadas al cliente
			CuentaNeg cuentaNeg = new CuentaNegImpl();
			List<Cuenta> cuentasDelCliente = cuentaNeg.leerLasCuentasDelCliente(idCliente);
			//recolectar los tipos de cuenta existentes
			TipoCuentaNeg tipoCuentaNeg = new TipoCuentaNegImpl();
			List<TipoCuenta> tipoCuenta = tipoCuentaNeg.leerTiposCuenta();
			//comprobar si son mas de 3 para ver si se pone la opcion de agregar cuenta
			boolean opcAgregarDisponible = cuentaNeg.clienteAptoDeAgregarCuenta(idCliente);
			//enviar al jsp
			request.setAttribute("tiposCuenta", tipoCuenta);
			request.setAttribute("cuentas", cuentasDelCliente);
			request.setAttribute("opcAgregarDisponible", opcAgregarDisponible);
			request.getRequestDispatcher("GestionarCuentasAdmin.jsp").forward(request, response);
		}
	}

}
