package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operacionesCuentas")
public class OperacionCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipooperacion;
    private Integer valor;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idpuntoatencion", referencedColumnName = "id")
    private PuntoAtencion idpuntoatencion;

    @ManyToOne
    @JoinColumn(name = "numcuenta", referencedColumnName = "numCuenta")
    private Cuenta cuenta;

    public OperacionCuenta(String tipoOperacion, Integer valor, LocalDate fecha,
            PuntoAtencion puntoAtencion, Cuenta cuenta) {
        this.tipooperacion = tipoOperacion;
        this.valor = valor;
        this.fecha = fecha;
        this.idpuntoatencion = puntoAtencion;
        this.cuenta = cuenta;
    }

    public OperacionCuenta()
    {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoOperacion() {
        return tipooperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipooperacion = tipoOperacion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public PuntoAtencion getPuntoAtencion() {
        return idpuntoatencion;
    }

    public void setPuntoAtencion(PuntoAtencion puntoAtencion) {
        this.idpuntoatencion = puntoAtencion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
}
