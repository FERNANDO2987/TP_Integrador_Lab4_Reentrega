package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movimiento {
	private int id;
	private String detalle;
	private BigDecimal importe;
	private TipoMovimiento tipoMovimiento;
	private int nroCuenta;
    
	private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
    
    
    // Constructor vacío
    public Movimiento() {
    	tipoMovimiento = new TipoMovimiento();
    }

    // Constructor con parámetros
	public Movimiento(int id, String detalle, BigDecimal importe, TipoMovimiento tipoMovimiento, int nroCuenta,
			LocalDate createDate, boolean deleted, LocalDate deleteDate) {
		super();
		this.id = id;
		this.detalle = detalle;
		this.importe = importe;
		this.tipoMovimiento = tipoMovimiento;
		this.nroCuenta = nroCuenta;
		this.createDate = createDate;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}

	
	// Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public int getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public boolean getDeleted() {
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
		return "Movimiento [id=" + id + ", detalle=" + detalle + ", importe=" + importe + ", tipoMovimiento="
				+ tipoMovimiento + ", nroCuenta=" + nroCuenta + ", createDate=" + createDate + ", deleted=" + deleted
				+ ", deleteDate=" + deleteDate + "]";
	} 
}