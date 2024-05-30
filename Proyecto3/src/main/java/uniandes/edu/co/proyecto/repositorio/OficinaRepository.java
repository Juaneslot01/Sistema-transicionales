package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

import java.util.Collection;
import java.util.List;

public interface OficinaRepository extends MongoRepository<Oficina, Integer> {

    public class MaxIdResult {
        private int maxIdPunto;
    
        public int getMaxIdPunto() {
            return maxIdPunto;
        }
    
        public void setMaxIdPunto(int maxIdPunto) {
            this.maxIdPunto = maxIdPunto;
        }
    }
    

    @Aggregation(pipeline={"{ $unwind: '$puntosdeatencion' }", 
    "{$project: {idOficina: '$_id', idPunto: '$puntosdeatencion.idPunto', ubicacion: '$puntosdeatencion.ubicacion',tipo: '$puntosdeatencion.tipo'}}" })
    List<PuntoAtencion> darPuntosAtencion();

    

    @Aggregation(pipeline={"{ $unwind: '$puntosdeatencion' }", 
    "{$match: {'puntosdeatencion.idPunto': ?0}}",
    "{$project: {idOficina: '$_id', idPunto: '$puntosdeatencion.idPunto', ubicacion: '$puntosdeatencion.ubicacion',tipo: '$puntosdeatencion.tipo'}}" })
    PuntoAtencion darPuntoAtencion(Integer idPuntoAtencion);

    @Query("{}")
    Collection<Oficina> darOficinas();

    @Query("{ '_id' : ?0 }")
    Oficina darOficina(Integer id);

    @Query(value = "{}", sort = "{_id : -1}", fields = "{_id : 1}")
    Collection<Oficina> findTop1ByOrderByIdDesc();

    @Aggregation(pipeline = {
        "{$unwind: '$puntosdeatencion'}",
        "{$group: {_id: null, maxIdPunto: {$max: '$puntosdeatencion.idPunto'}}}"
    })
    List<MaxIdResult> findMaxIdPunto();

    @Query("{'puntosdeatencion.idPunto': ?0}")
    void deletePuntoDeAtencionById(int puntoDeAtencionId);





}
