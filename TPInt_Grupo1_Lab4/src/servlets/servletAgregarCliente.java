package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Pais;
import negocio.PaisNeg;
import negocioImpl.PaisNegImpl;

/**
 * Servlet implementation class servletAgregarCliente
 */
@WebServlet("/servletAgregarCliente")
public class servletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 PaisNeg paisNeg = new PaisNegImpl();
    public servletAgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pais> listaPaises = paisNeg.ListarPaises();
		if (listaPaises == null || listaPaises.isEmpty()) {
		    listaPaises = new ArrayList<>(); // Para evitar que sea nula
		}
		request.setAttribute("paises", listaPaises);
		request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
