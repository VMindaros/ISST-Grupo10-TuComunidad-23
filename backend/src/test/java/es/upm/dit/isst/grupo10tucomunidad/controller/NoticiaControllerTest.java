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

import es.upm.dit.isst.grupo10tucomunidad.controller.NoticiaController;
import es.upm.dit.isst.grupo10tucomunidad.model.Noticia;
import es.upm.dit.isst.grupo10tucomunidad.repository.NoticiaRepository;

public class NoticiaControllerTest {
    private NoticiaController noticiaController;
    private NoticiaRepository noticiaRepository;

    @Before
    public void setup() {
        noticiaRepository = mock(NoticiaRepository.class);
        noticiaController = new NoticiaController(noticiaRepository);
    }

    @Test
    public void testLeerNoticias() {
        List<Noticia> noticias = new ArrayList<>();
        noticias.add(new Noticia(1L, "Noticia 1", "Cuerpo de la noticia 1", 1L, LocalDateTime.now(), null));
        noticias.add(new Noticia(2L, "Noticia 2", "Cuerpo de la noticia 2", 2L, LocalDateTime.now(), null));

        when(noticiaRepository.findAll()).thenReturn(noticias);
        List<Noticia> result = noticiaController.readAll();


        assertThat(result, hasSize(2));
        assertThat(result.get(0).getId(), is(1L));
        assertThat(result.get(0).getTitulo(), is("Noticia 1"));
        assertThat(result.get(0).getDescripcion(), is("Cuerpo de la noticia 1"));
        assertThat(result.get(0).getUserId(), is(1L));

    }

    @Test
    public void testCrearNoticias() throws URISyntaxException {
 
        Noticia newNoticia = new Noticia(1L, "Nueva noticia", "Descripción nueva noticia", 1L, LocalDateTime.now(), null);
        when(noticiaRepository.save(newNoticia)).thenReturn(newNoticia);
        ResponseEntity<Noticia> response = noticiaController.create(newNoticia);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getHeaders().getLocation(), is(new URI("/noticias/1")));
        assertThat(response.getBody(), is(newNoticia));
    }


}