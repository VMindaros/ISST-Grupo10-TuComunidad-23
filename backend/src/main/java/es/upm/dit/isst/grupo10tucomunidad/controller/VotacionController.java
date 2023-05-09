package es.upm.dit.isst.grupo10tucomunidad.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.grupo10tucomunidad.model.Votacion;
import es.upm.dit.isst.grupo10tucomunidad.repository.VotacionRepository;

@RestController
public class VotacionController {
    private final VotacionRepository votacionRepository;
    public VotacionController(VotacionRepository n) {
        this.votacionRepository = n;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/juntas/votos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRESIDENTE') or hasRole('VECINO')")
    List<Votacion> readAll() {
        return (List<Votacion>) votacionRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/juntas/votos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRESIDENTE') or hasRole('VECINO')")
    ResponseEntity<Votacion> create(@RequestBody Votacion newVotacion) throws URISyntaxException {
        List<Votacion> votosExistente = votacionRepository.findByUserIdAndJuntaId(newVotacion.getUserId(), newVotacion.getJuntaId());
        if (!votosExistente.isEmpty()) {
            return null;
        }
        Votacion res = votacionRepository.save(newVotacion);
        return ResponseEntity.created(new URI("/juntas/votos/")).body(res);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/juntas/votos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRESIDENTE') or hasRole('VECINO')")
    ResponseEntity<Votacion> update(@RequestBody Votacion newVotacion, @PathVariable String id) {
        return votacionRepository.findById(id).map(votacion -> {
            votacionRepository.save(votacion);
            return ResponseEntity.ok().body(votacion);
        }).orElse(new ResponseEntity<Votacion>(HttpStatus.NOT_FOUND));
    }
}   


