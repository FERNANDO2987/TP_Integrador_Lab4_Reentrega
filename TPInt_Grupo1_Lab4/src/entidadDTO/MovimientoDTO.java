package entidadDTO;

import java.math.BigDecimal;  

public class MovimientoDTO {  
    private int id;  
    private String detalle;  
    private BigDecimal importe;  
    private int nroCuenta;  
    private TipoMovimientoDTO tipoMovimiento;  

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

    public int getNroCuenta() {  
        return nroCuenta;  
    }  

    public void setNroCuenta(int nroCuenta) {  
        this.nroCuenta = nroCuenta;  
    }  

    public TipoMovimientoDTO getTipoMovimiento() {  
        return tipoMovimiento;  
    }  

    public void setTipoMovimiento(TipoMovimientoDTO tipoMovimiento) {  
        this.tipoMovimiento = tipoMovimiento;  
    }  
}