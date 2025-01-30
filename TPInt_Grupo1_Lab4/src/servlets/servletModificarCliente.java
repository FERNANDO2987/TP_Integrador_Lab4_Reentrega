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
			 
			  System.err.println("Procesando datos de Modificar ");
	            // Obtener parámetros del formulario
	            int id = Integer.parseInt(request.getParameter("id"));
	            String dni = request.getParameter("dni");
	            String cuil = request.getParameter("cuil");
	            String nombre = request.getParameter("nombre");
	            String apellido = request.getParameter("apellido");
	            String sexo = request.getParameter("sexo");
	            int idPais = Integer.parseInt(request.getParameter("pais"));
	            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
	            String direccion = request.getParameter("direccion");
	            int idLocalidad = Integer.parseInt(request.getParameter("localidad"));
	            int idProvincia = Integer.parseInt(request.getParameter("provincia"));
	            String correo = request.getParameter("email");
	            String telefono = request.getParameter("telefono");

	            
	            System.err.println("Procesando datos de Modificar 2");
	            // Convertir fecha de nacimiento a LocalDate
	            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	            // Obtener objetos de Pais, Provincia y Localidad
	            Pais paisNacimiento = paisNeg.ListarPaises().stream()
	                    .filter(p -> p.getId() == idPais)
	                    .findFirst()
	                    .orElse(null);

	            Provincia provincia = provinciaNeg.ListarProvincias().stream()
	                    .filter(p -> p.getId() == idProvincia)
	                    .findFirst()
	                    .orElse(null);

	            Localidad localidad = localidadNeg.ListarLocalidades().stream()
	                    .filter(l -> l.getId() == idLocalidad)
	                    .findFirst()
	                    .orElse(null);

	            // Validar que los objetos no sean nulos
	            if (paisNacimiento == null || provincia == null || localidad == null) {
	                request.setAttribute("mensajeError", "Error: Datos geográficos no válidos.");
	                request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
	                return;
	            }

	            // Crear el objeto Cliente
	            Cliente cliente = new Cliente(
	                    id, dni, cuil, nombre, apellido, sexo,
	                    paisNacimiento, fechaNacimiento, direccion,
	                    localidad, provincia, correo, telefono
	            );

	            // Modificar el cliente
	            boolean modificado = clienteNeg.agregarCliente(cliente);

	            if (modificado) {
	                request.setAttribute("mensajeExito", MENSAJE_EXITO);
	            } else {
	                request.setAttribute("mensajeError", MENSAJE_ERROR);
	            }

	            // Redirigir a la página de modificación
	            request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);

	        } catch (NumberFormatException e) {
	            request.setAttribute("mensajeError", "Error: Formato de número inválido.");
	            request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
	            request.getRequestDispatcher("ModificarCliente.jsp").forward(request, response);
	        }
	    }
		
		
	}


