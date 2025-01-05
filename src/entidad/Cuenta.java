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
    
    
    // Constructor 
	public Cuenta(int nroCuenta, Cliente cliente, TipoCuenta tipoCuenta, String cbu, BigDecimal saldo,
			LocalDate createDate, boolean deleted, LocalDate deleteDate) {
		super();
		this.nroCuenta = nroCuenta;
		this.cliente = cliente;
		this.tipoCuenta = tipoCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.createDate = createDate;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}
	
	
	// Getters y Setters
	public int getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public LocalDate getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(LocalDate deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	
	// ToString
	@Override
	public String toString() {
		return "Cuenta [nroCuenta=" + nroCuenta + ", cliente=" + cliente + ", tipoCuenta=" + tipoCuenta + ", cbu=" + cbu
				+ ", saldo=" + saldo + ", createDate=" + createDate + ", deleted=" + deleted + ", deleteDate="
				+ deleteDate + "]";
	}
}