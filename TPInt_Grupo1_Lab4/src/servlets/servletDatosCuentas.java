package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidadDTO.PrestamoDTO;
import negocio.PrestamoNeg;
import negocioImpl.PrestamoNegImpl;


@WebServlet("/servletDatosCuentas")
public class servletDatosCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
	 private static final String MENSAJE_EXITO = "Prestamos Disponible.";
	    private static final String MENSAJE_ERROR = "No hay prestamos disponibles.";
	
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
	           // List<CuentaDTO> cuentasPendientes = prestamoNeg.obtenerEstadosPendientes(idCliente);
	            List<PrestamoDTO> prestamosAprobados = prestamoNeg.ListarPrestamosPorEstadosAprobados(idCliente);
	            List<PrestamoDTO> prestamosPendientes = prestamoNeg.ListarPrestamosPorEstadosPendientes(idCliente);
	            List<PrestamoDTO> datosPrestamos = prestamoNeg.ListarPrestamosPorCliente(idCliente);
	            
	            
	            if (prestamosAprobados != null && !prestamosAprobados.isEmpty()) {
	                request.setAttribute("prestamosAprobados", prestamosAprobados);
	                  
	            } else {
	                request.setAttribute("error", "No se encontraron prestamos aprobados.");
	           
	                request.setAttribute("mensajeError", MENSAJE_EXITO); 
	            }
	            
	            if (prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
	                request.setAttribute("prestamosPendientes", prestamosPendientes);
	                   
	            } else {
	                request.setAttribute("error", "No se encontraron prestamos pendientes.");
	                request.setAttribute("mensajeError", MENSAJE_EXITO);  
	            }
	           
	        
	            
	            if (datosPrestamos != null && !datosPrestamos.isEmpty()) {
	                request.setAttribute("datosPrestamos", datosPrestamos);
	            
	            } else {
	                request.setAttribute("error", "No se encontraron prestamos.");
	                request.setAttribute("mensajeError", MENSAJE_EXITO);  
	            }
	           
	            
	       
	            request.getRequestDispatcher("DatosCuentas.jsp").forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Ocurrió un error al obtener las cuentas del cliente.");
	            request.getRequestDispatcher("DatosCuentas.jsp").forward(request, response);
	            request.setAttribute("mensajeError", MENSAJE_EXITO);
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
