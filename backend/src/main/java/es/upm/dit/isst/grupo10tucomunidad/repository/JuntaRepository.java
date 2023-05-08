package es.upm.dit.isst.grupo10tucomunidad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import es.upm.dit.isst.grupo10tucomunidad.model.Junta;

public interface JuntaRepository extends CrudRepository<Junta, String> {

}