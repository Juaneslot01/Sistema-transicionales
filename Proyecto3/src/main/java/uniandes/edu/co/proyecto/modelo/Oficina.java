package uniandes.edu.co.proyecto.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Oficinas")
public class Oficina {
    private String nombre;
    private String direccion;
    @Id
    public int id;
    private String nombregerente;
    private int numdocgerente;
    private List<PuntoAtencion> puntosdeatencion;

    public Oficina() {
    }

    public Oficina(String nombre, String direccion, int id, String nombregerente, int numdocgerente, List<PuntoAtencion> puntosdeatencion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.id = id;
        this.nombregerente = nombregerente;
        this.numdocgerente = numdocgerente;
        this.puntosdeatencion = puntosdeatencion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getNombregerente() {
        return nombregerente;
    }

    public void setNombregerente(String nombregerente) {
        this.nombregerente = nombregerente;
    }

    public int getNumdocgerente() {
        return numdocgerente;
    }

    public void setNumdocgerente(int numdocgerente) {
        this.numdocgerente = numdocgerente;
    }

    public List<PuntoAtencion> getPuntosdeatencion() {
        return puntosdeatencion;
    }

    public void setPuntosdeatencion(List<PuntoAtencion> puntosdeatencion) {
        this.puntosdeatencion = puntosdeatencion;
    }
}
