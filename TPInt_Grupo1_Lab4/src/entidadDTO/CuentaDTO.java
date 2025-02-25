package entidadDTO;

import java.math.BigDecimal;  

import java.util.ArrayList;  
import java.util.List;  

public class CuentaDTO {  
    private int nroCuenta;  
    private String cbu;  
    private BigDecimal saldo;  
    private ClienteDTO cliente;  
    private TipoCuentaDTO tipoCuenta;  
    private List<PrestamoDTO> prestamos = new ArrayList<>();  
    private List<MovimientoDTO> movimientos = new ArrayList<>();
    private List<CuotaDTO> cuotas = new ArrayList<>();

    // Getters y Setters  

    public int getNroCuenta() {  
        return nroCuenta;  
    }  

    public void setNroCuenta(int nroCuenta) {  
        this.nroCuenta = nroCuenta;  
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

    public ClienteDTO getCliente() {  
        return cliente;  
    }  

    public void setCliente(ClienteDTO cliente) {  
        this.cliente = cliente;  
    }  

    public TipoCuentaDTO getTipoCuenta() {  
        return tipoCuenta;  
    }  

    public void setTipoCuenta(TipoCuentaDTO tipoCuenta) {  
        this.tipoCuenta = tipoCuenta;  
    }  

    public List<PrestamoDTO> getPrestamos() {  
        return prestamos;  
    }  

    public void setPrestamos(List<PrestamoDTO> prestamos) {  
        this.prestamos = prestamos;  
    }  

    public List<MovimientoDTO> getMovimientos() {  
        return movimientos;  
    }  

    public void setMovimientos(List<MovimientoDTO> movimientos) {  
        this.movimientos = movimientos;  
    }  
    
    
    public List<CuotaDTO> getCuotas() {  
        return cuotas;  
    }  

    public void setCuotas(List<CuotaDTO> cuotas) {  
        this.cuotas = cuotas;  
    } 
}