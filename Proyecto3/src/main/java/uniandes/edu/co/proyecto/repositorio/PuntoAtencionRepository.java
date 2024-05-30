package uniandes.edu.co.proyecto.repositorio;

import org.bson.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

import java.util.Collection;
import java.util.List;

public interface PuntoAtencionRepository extends MongoRepository<PuntoAtencion, Integer> {

    // Método para obtener un punto de atención por su ID
    @Query(value = "SELECT * FROM PUNTOSATENCION WHERE id = :id")
    PuntoAtencion darPuntoAtencion(@Param("id") Integer id);
}
