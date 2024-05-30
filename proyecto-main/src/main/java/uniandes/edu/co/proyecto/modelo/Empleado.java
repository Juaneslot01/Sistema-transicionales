package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
    private String tipodocumento;
    @Id
    private Integer numerodocumento;
    private String nombre;
    private String nacionalidad;
    private String direccionfisica;
    private String direccionelectronica;
    private String telefono;
    private String ciudad;
    private String departamento;
    private Integer codigopostal;
    private String password;
    private String cargo;

    @OneToOne
    @JoinColumn(name = "idpuntoatencion", referencedColumnName = "id")
    private PuntoAtencion puntoAtencion;
  

    public Empleado(String tipoDocumento, Integer numeroDocumento, String nombre, String nacionalidad, String direccionFisica,
            String direccionElectronica, String telefono, String ciudad, String departamento, Integer codigoPostal,
            String password, String tipoEmpleado,PuntoAtencion puntoAtencion) {
        
        this.tipodocumento = tipoDocumento;
        this.numerodocumento = numeroDocumento;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.direccionfisica = direccionFisica;
        this.direccionelectronica = direccionElectronica;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigopostal = codigoPostal;
        this.password = password;
        this.cargo = tipoEmpleado;
        this.puntoAtencion = puntoAtencion;
    }



    public Empleado() {
        ;
    }

    public String getTipoDocumento() {
        return tipodocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipodocumento = tipoDocumento;
    }

    public Integer getNumeroDocumento() {
        return numerodocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numerodocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getNacionalidad() {
        return nacionalidad;
    }



    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }



    public String getDireccionFisica() {
        return direccionfisica;
    }



    public void setDireccionFisica(String direccionFisica) {
        this.direccionfisica = direccionFisica;
    }



    public String getDireccionElectronica() {
        return direccionelectronica;
    }



    public void setDireccionElectronica(String direccionElectronica) {
        this.direccionelectronica = direccionElectronica;
    }



    public String getTelefono() {
        return telefono;
    }



    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public String getCiudad() {
        return ciudad;
    }



    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }



    public String getDepartamento() {
        return departamento;
    }



    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getCodigoPostal() {
        return codigopostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigopostal = codigoPostal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public PuntoAtencion getPuntoAtencion() {
        return puntoAtencion;
    }

    public void setPuntoAtencion(PuntoAtencion puntoAtencion) {
        this.puntoAtencion = puntoAtencion;
    }
}
