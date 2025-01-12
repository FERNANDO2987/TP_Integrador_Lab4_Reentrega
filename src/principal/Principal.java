package principal;

import java.util.List;

import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidad.Cliente;

public class Principal {

	public static void main(String[] args) {
		Cliente cliente;
		ClienteDao clienteDao = new ClienteDaoImpl();
		cliente = clienteDao.leerUnCliente(6);
		System.out.println("antes de la modificacion: "+ cliente.toString());
		cliente.setNombre("Alejo");
		clienteDao.modificarCliente(cliente);
		cliente = clienteDao.leerUnCliente(6);
		System.out.println("despues de la modificacion: "+ cliente.toString());
		

	}

}
