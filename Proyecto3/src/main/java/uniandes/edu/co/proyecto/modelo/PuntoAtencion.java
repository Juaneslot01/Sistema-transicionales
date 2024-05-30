package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PuntoAtencion {
    public int idPunto;
    private String ubicacion;
    private String tipo;
    private int idOficina;

    public PuntoAtencion() {
    }

    public PuntoAtencion(int id, String ubicacion, String tipo, int idOficina) {
        this.idPunto = id;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.idOficina = idOficina;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public int getid() {
        return idPunto;
    }

    public void setid(int id) {
        this.idPunto = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
