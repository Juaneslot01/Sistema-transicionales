package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

import java.util.Collection;

public interface PuntoAtencionRepository extends JpaRepository<PuntoAtencion, Integer> {

    // Método para obtener todos los puntos de atención
    @Query(value = "SELECT * FROM PUNTOSATENCION", nativeQuery = true)
    Collection<PuntoAtencion> darPuntosAtencion();

    // Método para obtener un punto de atención por su ID
    @Query(value = "SELECT * FROM PUNTOSATENCION WHERE id = :id", nativeQuery = true)
    PuntoAtencion darPuntoAtencion(@Param("id") Integer id);

    // Método para insertar un nuevo punto de atención
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PUNTOSATENCION (id, tipo, ubicacion, idoficina) VALUES (:id, :tipo, :ubicacion, :idoficina)", nativeQuery = true)
    void insertarPuntoAtencion(@Param("id") Integer id, @Param("tipo") String tipo, @Param("ubicacion") String ubicacion, @Param("idoficina") Integer idoficina);


    // Método para eliminar un punto de atención por su ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PUNTOSATENCION WHERE id = :id", nativeQuery = true)
    void eliminarPuntoAtencion(@Param("id") Integer id);
}
