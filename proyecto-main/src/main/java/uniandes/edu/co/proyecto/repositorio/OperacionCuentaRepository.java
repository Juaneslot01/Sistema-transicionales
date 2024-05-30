package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;

import java.time.LocalDate;
import java.util.Collection;

public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Integer> {

    // Método para obtener todas las operaciones de cuenta
    @Query(value = "SELECT * FROM operacionesCuentas", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesCuenta();

    @Query(value = "SELECT * FROM operacionesCuentas WHERE operacionesCuentas.numcuenta = :numcuenta", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesUnaCuenta(@Param("numcuenta") Integer numcuenta);

    // Método para obtener una operación de cuenta por su ID
    @Query(value = "SELECT * FROM operacionesCuentas WHERE id = :id", nativeQuery = true)
    OperacionCuenta darOperacionCuenta(@Param("id") Integer id);

    // Método para insertar una nueva operación de cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesCuentas (id, tipoOperacion, valor, fecha, idPuntoAtencion, numCuenta) " +
            "VALUES (:id, :tipoOperacion, :valor, :fecha, :idPuntoAtencion, :numCuenta)", nativeQuery = true)
    void insertarOperacionCuenta(@Param("id") Integer id, @Param("tipoOperacion") String tipoOperacion, @Param("valor") Integer valor,
                                  @Param("fecha") LocalDate fecha, @Param("idPuntoAtencion") Integer idPuntoAtencion,
                                  @Param("numCuenta") Integer numCuenta);

}
