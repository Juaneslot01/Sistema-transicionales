package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Oficina;

import java.util.Collection;

public interface OficinaRepository extends JpaRepository<Oficina, Integer> {

    // Método para obtener todas las oficinas
    @Query(value = "SELECT * FROM oficinas", nativeQuery = true)
    Collection<Oficina> darOficinas();

    // Método para obtener una oficina por su ID
    @Query(value = "SELECT * FROM oficinas WHERE id = :id", nativeQuery = true)
    Oficina darOficina(@Param("id") Integer id);

    // Método para insertar una nueva oficina
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO oficinas (id, nombre, direccion, numPuntosAtencion, numdocgerente) " +
            "VALUES (:id, :nombre, :direccion, :numPuntosAtencion, :numdocgerente)", nativeQuery = true)
    void insertarOficina(@Param("id") Integer id, @Param("nombre") String nombre, @Param("direccion") String direccion,
                         @Param("numPuntosAtencion") Integer numPuntosAtencion, @Param("numdocgerente") Integer numdocgerente);

}
