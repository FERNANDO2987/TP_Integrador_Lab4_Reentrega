package principal;

import negocioImpl.PrestamoNegImpl;

import java.math.BigDecimal;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;


public class Principal {

    public static void main(String[] args) {
    	PrestamoNegImpl negocio = new PrestamoNegImpl();
    	Prestamo p = new Prestamo();
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = new Cuenta();
    	BigDecimal importe = BigDecimal.valueOf(666);
    	
    	cliente.setId(2);
    	cuenta.setNroCuenta(222222);
    	p.setCliente(cliente);
    	p.setCuenta(cuenta);
    	p.setImporte(importe);
    	p.setCuotas(6);
    	p.setObservaciones("Prestamo personal");
    	
    	negocio.AgregarPrestamo(p);
    }
    
}

 