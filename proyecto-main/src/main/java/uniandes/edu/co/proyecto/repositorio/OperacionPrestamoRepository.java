package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.OperacionPrestamo;

import java.time.LocalDate;
import java.util.Collection;

public interface OperacionPrestamoRepository extends JpaRepository<OperacionPrestamo, Integer> {

    // Método para obtener todas las operaciones de préstamo
    @Query(value = "SELECT * FROM operacionesPrestamo", nativeQuery = true)
    Collection<OperacionPrestamo> darOperacionesPrestamo();

    // Método para obtener una operación de préstamo por su ID
    @Query(value = "SELECT * FROM operacionesPrestamo WHERE id = :id", nativeQuery = true)
    OperacionPrestamo darOperacionPrestamo(@Param("id") Integer id);

    // Método para insertar una nueva operación de préstamo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacionesPrestamo (id, tipoOperacion, valor, fecha, idPuntoAtencion, numPrestamo) " +
            "VALUES (:id, :tipoOperacion, :valor, :fecha, :idPuntoAtencion, :numPrestamo)", nativeQuery = true)
    void insertarOperacionPrestamo(@Param("id") Integer id, @Param("tipoOperacion") String tipoOperacion, @Param("valor") Integer valor,
                                   @Param("fecha") LocalDate fecha, @Param("idPuntoAtencion") Integer idPuntoAtencion,
                                   @Param("numPrestamo") Integer integer);

}
