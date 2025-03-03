package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
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
            // Obtener los préstamos del usuario autenticado
            List<Prestamo> prestamosAprobados = prestamoNeg.ListarPrestamosPorClientesAprobados(idCliente);
            List<Prestamo> prestamosPendientes = prestamoNeg.ListarPrestamosPorClientesPendientes(idCliente);
            List<Prestamo> datosPrestamos = prestamoNeg.ListarPrestamosDeClientesPorEstados(idCliente);

            // Limpiar mensajes previos
            request.removeAttribute("mensajeExito");
            request.removeAttribute("mensajeError");
            request.removeAttribute("error");
            
            // Verificar préstamos aprobados
            if (prestamosAprobados != null && !prestamosAprobados.isEmpty()) {
                request.setAttribute("prestamosAprobados", prestamosAprobados);
            } else {
                request.setAttribute("error", "No se encontraron prestamos aprobados.");
                request.setAttribute("mensajeError", MENSAJE_ERROR);
            }
            
            // Verificar préstamos pendientes
            if (prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
                request.setAttribute("prestamosPendientes", prestamosPendientes);
            } else {
                request.setAttribute("error", "No se encontraron prestamos pendientes.");
                request.setAttribute("mensajeError", MENSAJE_ERROR);
            }

            // Verificar otros préstamos
            if (datosPrestamos != null && !datosPrestamos.isEmpty()) {
                request.setAttribute("datosPrestamos", datosPrestamos);
            } else {
                request.setAttribute("error", "No se encontraron prestamos.");
                request.setAttribute("mensajeError", MENSAJE_ERROR);
            }
            
            // Si se encontraron préstamos de algún tipo
            if (prestamosAprobados != null && !prestamosAprobados.isEmpty() || prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
                request.setAttribute("mensajeExito", MENSAJE_EXITO);
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
