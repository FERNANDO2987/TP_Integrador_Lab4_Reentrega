package entidad;

import java.math.BigDecimal;

public class Transferencia {
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private BigDecimal monto;
	private String detalle;
	
	
	public Transferencia() {
		super();
		cuentaOrigen = new Cuenta();
		cuentaDestino = new Cuenta();
	}
	
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
