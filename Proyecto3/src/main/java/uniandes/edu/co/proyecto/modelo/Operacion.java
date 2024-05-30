package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "OperacionesCuenta")
public class Operacion {
    private String tipo;
    private int idpuntoatencion;
    private int valor;
    private int numcuentaprincipal;
    @Id
    private int id;
    private String fecha;
    private Integer numcuentadestino;

    public Operacion() {
    }

    public Operacion(String tipo, int idpuntoatencion, int valor, int numcuentaprincipal, int id, String fecha, Integer numcuentadestino) {
        this.tipo = tipo;
        this.idpuntoatencion = idpuntoatencion;
        this.valor = valor;
        this.numcuentaprincipal = numcuentaprincipal;
        this.id = id;
        this.fecha = fecha;
        this.numcuentadestino = numcuentadestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdpuntoatencion() {
        return idpuntoatencion;
    }

    public void setIdpuntoatencion(int idpuntoatencion) {
        this.idpuntoatencion = idpuntoatencion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getNumcuentaprincipal() {
        return numcuentaprincipal;
    }

    public void setNumcuentaprincipal(int numcuentaprincipal) {
        this.numcuentaprincipal = numcuentaprincipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getNumcuentadestino() {
        return numcuentadestino;
    }

    public void setNumcuentadestino(Integer numcuentadestino) {
        this.numcuentadestino = numcuentadestino;
    }
}
