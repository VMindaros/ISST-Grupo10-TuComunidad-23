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

import es.upm.dit.isst.grupo10tucomunidad.controller.SugerenciaController;
import es.upm.dit.isst.grupo10tucomunidad.model.Sugerencia;
import es.upm.dit.isst.grupo10tucomunidad.repository.SugerenciaRepository;

public class SugerenciaControllerTest {
    private SugerenciaController sugerenciaController;
    private SugerenciaRepository sugerenciaRepository;

    @Before
    public void setup() {
        sugerenciaRepository = mock(SugerenciaRepository.class);
        sugerenciaController = new SugerenciaController(sugerenciaRepository);
    }

    @Test
    public void testLeerSugerencias() {
        List<Sugerencia> sugerencias = new ArrayList<>();
        sugerencias.add(new Sugerencia(1L, "Sugerencia 1", "Cuerpo de la Sugerencia 1", 1L, LocalDateTime.now()));
        sugerencias.add(new Sugerencia(2L, "Sugerencia 2", "Cuerpo de la Sugerencia 2", 2L, LocalDateTime.now()));

        when(sugerenciaRepository.findAll()).thenReturn(sugerencias);
        List<Sugerencia> result = sugerenciaController.readAll();


        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getTitulo(), is("Sugerencia 1"));
        assertThat(result.get(0).getDescripcion(), is("Cuerpo de la Sugerencia 1"));
        assertThat(result.get(0).getUserId(), is(1L));

    }

    @Test
    public void testCrearSugerencias() throws URISyntaxException {
 
        Sugerencia newSugerencia = new Sugerencia(1L, "Nueva sugerencia", "Descripción nueva sugerencia", 1L, LocalDateTime.now());
        when(sugerenciaRepository.save(newSugerencia)).thenReturn(newSugerencia);
        ResponseEntity<Sugerencia> response = sugerenciaController.create(newSugerencia);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getHeaders().getLocation(), is(new URI("/sugerencias/1")));
        assertThat(response.getBody(), is(newSugerencia));
    }


}