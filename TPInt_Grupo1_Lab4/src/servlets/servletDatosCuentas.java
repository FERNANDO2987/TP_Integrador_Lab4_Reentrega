package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidadDTO.CuentaDTO;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;


@WebServlet("/servletDatosCuentas")
public class servletDatosCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
	 private static final String MENSAJE_EXITO = "Cuenta Exitosa.";
	    private static final String MENSAJE_ERROR = "Error al obtener cuentas.";
	
    public servletDatosCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

	        if (usuario == null) {
	            response.sendRedirect("Login.jsp");
	            return;
	        }

	        int idCliente = usuario.getCliente().getId();
	        
	        try {
	            // Obtener solo los préstamos del usuario autenticado
	            List<CuentaDTO> cuentasPendientes = prestamoNeg.obtenerEstadosPendientes(idCliente);
	            List<CuentaDTO> cuentasVigentes = prestamoNeg.obtenerEstadosVigentes(idCliente);
	            List<CuentaDTO> datosClientes = prestamoNeg.ObtenerDatosCliente(idCliente);
	            
	            
	            
	            if (cuentasVigentes != null && !cuentasVigentes.isEmpty()) {
	                request.setAttribute("cuentasVigentes", cuentasVigentes);
	            } else {
	                request.setAttribute("error", "No se encontraron cuentas.");
	            }
	            
	            if (cuentasPendientes != null && !cuentasPendientes.isEmpty()) {
	                request.setAttribute("cuentasPendientes", cuentasPendientes);
	            } else {
	                request.setAttribute("error", "No se encontraron cuentas.");
	            }
	           
	        
	            
	            if (datosClientes != null && !datosClientes.isEmpty()) {
	                request.setAttribute("datosClientes", datosClientes);
	            } else {
	                request.setAttribute("error", "No se encontraron datos del Clientes.");
	            }
	           
	            
	       
	            request.getRequestDispatcher("DatosCuentas.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Ocurrió un error al obtener las cuentas del cliente.");
	            request.getRequestDispatcher("DatosCuentas.jsp").forward(request, response);
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
