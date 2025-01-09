package principal;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;

public class Principal {

	public static void main(String[] args) {
		Cliente cliente;
		ClienteDao clienteDao = new ClienteDaoImpl();
		cliente = clienteDao.leerUnCliente(1);
		System.out.println(cliente.toString());

	}

}
