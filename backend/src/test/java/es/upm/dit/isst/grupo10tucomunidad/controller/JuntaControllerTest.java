package es.upm.dit.isst.grupo10tucomunidad.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any; // se cambia la importación a ArgumentMatchers
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

import es.upm.dit.isst.grupo10tucomunidad.controller.JuntaController;
import es.upm.dit.isst.grupo10tucomunidad.model.Junta;
import es.upm.dit.isst.grupo10tucomunidad.repository.JuntaRepository;

public class JuntaControllerTest {
    private JuntaController juntaController;
    private JuntaRepository juntaRepository;

    @Before
    public void setup() {
        juntaRepository = mock(JuntaRepository.class);
        juntaController = new JuntaController(juntaRepository);
    }

    @Test
    public void testLeerJuntas() {
        List<Junta> juntas = new ArrayList<>();
        juntas.add(new Junta(1L, "Junta 1", "Cuerpo de la junta 1", LocalDateTime.now(), 1L));
        juntas.add(new Junta(2L, "Junta 2", "Cuerpo de la junta 2", LocalDateTime.now(), 0L));

        when(juntaRepository.findAll()).thenReturn(juntas);
        List<Junta> result = juntaController.readAll();


        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getTitulo(), is("Junta 1"));
        assertThat(result.get(0).getDescripcion(), is("Cuerpo de la junta 1"));
        assertThat(result.get(0).getVotacionActiva(), is(1L));

    }

    @Test
    public void testCrearJunta() throws URISyntaxException {
        Junta junta = new Junta();
        junta.setTitulo("Nueva junta");
        junta.setDescripcion("Cuerpo de la nueva junta");
        
        when(juntaRepository.save(any(Junta.class))).thenAnswer(invocation -> {
            Junta savedJunta = invocation.getArgument(0);
            savedJunta.setId(1L); // Establecer un valor para la propiedad id
            return savedJunta;
        });
        
        ResponseEntity<Junta> response = juntaController.create(junta);
        
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody().getId(), notNullValue()); // Verificar que la propiedad id no sea nula
        assertThat(response.getBody().getTitulo(), is("Nueva junta"));
        assertThat(response.getBody().getDescripcion(), is("Cuerpo de la nueva junta"));
    }
}