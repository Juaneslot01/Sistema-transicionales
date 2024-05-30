package uniandes.edu.co.proyecto.repositorio;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;

import java.util.Collection;
import java.util.List;

public interface OperacionRepository extends MongoRepository<Operacion, Integer> {

    @Query(value = "{}", sort = "{_id : -1}", fields = "{_id : 1}")
    Collection<Operacion> findTop1ByOrderByNumerodocumentoDesc();

    @Query("{}")
    Collection<Operacion> darOperacionesTransferencia();

    @Aggregation(pipeline={
        "{$match:{numcuentaprincipal: ?0, fecha:{$regex:?1}}}",
        "{$project: {id_operacion: '$_id', tipo: '$tipo', idpuntoatencion: '$idpuntoatencion', valor: '$valor',numcuentadestino: '$numcuentadestino', numcuentaprincipal: '$numcuentaprincipal', fecha: '$fecha'}}"})
        List<Operacion> darOperacionCuenta(int numcuentaprincipal, String fecha);

}
