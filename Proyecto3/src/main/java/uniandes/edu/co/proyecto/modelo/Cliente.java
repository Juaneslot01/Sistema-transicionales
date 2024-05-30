package uniandes.edu.co.proyecto.modelo;


import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Clientes")
public class Cliente {
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
    private List<Cuenta> cuentas;

    public Cliente(String tipoDocumento, Integer numeroDocumento, String nombre, String nacionalidad, String direccionFisica,
            String direccionElectronica, String telefono, String ciudad, String departamento, Integer codigoPostal,
            String password, List<Cuenta> cuentas) {
        
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
        this.cuentas = cuentas;
    }

    public Cliente() {
        ;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Integer getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Integer numerodocumento) {
        this.numerodocumento = numerodocumento;
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

    public String getDireccionfisica() {
        return direccionfisica;
    }

    public void setDireccionfisica(String direccionfisica) {
        this.direccionfisica = direccionfisica;
    }

    public String getDireccionelectronica() {
        return direccionelectronica;
    }

    public void setDireccionelectronica(String direccionelectronica) {
        this.direccionelectronica = direccionelectronica;
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

    public Integer getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(Integer codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
