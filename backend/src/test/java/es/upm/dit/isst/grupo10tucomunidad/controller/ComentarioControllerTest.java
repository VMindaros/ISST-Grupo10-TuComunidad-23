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

import es.upm.dit.isst.grupo10tucomunidad.controller.ComentarioController;
import es.upm.dit.isst.grupo10tucomunidad.model.Comentario;
import es.upm.dit.isst.grupo10tucomunidad.repository.ComentarioRepository;

public class ComentarioControllerTest {
    private ComentarioController comentarioController;
    private ComentarioRepository comentarioRepository;

    @Before
    public void setup() {
        comentarioRepository = mock(ComentarioRepository.class);
        comentarioController = new ComentarioController(comentarioRepository);
    }

    @Test
    public void testLeerComentarios() {
        List<Comentario> comentarios = new ArrayList<>();
        comentarios.add(new Comentario(1L, "Comentario 1",1L, 1L));
        comentarios.add(new Comentario(2L, "Comentario 2", 2L, 2L));

        when(comentarioRepository.findAll()).thenReturn(comentarios);
        List<Comentario> result = comentarioController.readAll();


        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getDescripcion(), is("Comentario 1"));
        assertThat(result.get(0).getSugerenciaId(), is(1L));
        assertThat(result.get(0).getUserId(), is(1L));
    }


    @Test
    public void testCrearComentarios() throws URISyntaxException {
 
        Comentario newComentario = new Comentario(1L, "Nuevo Comentario",1L, 1L);
        when(comentarioRepository.save(newComentario)).thenReturn(newComentario);
        ResponseEntity<Comentario> response = comentarioController.create(newComentario);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getHeaders().getLocation(), is(new URI("/sugerencias/responder/")));
        assertThat(response.getBody(), is(newComentario));

    }
}
