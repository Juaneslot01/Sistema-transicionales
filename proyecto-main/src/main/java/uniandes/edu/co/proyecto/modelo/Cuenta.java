package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numcuenta;
    private String tipocuenta;
    private Integer saldo;
    private String estado;
    private LocalDate fechaultimatransaccion;
    private LocalDate fechacreacion;

    @ManyToOne
    @JoinColumn(name = "numdoccliente", referencedColumnName = "numerodocumento")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idoficina", referencedColumnName = "id")
    private Oficina oficina;

    public Cuenta(String tipoCuenta, Integer saldo, String estado, LocalDate fechaUltimaTransaccion, Cliente cliente, Oficina oficina) {
        this.tipocuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaultimatransaccion = fechaUltimaTransaccion;
        this.cliente = cliente;
        this.oficina = oficina;
        fechacreacion = LocalDate.now();
    }

    public Cuenta()
    {
        ;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Integer getNumCuenta() {
        return numcuenta;
    }

    public void setNumCuenta(Integer numCuenta) {
        this.numcuenta = numCuenta;
    }

    public String getTipoCuenta() {
        return tipocuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipocuenta = tipoCuenta;
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

    public LocalDate getFechaUltimaTransaccion() {
        return fechaultimatransaccion;
    }

    public void setFechaUltimaTransaccion(LocalDate fechaUltimaTransaccion) {
        this.fechaultimatransaccion = fechaUltimaTransaccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechacreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechacreacion = fechaCreacion;
    }
}