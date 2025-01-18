package principal;


import entidad.Usuario;
import negocioImpl.UsuarioNegImpl;

public class Principal {

    public static void main(String[] args) {

        
        // Crear una instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setUsuario("nuevoUsuario");
        usuario.setPassword("contraseña123");

        // Crear una instancia de UsuarioNegImpl (o UsuarioDaoImpl si es necesario)
        UsuarioNegImpl usuarioDao = new UsuarioNegImpl();
        
        // Llamar al método AgregarUsuario
        boolean resultado = usuarioDao.AgregarUsuario(usuario);

        // Imprimir el resultado
        if (resultado) {
            System.out.println("Usuario agregado exitosamente.");
        } else {
            System.out.println("Hubo un error al agregar el usuario.");
        }
        
    
    }
}
