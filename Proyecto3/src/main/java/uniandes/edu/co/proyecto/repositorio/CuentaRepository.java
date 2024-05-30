package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Cuenta;

import java.util.Collection;
import java.util.List;

public interface CuentaRepository extends MongoRepository<Cuenta, Integer> {




    


}
