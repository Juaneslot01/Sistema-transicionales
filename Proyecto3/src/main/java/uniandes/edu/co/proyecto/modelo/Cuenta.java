package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.time.LocalDate;


public class Cuenta implements Serializable {
    private Integer numcuenta;
    private String tipocuenta;
    private Integer saldo;
    private String estado;
    private LocalDate fechaultimatransaccion;
    private LocalDate fechacreacion;
    private Integer id_oficina;
    private Integer id_cliente;

    public Cuenta() {
    }

    public Cuenta(Integer numcuenta, String tipocuenta, Integer saldo, String estado, LocalDate fechaultimatransaccion, LocalDate fechacreacion, Integer id_oficina, Integer id_cliente) {
        this.numcuenta = numcuenta;
        this.tipocuenta = tipocuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaultimatransaccion = fechaultimatransaccion;
        this.fechacreacion = fechacreacion;
        this.id_oficina = id_oficina;
        this.id_cliente = id_cliente;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(Integer numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaultimatransaccion() {
        return fechaultimatransaccion;
    }

    public void setFechaultimatransaccion(LocalDate fechaultimatransaccion) {
        this.fechaultimatransaccion = fechaultimatransaccion;
    }

    public LocalDate getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(LocalDate fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Integer getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(Integer id_oficina) {
        this.id_oficina = id_oficina;
    }
}
