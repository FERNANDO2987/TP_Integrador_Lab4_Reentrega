package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import negocio.ClienteNeg;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciasNeg;
import negocioImpl.ClienteNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;

/**
 * Servlet implementation class servletModificarCliente
 */
@WebServlet("/servletModificarCliente")
public class servletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	 PaisNeg paisNeg = new PaisNegImpl();
	 ProvinciasNeg provinciaNeg = new ProvinciaNegImpl();
	 LocalidadNeg localidadNeg = new LocalidadNegImpl();
	 
	 ClienteNeg clienteNeg = new ClienteNegImpl();
	 UsuarioDao usuarioDao = new UsuarioDaoImpl();
	 private static final String MENSAJE_EXITO = "Cliente y Usuario agregado exitosamente, recibirá por email usuario y password.";
	    private static final String MENSAJE_ERROR = "Error al agregar el cliente.";
	
	
    public servletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	
	    	
	        // Obtener los parámetros del formulario
	    	  int id = Integer.parseInt(request.getParameter("id"));
	        String dni = request.getParameter("dni");
	        String cuil = request.getParameter("cuil");
	        String nombre = request.getParameter("nombre");
	        String apellido = request.getParameter("apellido");
	        String sexo = request.getParameter("sexo");
	        String paisNacimientoNombre = request.getParameter("pais");
	        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
	        String direccion = request.getParameter("direccion");
	        String localidadNombre = request.getParameter("localidad");
	        String provinciaStr = request.getParameter("provincia");
	        String correo = request.getParameter("email");
	        String telefono = request.getParameter("telefono");

	     
	     // Agregar los parámetros al request para mantener los valores si hay error
	        request.setAttribute("dni", dni);
	        request.setAttribute("cuil", cuil);
	        request.setAttribute("nombre", nombre);
	        request.setAttribute("apellido", apellido);
	        request.setAttribute("sexo", sexo);
	        request.setAttribute("pais", paisNacimientoNombre);
	        request.setAttribute("fechaNacimiento", fechaNacimientoStr);
	        request.setAttribute("direccion", direccion);
	        request.setAttribute("localidad", localidadNombre);
	        request.setAttribute("provincia", provinciaStr);
	        request.setAttribute("email", correo);
	        request.setAttribute("telefono", telefono);
	        
	      

	        // Convertir fechaNacimientoStr de String a LocalDate
	        LocalDate fechaNacimiento;
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Formato de fecha inválido.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }

	        int idPais;
	        try {
	            idPais = Integer.parseInt(paisNacimientoNombre);
	        } catch (NumberFormatException e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: ID del país inválido.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }

	        int idProvincia;
	        try {
	            idProvincia = Integer.parseInt(provinciaStr);
	        } catch (NumberFormatException e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: ID de la provincia inválido.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }
	        
	        int idLocalidad;
	        try {
	        	idLocalidad = Integer.parseInt(localidadNombre);
	        } catch (NumberFormatException e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: ID de la localidad inválido.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }

	        // Obtener el país de nacimiento por ID
	        List<Pais> paises = paisNeg.ListarPaises();
	        Pais paisNacimiento = paises.stream()
	            .filter(p -> p.getId() == idPais)
	            .findFirst()
	            .orElse(null);

	        if (paisNacimiento == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: País de nacimiento no encontrado.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }

	        // Obtener la provincia por ID
	        List<Provincia> provincias = provinciaNeg.ListarProvincias();
	        Provincia provincia = provincias.stream()
	            .filter(p -> p.getId() == idProvincia)
	            .findFirst()
	            .orElse(null);

	        if (provincia == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: Provincia no encontrada.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }

	       
	        
	        List<Localidad> localidades = localidadNeg.ListarLocalidades();
	        Localidad localidad = localidades.stream()
	            .filter(p -> p.getId() == idLocalidad)
	            .findFirst()
	            .orElse(null);

	        if (localidad == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            request.setAttribute("mensajeError", "Error: localidad no encontrado.");
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	            return;
	        }


	        // Crear el objeto Cliente
	        Cliente cliente = new Cliente(
	            id, dni, cuil, nombre, apellido, sexo,
	            paisNacimiento, fechaNacimiento, direccion,
	            localidad, provincia, correo, telefono
	        );

	      
	        
	        Map<String, String> errores = clienteNeg.AgregarCliente(cliente);  

	        // Manejo de errores  
	        if (!errores.isEmpty()) {  
	            String mensajeError = String.join(", ", errores.values());  
	            request.setAttribute("mensajeError", mensajeError);  
	            request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);  
	            return;  
	        }  

	        // Si no hay errores, ya el cliente se ha guardado en el método AgregarCliente  
	        // Mensaje de éxito  
	        request.setAttribute("mensajeExito", MENSAJE_EXITO);  
	     // Limpiar los campos del formulario eliminando los atributos del request  
	        request.removeAttribute("dni");
	        request.removeAttribute("cuil");
	        request.removeAttribute("nombre");
	        request.removeAttribute("apellido");
	        request.removeAttribute("sexo");
	        request.removeAttribute("pais");
	        request.removeAttribute("fechaNacimiento");
	        request.removeAttribute("direccion");
	        request.removeAttribute("localidad");
	        request.removeAttribute("provincia");
	        request.removeAttribute("email");
	        request.removeAttribute("telefono");
	        request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);  



	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensajeError", "Error al procesar la solicitud: " + e.getMessage());
	        request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	    }
	}
		
		
	}


