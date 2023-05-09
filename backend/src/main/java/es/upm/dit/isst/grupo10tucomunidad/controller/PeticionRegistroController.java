package es.upm.dit.isst.grupo10tucomunidad.controller;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.grupo10tucomunidad.model.DatosVecino;
import es.upm.dit.isst.grupo10tucomunidad.model.ERol;
import es.upm.dit.isst.grupo10tucomunidad.model.PeticionVecino;
import es.upm.dit.isst.grupo10tucomunidad.model.Rol;
import es.upm.dit.isst.grupo10tucomunidad.model.Usuario;
import es.upm.dit.isst.grupo10tucomunidad.repository.DatosVecinoRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.PeticionRegistroRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.RolRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.UsuarioRepository;

@RestController
public class PeticionRegistroController {
    private final PeticionRegistroRepository peticionRegistroRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final DatosVecinoRepository datosVecinoRepository;
    private final RolRepository rolRepository;

    public PeticionRegistroController(PeticionRegistroRepository pr, PasswordEncoder pe,
            UsuarioRepository ur, DatosVecinoRepository dv,
            RolRepository r) {
        this.peticionRegistroRepository = pr;
        this.passwordEncoder = pe;
        this.usuarioRepository = ur;
        this.datosVecinoRepository = dv;
        this.rolRepository = r;
    }

    @PostMapping("/peticionregistro")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<PeticionVecino> create(@RequestBody PeticionVecino newPeticion) throws URISyntaxException {
        String encodedPassword = passwordEncoder.encode(newPeticion.getPassword());
        newPeticion.setPassword(encodedPassword);
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

    @PostMapping("/admisionregistro/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Usuario> create(@PathVariable Long id) {
        return peticionRegistroRepository.findById(id).map(peticionRegistro -> {
            DatosVecino newDatosVecino = new DatosVecino(
                    peticionRegistro.getPiso(),
                    peticionRegistro.getLetra(),
                    peticionRegistro.getDni());
            datosVecinoRepository.save(newDatosVecino);

            Set<Rol> roles = new HashSet<>();

            Rol userRole = rolRepository.findByNombre(ERol.ROLE_VECINO)
                    .orElseThrow(() -> new RuntimeException("No se encuentra rol"));

            roles.add(userRole);

            Usuario newUsuario = new Usuario(
                    peticionRegistro.getTlfNumber(),
                    peticionRegistro.getPassword(),
                    roles,
                    newDatosVecino);

            usuarioRepository.save(newUsuario);
            return ResponseEntity.ok().body(newUsuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }
}