package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Cliente;

import java.util.Collection;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Método para obtener todos los clientes
    @Query(value = "SELECT * FROM clientes", nativeQuery = true)
    Collection<Cliente> darClientes();

    // Método para obtener un cliente por su ID
    @Query(value = "SELECT * FROM clientes WHERE numeroDocumento = :numeroDocumento", nativeQuery = true)
    Cliente darCliente(@Param("numeroDocumento") Integer numeroDocumento);

    @Query(value = "SELECT c.nombre, cu.numcuenta AS numero_cuenta, cu.saldo AS saldo_cuenta, " +
    "o.nombre AS nombre_oficina, p.numprestamo AS id_prestamo, p.monto AS monto_prestamo " +
    "FROM clientes c " +
    "LEFT JOIN cuentas cu ON c.numerodocumento = cu.numdoccliente " +
    "LEFT JOIN oficinas o ON o.id = cu.idoficina " +
    "LEFT JOIN prestamos p ON c.numerodocumento = p.numdoccliente " +
    "WHERE c.numerodocumento = :numeroDocumento", nativeQuery = true)
    List<Object[]> consultarClienteCompleto(@Param("numeroDocumento") Integer numeroDocumento);



    // Método para insertar un nuevo cliente
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (tipoDocumento, numeroDocumento, nombre, nacionalidad, direccionFisica, direccionElectronica, telefono, ciudad, departamento, codigoPostal, password) " +
            "VALUES (:tipoDocumento, :numeroDocumento, :nombre, :nacionalidad, :direccionFisica, :direccionElectronica, :telefono, :ciudad, :departamento, :codigoPostal, :password)", nativeQuery = true)
    void insertarCliente(@Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") Integer numeroDocumento,
                         @Param("nombre") String nombre, @Param("nacionalidad") String nacionalidad,
                         @Param("direccionFisica") String direccionFisica, @Param("direccionElectronica") String direccionElectronica,
                         @Param("telefono") String telefono, @Param("ciudad") String ciudad, @Param("departamento") String departamento,
                         @Param("codigoPostal") Integer codigoPostal, @Param("password") String password);
}
