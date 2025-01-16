package principal;

import java.util.List;

import datosImpl.UsuarioDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocioImpl.UsuarioNegImpl;

public class Principal {

    public static void main(String[] args) {
        // Crear instancia del DAO
        UsuarioNegImpl usuarioDao = new UsuarioNegImpl();
        
        List<Usuario> usuarios = usuarioDao.ListarUsuarios();

        // Iterar y mostrar los usuarios obtenidos
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Usuario: " + usuario.getUsuario());
                System.out.println("Password: " + usuario.getPassword());
                System.out.println("Admin: " + usuario.isAdmin());

                if (usuario.getCliente() != null) {
                    System.out.println("ID Cliente: " + usuario.getCliente().getId());
                }
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("No se encontraron usuarios.");
        }

    }
}
