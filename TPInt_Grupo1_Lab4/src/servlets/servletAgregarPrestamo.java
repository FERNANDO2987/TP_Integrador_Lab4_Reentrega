package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocioImpl.PrestamoNegImpl;



/**
 * Servlet implementation class servletAgregarPrestamo
 */
@WebServlet("/servletAgregarPrestamo")
public class servletAgregarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrestamoNegImpl neg = new PrestamoNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Cuenta cuenta = new Cuenta();
			Cliente cliente = new Cliente();
			
			cuenta.setNroCuenta( request.getParameter("cuentaDestino") != null ? Integer.parseInt(request.getParameter("cuentaDestino")) : -1);
			cliente.setId(request.getParameter("usuarioID") != null ? Integer.parseInt(request.getParameter("usuarioID")) : -1);
			String imp = request.getParameter("monto");
			String cuo = request.getParameter("cuotas");

			imp = imp.trim();
			cuo = cuo.trim();
			
			// Validar campos obligatorios
			if (imp == null || imp.isEmpty() || 
				cuo == null || cuo.isEmpty() ||
				cliente.getId() == -1 || 
				cuenta.getNroCuenta() == -1)
			{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Todos los campos obligatorios deben estar presentes.");
                return;
			}
			// convertir y llamar datos de session
			int cuotas = Integer.parseInt(cuo);
			BigDecimal importe = new BigDecimal(imp);

			
			Prestamo p = new Prestamo();
			p.setCliente(cliente);
			p.setCuenta(cuenta);
			p.setCuotas(cuotas);
			p.setImporte(importe);
			boolean estado = neg.AgregarPrestamo(p); //insertar prestamo
			
			// Respuesta al prestamo
            if (estado) {  
                request.setAttribute("successMessage", "Prestamo agregado exitosamente.");  
            } else {  
                request.setAttribute("errorMessage", "Error al agregar Prestamo.");  
            }
         // Redirigir al JSP
            request.getRequestDispatcher("SolicitarPrestamos.jsp").forward(request, response);
		}
		catch(Exception e){
			request.setAttribute("errorMessage", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("SolicitarPrestamos.jsp").forward(request, response); 
		}
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
