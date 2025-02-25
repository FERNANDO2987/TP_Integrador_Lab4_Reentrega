package entidadDTO;

import java.math.BigDecimal;  
import java.time.LocalDateTime;  

public class CuotaDTO {  
    private int id;  
    private int nroCuota;  
    private BigDecimal monto;  
    private LocalDateTime fechaPago;  
    private boolean estadoPago;  

    // Getters y Setters  
    public int getId() {  
        return id;  
    }  

    public void setId(int id) {  
        this.id = id;  
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
}
