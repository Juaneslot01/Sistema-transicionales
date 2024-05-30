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
@Table(name = "OperacionesTransferencia")
public class OperacionTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer valor;
    private LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "numcuentaorigen", referencedColumnName = "numCuenta")
    private Cuenta cuentaorigen;

    @ManyToOne
    @JoinColumn(name = "numcuentadestino", referencedColumnName = "numCuenta")
    private Cuenta cuentadestino;

    @ManyToOne
    @JoinColumn(name = "idpuntoatencion", referencedColumnName = "id")
    private PuntoAtencion idpuntoatencion;

    public OperacionTransferencia(Integer valor, LocalDate fecha, Cuenta cuentaOrigen, Cuenta cuentaDestino,
            PuntoAtencion puntoAtencion) {
        this.valor = valor;
        this.fecha = fecha;
        this.cuentaorigen = cuentaOrigen;
        this.cuentadestino = cuentaDestino;
        this.idpuntoatencion = puntoAtencion;
    }

    public OperacionTransferencia()
    {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Cuenta getCuentaOrigen() {
        return cuentaorigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaorigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentadestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentadestino = cuentaDestino;
    }

    public PuntoAtencion getPuntoAtencion() {
        return idpuntoatencion;
    }

    public void setPuntoAtencion(PuntoAtencion puntoAtencion) {
        this.idpuntoatencion = puntoAtencion;
    }


    
    



    
}
