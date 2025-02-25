package entidadDTO;

import java.math.BigDecimal;  
import java.time.LocalDate;  

public class PrestamoDTO {  
    private int id;  
    private String observaciones;  
    private BigDecimal importe;  
    private int cuotas;  
    private BigDecimal valorCuotas;  
    private String estado;  
    private LocalDate fechaAlta;  

    // Getters y Setters  
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

    public LocalDate getFechaAlta() {  
        return fechaAlta;  
    }  

    public void setFechaAlta(LocalDate fechaAlta) {  
        this.fechaAlta = fechaAlta;  
    }  
}