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
@Table(name = "prestamos")
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numprestamo;

    private String tipoprestamo;
    private Integer monto;
    private Double intereses;
    private String estado;
    private Integer numcuotas;
    private LocalDate diapagoproximacuota;

    @ManyToOne
    @JoinColumn(name = "numdoccliente", referencedColumnName = "numerodocumento")
    private Cliente cliente;

    public Prestamo(String tipoPrestamo, Integer monto, Double intereses, String estado,
            Integer numCuotas, LocalDate diaPagoProximaCuota, Cliente cliente) {
        this.tipoprestamo = tipoPrestamo;
        this.monto = monto;
        this.intereses = intereses;
        this.estado = estado;
        this.numcuotas = numCuotas;
        this.diapagoproximacuota = diaPagoProximaCuota;
        this.cliente = cliente;
    }

    public Prestamo()
    {
        ;
    }

    public Integer getNumPrestamo() {
        return numprestamo;
    }

    public void setNumPrestamo(Integer numPrestamo) {
        this.numprestamo = numPrestamo;
    }

    public String getTipoPrestamo() {
        return tipoprestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoprestamo = tipoPrestamo;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Double getIntereses() {
        return intereses;
    }

    public void setIntereses(Double intereses) {
        this.intereses = intereses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumCuotas() {
        return numcuotas;
    }

    public void setNumCuotas(Integer numCuotas) {
        this.numcuotas = numCuotas;
    }

    public LocalDate getDiaPagoProximaCuota() {
        return diapagoproximacuota;
    }

    public void setDiaPagoProximaCuota(LocalDate diaPagoProximaCuota) {
        this.diapagoproximacuota = diaPagoProximaCuota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

    

    
}