package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Usuario;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegImpl;



/**
 * Servlet implementation class servletAgregarPrestamo
 */
@WebServlet("/servletAgregarPrestamo")
public class servletAgregarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PrestamoNegImpl neg = new PrestamoNegImpl();
	private CuentaNegImpl cuentaNeg = new CuentaNegImpl();
       
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
		
		HttpSession session = request.getSession(false); 
		Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
		
		if (usuario == null) {
			response.sendRedirect("login.jsp"); // 
			return;
		}
		
		int id_cliente = usuario.getCliente().getId();
		List<Cuenta> listaCuentas = cuentaNeg.leerLasCuentasDelCliente(id_cliente);
		
		request.setAttribute("listaDeMisCuentas", listaCuentas);
		
		 // Limpiar el formulario (vaciar los campos)
		request.setAttribute("cuentaDestino", "");
		request.setAttribute("monto", "");
	    request.setAttribute("cuotas", "");
	    request.setAttribute("tipoPrestamo", "");
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("SolicitarPrestamo.jsp");
	    dispatcher.forward(request, response); // Enviar datos a la vista
		/*
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
            request.getRequestDispatcher("SolicitarPrestamo.jsp").forward(request, response);
		}
		catch(Exception e){
			request.setAttribute("errorMessage", "Error inesperado: " + e.getMessage());  
            e.printStackTrace();  
            request.getRequestDispatcher("SolicitarPrestamo.jsp").forward(request, response); 
		}
		 * */
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        Cuenta cuenta = new Cuenta();
	        Cliente cliente = new Cliente();

	        // Validar y obtener parámetros
	        String cuentaDestinoParam = request.getParameter("cuentaDestino");
	        int nroCuenta = (cuentaDestinoParam != null && !cuentaDestinoParam.trim().isEmpty()) ? Integer.parseInt(cuentaDestinoParam) : -1;
	        cuenta.setNroCuenta(nroCuenta);

	        String usuarioIDParam = request.getParameter("usuarioID");
	        int usuarioID = (usuarioIDParam != null && !usuarioIDParam.trim().isEmpty()) ? Integer.parseInt(usuarioIDParam) : -1;
	        cliente.setId(usuarioID);

	        String tipoPrestamo = request.getParameter("tipoPrestamo");
	        String imp = request.getParameter("monto");
	        String cuo = request.getParameter("cuotas");

	        // Validar campos obligatorios
	        if (imp == null || imp.trim().isEmpty() || 
	            cuo == null || cuo.trim().isEmpty() || 
	            tipoPrestamo == null || tipoPrestamo.trim().isEmpty() || 
	            cliente.getId() == -1 || cuenta.getNroCuenta() == -1) {
	        	
	        	request.getSession().setAttribute("errorMessage", "Todos los camposson obligatorios.");
	            request.getRequestDispatcher("pagina.jsp").forward(request, response);
	            return;
	        }
	        
	        if (!imp.trim().matches("\\d+(\\.\\d+)?") || !cuo.trim().matches("^(100|[1-9]\\d?)$"))
	        {
	        	request.getSession().setAttribute("errorMessage", "Solo ingresar números");
	        	request.setAttribute("monto", "");
	        	request.setAttribute("cuotas", "");
	            request.getRequestDispatcher("pagina.jsp").forward(request, response);
	            return;
	        }
	        // Convertir datos
	        try {
	            int cuotas = Integer.parseInt(cuo);
	            BigDecimal importe = new BigDecimal(imp);

	            // Crear el prestamo
	            Prestamo p = new Prestamo();
	            p.setCliente(cliente);
	            p.setCuenta(cuenta);
	            p.setCuotas(cuotas);
	            p.setImporte(importe);
	            p.setObservaciones(tipoPrestamo);

	            // Insertar en la base de datos
	            boolean estado = neg.AgregarPrestamo(p);

	            // Definir mensajes de éxito/error
	            if (estado) {
	            	request.getSession().setAttribute("successMessage", "Solicitud realizada correctamente.");
	            } else {
	            	request.getSession().setAttribute("errorMessage", "Error al solicitar prestamo.");
	            }
	        } catch (NumberFormatException e) {
	        	request.getSession().setAttribute("errorMessage", "Error al solicitar prestamo.");
	        }

	        // Volver a cargar la página con los mensajes
	        request.getRequestDispatcher("SolicitarPrestamo.jsp").forward(request, response);

	    } catch (Exception e) {
	    	request.getSession().setAttribute("errorMessage", "Error al solicitar prestamo." + e.getMessage());
	        e.printStackTrace();
	        request.getRequestDispatcher("pagina.jsp").forward(request, response);
	    }

	}


}
