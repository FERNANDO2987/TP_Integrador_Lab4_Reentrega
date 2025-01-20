package principal;


import java.util.List;

import entidad.Cliente;

import negocioImpl.ClienteNegImpl;


public class Principal {

    public static void main(String[] args) {

        
    
        ClienteNegImpl clienteNeg = new ClienteNegImpl();
        
        List<Cliente> clientes = clienteNeg.ListarClientes();

        // Verificar e imprimir el resultado
        if (clientes != null && !clientes.isEmpty()) {
            System.out.println("Lista de clientes obtenida exitosamente:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No se encontraron clientes o hubo un error.");
        }
    
    }
}
