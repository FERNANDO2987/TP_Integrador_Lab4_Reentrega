package servlets;

import java.io.IOException;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Usuario;

import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegImpl;


/**
 * Servlet implementation class servletPagarPrestamo
 */
@WebServlet("/servletPagarPrestamo")
public class servletPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	CuentaNeg cuentaNeg = new CuentaNegImpl();

	 private static final String MENSAJE_EXITO = "Cuenta Exitosa.";
	    private static final String MENSAJE_ERROR = "Error al obtener cuentas.";

    public servletPagarPrestamo() {
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

        String idPrestamoParam = request.getParameter("idPrestamo");
        int idPrestamo;

        try {
            idPrestamo = Integer.parseInt(idPrestamoParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID inválido.");
            request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
            return;
        }

        // Obtener las cuentas del cliente
        List<Cuenta> cuentas = cuentaNeg.leerLasCuentasDelCliente(idCliente);

        System.out.println("ID del usuario: " + idCliente);

        
        Prestamo prestamo = prestamoNeg.ObtenerPrestamoPorId(idPrestamo);

        if (prestamo != null) {
        	
        	  System.out.println("ID del usuario: " + usuario.getId());
            request.setAttribute("prestamo", prestamo);
        } else {
            request.setAttribute("error", "No se encontraron datos del préstamo.");
        }
        
        
        if (cuentas != null && !cuentas.isEmpty()) {
            // Establecer la lista de prestamos como un atributo en el request
            request.setAttribute("cuentas", cuentas);
        } else {
            // Si no hay prestamos, establecer un mensaje de error
            request.setAttribute("error", "No se encontraron prestamos.");
        }

 

        request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
    }


    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 }
	

}


