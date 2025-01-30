package principal;




import negocioImpl.ClienteNegImpl;



public class Principal {

    public static void main(String[] args) {

    	 // Crear una instancia de ClienteNegImpl
        ClienteNegImpl clienteNegImpl = new ClienteNegImpl();

        // Crear un Cliente con un DNI para probar
        int id = 1;  // Asegúrate de que este DNI exista en la base de datos para la prueba
        
        // Llamar al método existeDni
        boolean existe = clienteNegImpl.EliminarCliente(id);

        // Imprimir el resultado
        if (existe) {
            System.out.println("El ID " + id + " YA FUE ELIMINADO.");
        } else {
            System.out.println("El ID " + id + " NO SE PUDO ELIMINAR.");
        }
        
       
    
    }
}
