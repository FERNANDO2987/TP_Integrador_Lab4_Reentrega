package servlets;

import java.io.IOException;
import java.util.Collections;
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


/**
 * Servlet implementation class servletPagarPrestamo
 */
@WebServlet("/servletPagarPrestamo")
public class servletPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
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

        try {
            List<CuentaDTO> datosClientes = prestamoNeg.ObtenerDatosCliente(idCliente);

            if (datosClientes != null && !datosClientes.isEmpty()) {
                // Filtrar solo un préstamo por cuenta
                for (CuentaDTO cuenta : datosClientes) {
                    if (!cuenta.getPrestamos().isEmpty()) {
                        cuenta.setPrestamos(Collections.singletonList(cuenta.getPrestamos().get(0)));
                    }
                }

                request.setAttribute("datosClientes", datosClientes);
            } else {
                request.setAttribute("error", "No se encontraron datos del cliente.");
            }

            request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al obtener las cuentas del cliente.");
            request.getRequestDispatcher("PagarPrestamo.jsp").forward(request, response);
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
