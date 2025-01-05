package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuenta {
	private int nroCuenta;
    private Cliente cliente;
    private TipoCuenta tipoCuenta;
    private String cbu;
    private BigDecimal saldo;

    private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
}