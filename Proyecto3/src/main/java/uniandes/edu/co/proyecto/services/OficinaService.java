package uniandes.edu.co.proyecto.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;

@Service
public class OficinaService {

    private final OficinaRepository oficinaRepository;
    private final MongoTemplate mongoTemplate;
    private final PuntoAtencionRepository puntoAtencionRepository;

    public OficinaService(OficinaRepository oficinaRepository, PuntoAtencionRepository puntoAtencionRepository, MongoTemplate mongoTemplate) {
        this.oficinaRepository = oficinaRepository;
        this.puntoAtencionRepository = puntoAtencionRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void deletePuntoDeAtencionById(int puntoDeAtencionId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("puntosdeatencion.idPunto").is(puntoDeAtencionId));

        Update update = new Update();
        update.pull("puntosdeatencion", Query.query(Criteria.where("idPunto").is(puntoDeAtencionId)));

        mongoTemplate.updateMulti(query, update, Oficina.class);
    }



    public void crearPuntoDeAtencion(Integer idOficina, PuntoAtencion nuevoPuntoDeAtencion) {

        Oficina oficina = oficinaRepository.darOficina(idOficina);
        if (oficina != null) {
            if (oficina.getPuntosdeatencion() == null) {
                oficina.setPuntosdeatencion(new ArrayList<>());
            }
            oficina.getPuntosdeatencion().add(nuevoPuntoDeAtencion);
            oficinaRepository.save(oficina);
        }
    }
}
