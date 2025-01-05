package entidad;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Prestamo {
	private long id;
	private String observaciones;
	private Cliente cliente;
	private Cuenta cuenta;
	private LocalDate fechaAlta;
	private BigDecimal importe;
	private int cuotas;
	private BigDecimal valorCuotas;
	private String estado;
	
	private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
    
}
