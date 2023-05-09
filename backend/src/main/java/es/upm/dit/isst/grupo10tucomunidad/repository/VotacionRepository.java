package es.upm.dit.isst.grupo10tucomunidad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.upm.dit.isst.grupo10tucomunidad.model.Votacion;

public interface VotacionRepository extends CrudRepository<Votacion, String> {

    List<Votacion> findByUserIdAndJuntaId(Long userId, Long juntaId);
}