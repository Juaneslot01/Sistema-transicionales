package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Empleado;

import java.util.Collection;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // Método para obtener todos los empleados
    @Query(value = "SELECT * FROM empleados", nativeQuery = true)
    Collection<Empleado> darEmpleados();


    // Método para obtener todos los empleados
    @Query(value = "SELECT cuentas.* FROM empleados INNER JOIN oficinas ON oficinas.numdocgerente = empleados.numerodocumento INNER JOIN cuentas ON cuentas.id_oficina = oficinas.id WHERE oficinas.id = :idoficina", nativeQuery = true)
    Collection<Cuenta> darCuentasGerenteOficina(@Param("idoficina") Integer idoficina);


    // Método para obtener un empleado por su PK
    @Query(value = "SELECT * FROM empleados WHERE  numeroDocumento = :numeroDocumento", nativeQuery = true)
    Empleado darEmpleado(@Param("numeroDocumento") Integer numeroDocumento);

    // Método para insertar un nuevo empleado
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO empleados (tipoDocumento, numeroDocumento, nombre, nacionalidad, direccionFisica, direccionElectronica, " +
            "telefono, ciudad, departamento, codigoPostal, password, cargo, idPuntoAtencion)" +
            "VALUES (:tipoDocumento, :numeroDocumento, :nombre, :nacionalidad, :direccionFisica, :direccionElectronica, " +
            ":telefono, :ciudad, :departamento, :codigoPostal, :password, :cargo,:idPuntoAtencion)", nativeQuery = true)
    void insertarEmpleado(@Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") Integer numeroDocumento, @Param("nombre") String nombre,
                          @Param("nacionalidad") String nacionalidad, @Param("direccionFisica") String direccionFisica,
                          @Param("direccionElectronica") String direccionElectronica, @Param("telefono") String telefono,
                          @Param("ciudad") String ciudad, @Param("departamento") String departamento,
                          @Param("codigoPostal") Integer codigoPostal, @Param("password") String password,
                          @Param("cargo") String cargo, @Param("idPuntoAtencion") Integer idPuntoAtencion);

}
