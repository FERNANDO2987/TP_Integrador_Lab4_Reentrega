package principal;


import java.time.LocalDate;

import datosImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import negocioImpl.ClienteNegImpl;


public class Principal {

    public static void main(String[] args) {

    	
    	  // Crear una instancia de ClienteNegImpl para llamar al método
        ClienteNegImpl clienteNegImpl = new ClienteNegImpl();
        ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();

        // Crear una instancia de Cliente con los datos que deseas probar
        Cliente cliente = new Cliente();
        
        cliente.setId(2);
        cliente.setDni("13115225");
        cliente.setCuil("20-13115225-9");
        cliente.setNombre("Juan CARLOS");
        cliente.setApellido("Pérez");
        cliente.setSexo("Masculino");

        // Crear objetos para el País, Localidad y Provincia (puedes ajustarlos a los datos de tu base)
        Pais pais = new Pais();
        pais.setId(1);  // Suponiendo que el ID de País es 1
        
        Provincia provincia = new Provincia();
        provincia.setId(2);  // Suponiendo que el ID de Provincia es 5
        
        Localidad localidad = new Localidad();
        localidad.setId(2);  // Suponiendo que el ID de Localidad es 5

        // Validar que los objetos no sean nulos
        if (pais == null || provincia == null || localidad == null) {
            System.out.println("Alguno de los valores de país, provincia o localidad es nulo.");
            return; // Detener la ejecución si alguno es nulo
        }

        // Asignar objetos de País, Provincia y Localidad al cliente
        cliente.setPaisNacimiento(pais);
        cliente.setProvincia(provincia);
        cliente.setLocalidad(localidad);

        // Asignar más detalles
        cliente.setFechaNacimiento(LocalDate.of(1990, 1, 1));  // Fecha de nacimiento
        cliente.setDireccion("Av. Siempre Viva 123");
        cliente.setCorreo("juan.perez23@email.com");
        cliente.setTelefono("11223356688");

        // Validar datos requeridos
        if (cliente.getDni() == null || cliente.getDni().isEmpty()) {
            System.out.println("DNI es obligatorio.");
            return;
        }
        if (cliente.getCorreo() == null || cliente.getCorreo().isEmpty()) {
            System.out.println("Correo es obligatorio.");
            return;
        }
        
        // Llamar al método de agregar o modificar cliente
        boolean resultado = clienteDaoImpl.agregarOmodifcarCliente(cliente);

        // Mostrar el resultado
        if (resultado) {
            System.out.println("Cliente agregado o modificado exitosamente.");
        } else {
            System.out.println("Hubo un error al agregar o modificar el cliente.");
        }
    
    }
}
