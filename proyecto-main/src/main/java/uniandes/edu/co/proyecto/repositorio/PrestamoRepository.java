package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Prestamo;

import java.time.LocalDate;
import java.util.Collection;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    // Método para obtener todos los préstamos
    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamos();

    // Método para obtener un préstamo por su número de préstamo
    @Query(value = "SELECT * FROM prestamos WHERE numPrestamo = :numPrestamo", nativeQuery = true)
    Prestamo darPrestamo(@Param("numPrestamo") Integer numPrestamo);

    // Método para insertar un nuevo préstamo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prestamos (numPrestamo, tipoPrestamo, monto, intereses, estado, numCuotas, " +
            "diaPagoProximaCuota, numDocCliente) VALUES (:numPrestamo, :tipoPrestamo, :monto, :intereses, :estado, " +
            ":numCuotas, :diaPagoProximaCuota, :numDocCliente)", nativeQuery = true)
    void insertarPrestamo(@Param("numPrestamo") Integer integer, @Param("tipoPrestamo") String tipoPrestamo,
                          @Param("monto") Integer monto, @Param("intereses") Double intereses,
                          @Param("estado") String estado, @Param("numCuotas") Integer numCuotas,
                          @Param("diaPagoProximaCuota") LocalDate diaPagoProximaCuota,
                          @Param("numDocCliente") Integer numDocCliente);

    // Método para actualizar un préstamo existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET estado = :estado WHERE numPrestamo = :numPrestamo", nativeQuery = true)
    void actualizarEstadoPrestamo(@Param("numPrestamo") Integer numPrestamo,
                                  @Param("estado") String estado);

// Método para actualizar un préstamo existente
    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET monto = :monto WHERE numPrestamo = :numPrestamo", nativeQuery = true)
    void actualizarMontoPrestamo(@Param("numPrestamo") Integer numPrestamo,
                                  @Param("monto") Integer monto);
    

}
