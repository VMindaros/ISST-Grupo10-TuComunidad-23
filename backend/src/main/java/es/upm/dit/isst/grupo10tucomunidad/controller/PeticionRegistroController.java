package es.upm.dit.isst.grupo10tucomunidad.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}