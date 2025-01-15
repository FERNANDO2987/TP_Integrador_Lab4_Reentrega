package principal;

import datosImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Usuario;

public class Principal {

    public static void main(String[] args) {
        // Crear instancia del DAO
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

        // Crear un cliente para asociarlo al usuario
        Cliente cliente = new Cliente();
        cliente.setId(1); // ID del cliente existente en la base de datos

        // Crear un usuario para insertar o actualizar
        Usuario usuario = new Usuario();
        usuario.setCliente(cliente);     // Asociar cliente al usuario
        usuario.setUsuario("PalaciosFernando"); // Nombre de usuario
        usuario.setPassword("password12333333333333"); // Contraseña
        usuario.setAdmin(true);          // Es administrador

        // Llamar al método para insertar o actualizar
        boolean resultado = usuarioDao.insertarOActualizarUsuario(usuario);

        // Mostrar el resultado
        if (resultado) {
            System.out.println("El usuario fue insertado o actualizado correctamente.");
        } else {
            System.out.println("Error al insertar o actualizar el usuario.");
        }
    }
}
