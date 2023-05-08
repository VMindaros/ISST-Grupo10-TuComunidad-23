package es.upm.dit.isst.grupo10tucomunidad.controller;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.upm.dit.isst.grupo10tucomunidad.controller.VotacionController;
import es.upm.dit.isst.grupo10tucomunidad.model.Votacion;
import es.upm.dit.isst.grupo10tucomunidad.repository.VotacionRepository;

public class VotacionControllerTest {

    private VotacionController votacionController;
    private VotacionRepository votacionRepository;

    @Before
    public void setup() {
        votacionRepository = mock(VotacionRepository.class);
        votacionController = new VotacionController(votacionRepository);
    }

    @Test
    public void testLeerVotaciones() {
        List<Votacion> votaciones = new ArrayList<>();
        votaciones.add(new Votacion(1L, "Voto 1",1L, 1L));
        votaciones.add(new Votacion(2L, "Voto 2", 2L, 2L));

        when(votacionRepository.findAll()).thenReturn(votaciones);
        List<Votacion> result = votacionController.readAll();


        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getVoto(), is("Voto 1"));
        assertThat(result.get(0).getJuntaId(), is(1L));
        assertThat(result.get(0).getUserId(), is(1L));
    }

    @Test
    public void testCrearVotaciones() throws URISyntaxException {
 
        Votacion newVotacion = new Votacion(1L, "Voto 1",1L, 1L);
        when(votacionRepository.save(newVotacion)).thenReturn(newVotacion);
    }

    
}
