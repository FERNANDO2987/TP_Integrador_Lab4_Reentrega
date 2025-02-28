package entidadDTO;

import java.math.BigDecimal;  
import java.time.LocalDate;
import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;  

public class PrestamoDTO {  

	   private int id;
	    private String observaciones;
	    private Cliente cliente;
	    private Cuenta cuenta;
	    private LocalDate fechaAlta;
	    private BigDecimal importe;
	    private int cuotas;
	    private BigDecimal valorCuotas;
	    private String estado;
	    
	    public PrestamoDTO() {}
	    
	    
		public PrestamoDTO(int id, String observaciones, Cliente cliente, Cuenta cuenta, LocalDate fechaAlta,
				BigDecimal importe, int cuotas, BigDecimal valorCuotas, String estado, List<Movimiento> movimientos) {
			super();
			this.id = id;
			this.observaciones = observaciones;
			this.cliente = cliente;
			this.cuenta = cuenta;
			this.fechaAlta = fechaAlta;
			this.importe = importe;
			this.cuotas = cuotas;
			this.valorCuotas = valorCuotas;
			this.estado = estado;
			
		}
	

	  

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getObservaciones() {
			return observaciones;
		}

		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Cuenta getCuenta() {
			return cuenta;
		}

		public void setCuenta(Cuenta cuenta) {
			this.cuenta = cuenta;
		}

		public LocalDate getFechaAlta() {
			return fechaAlta;
		}

		public void setFechaAlta(LocalDate fechaAlta) {
			this.fechaAlta = fechaAlta;
		}

		public BigDecimal getImporte() {
			return importe;
		}

		public void setImporte(BigDecimal importe) {
			this.importe = importe;
		}

		public int getCuotas() {
			return cuotas;
		}

		public void setCuotas(int cuotas) {
			this.cuotas = cuotas;
		}

		public BigDecimal getValorCuotas() {
			return valorCuotas;
		}

		public void setValorCuotas(BigDecimal valorCuotas) {
			this.valorCuotas = valorCuotas;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		@Override
		public String toString() {
			return "PrestamoDTO [id=" + id + ", observaciones=" + observaciones + ", cliente=" + cliente + ", cuenta="
					+ cuenta + ", fechaAlta=" + fechaAlta + ", importe=" + importe + ", cuotas=" + cuotas
					+ ", valorCuotas=" + valorCuotas + ", estado=" + estado + ", movimientos=" +  "]";
		}


	    
		
	    
	
}