package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cuota {
	private int id;
	private Prestamo prestamo;
    private int nroCuota;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private boolean estadoPago;
    
    private LocalDate createDate;
    private boolean deleted;
    private LocalDate deleteDate;
    
    
    // Constructor vacío
    public Cuota() {}
    
    // Constructor con parámetros
	public Cuota(int id, Prestamo prestamo, int nroCuota, BigDecimal monto, LocalDateTime fechaPago, boolean estadoPago,
			LocalDate createDate, boolean deleted, LocalDate deleteDate) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.nroCuota = nroCuota;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.estadoPago = estadoPago;
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
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public int getNroCuota() {
		return nroCuota;
	}
	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public LocalDateTime getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}
	public boolean isEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
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
		return "Cuota [id=" + id + ", prestamo=" + prestamo + ", nroCuota=" + nroCuota + ", monto=" + monto
				+ ", fechaPago=" + fechaPago + ", estadoPago=" + estadoPago + ", createDate=" + createDate
				+ ", deleted=" + deleted + ", deleteDate=" + deleteDate + "]";
	}
}
