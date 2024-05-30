package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.OperacionTransferencia;

import java.time.LocalDate;
import java.util.Collection;

public interface OperacionTransferenciaRepository extends JpaRepository<OperacionTransferencia, Integer> {

    // Método para obtener todas las operaciones de transferencia
    @Query(value = "SELECT * FROM operacionesTransferencia", nativeQuery = true)
    Collection<OperacionTransferencia> darOperacionesTransferencia();

    @Query(value = "SELECT * FROM operacionesTransferencia WHERE operacionesTransferencia.numcuentadestino = :numcuenta or operacionesTransferencia.numcuentaorigen = :numcuenta", nativeQuery = true)
    Collection<OperacionTransferencia> darOperacionesTransferenciaCuenta(@Param("numcuenta") Integer numcuenta);

    // Método para obtener una operación de transferencia por su ID
    @Query(value = "SELECT * FROM operacionesTransferencia WHERE id = :id", nativeQuery = true)
    OperacionTransferencia darOperacionTransferencia(@Param("id") Integer id);

    // Método para insertar una nueva operación de transferencia
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesTransferencia (id, valor, fecha, numCuentaOrigen, numCuentaDestino, idPuntoAtencion) " +
            "VALUES (:id, :valor, :fecha, :numCuentaOrigen, :numCuentaDestino, :idPuntoAtencion)", nativeQuery = true)
    void insertarOperacionTransferencia(@Param("id") Integer id, @Param("valor") Integer valor, @Param("fecha") LocalDate fecha,
                                        @Param("numCuentaOrigen") Integer integer,
                                        @Param("numCuentaDestino") Integer integer2,
                                        @Param("idPuntoAtencion") Integer idPuntoAtencion);

}
