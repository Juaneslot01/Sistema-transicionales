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
@Table(name = "OperacionesPrestamo")
public class OperacionPrestamo {

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
    @JoinColumn(name = "numprestamo", referencedColumnName = "numPrestamo")
    private Prestamo prestamo;

    public OperacionPrestamo(String tipoOperacion, Integer valor, LocalDate fecha, PuntoAtencion puntoAtencion,
            Prestamo prestamo) {
        this.tipooperacion = tipoOperacion;
        this.valor = valor;
        this.fecha = fecha;
        this.idpuntoatencion = puntoAtencion;
        this.prestamo = prestamo;
    }

    public OperacionPrestamo()
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

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
