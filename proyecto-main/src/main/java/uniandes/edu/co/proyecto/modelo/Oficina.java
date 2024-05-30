package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oficinas")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String direccion;
    private Integer numpuntosatencion;

    @OneToOne
    @JoinColumn(name = "numdocgerente", referencedColumnName = "numerodocumento")
    private Empleado empleado;   

    public Oficina(String nombre, String direccion, Integer numPuntosAtencion, Empleado empleado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numpuntosatencion = numPuntosAtencion;
        this.empleado = empleado;
    }



    public Oficina()
    {
        ;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
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



    public Integer getNumPuntosAtencion() {
        return numpuntosatencion;
    }



    public void setNumPuntosAtencion(Integer numPuntosAtencion) {
        this.numpuntosatencion = numPuntosAtencion;
    }



    public Empleado getEmpleado() {
        return empleado;
    }



    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    
    

    
}
