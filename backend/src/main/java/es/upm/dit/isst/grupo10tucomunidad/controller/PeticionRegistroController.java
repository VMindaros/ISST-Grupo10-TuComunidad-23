package es.upm.dit.isst.grupo10tucomunidad.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.grupo10tucomunidad.model.PeticionVecino;
import es.upm.dit.isst.grupo10tucomunidad.repository.PeticionRegistroRepository;

@RestController
public class PeticionRegistroController {
    private final PeticionRegistroRepository peticionRegistroRepository;

    public PeticionRegistroController(PeticionRegistroRepository pr) {
        this.peticionRegistroRepository = pr;
    }

    @PostMapping("/peticionregistro")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<PeticionVecino> create(@RequestBody PeticionVecino newPeticion) throws URISyntaxException {
        PeticionVecino res = peticionRegistroRepository.save(newPeticion);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/admisionregistro")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('ADMIN')")
    List<PeticionVecino> readAll() {
       return (List<PeticionVecino>) peticionRegistroRepository.findAll(); 
    }

    @DeleteMapping("/admisionregistro/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PeticionVecino> delete(@PathVariable Long id) {
        peticionRegistroRepository.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}