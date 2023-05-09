package es.upm.dit.isst.grupo10tucomunidad.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.upm.dit.isst.grupo10tucomunidad.dto.UsuarioDto;
import es.upm.dit.isst.grupo10tucomunidad.model.DatosVecino;
import es.upm.dit.isst.grupo10tucomunidad.model.ERol;
import es.upm.dit.isst.grupo10tucomunidad.model.Rol;
import es.upm.dit.isst.grupo10tucomunidad.model.Usuario;
import es.upm.dit.isst.grupo10tucomunidad.repository.DatosVecinoRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.RolRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.UsuarioRepository;

public class UsuarioControllerTest {
    private UsuarioController usuarioController;

    private UsuarioRepository usuarioRepository;
    private DatosVecinoRepository datosVecinoRepository;
    private RolRepository rolRepository;

    @Before
    public void setup() {
        usuarioRepository = mock(UsuarioRepository.class);
        datosVecinoRepository = mock(DatosVecinoRepository.class);
        rolRepository = mock(RolRepository.class);

        usuarioController = new UsuarioController(usuarioRepository, datosVecinoRepository, rolRepository);

        usuarioRepository.save(new Usuario(
                "000000000",
                "$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG",
                new HashSet<>() {
                    {
                        add(new Rol(ERol.ROLE_ADMIN));
                    }
                },
                new DatosVecino(1, "A", "61553359T")));
        usuarioRepository.save(new Usuario(
                "111111111",
                "$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG",
                new HashSet<>() {
                    {
                        add(new Rol(ERol.ROLE_ADMIN));
                    }
                },
                new DatosVecino(2, "B", "78707721L")));

        usuarioRepository.save(new Usuario(
                "222222222",
                "$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG",
                new HashSet<>() {
                    {
                        add(new Rol(ERol.ROLE_ADMIN));
                    }
                },
                new DatosVecino(3, "C", "26084441A")));
    }

    @Test
    public void testReadAll() {
        ResponseEntity<List<UsuarioDto>> response = usuarioController.readAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdate() {
        Usuario updatedUser = new Usuario(
                "333333333",
                "$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG",
                new HashSet<>() {
                    {
                        add(new Rol(ERol.ROLE_ADMIN));
                    }
                },
                new DatosVecino(4, "D", "46385912E"));
        Long id = 1L;

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(new Usuario()));
        when(datosVecinoRepository.save(any())).thenReturn(updatedUser.getDatosVecino());
        when(rolRepository.findByNombre(ERol.ROLE_PRESIDENTE)).thenReturn(Optional.of(new Rol(ERol.ROLE_PRESIDENTE)));
        when(rolRepository.findByNombre(ERol.ROLE_VECINO)).thenReturn(Optional.of(new Rol(ERol.ROLE_VECINO)));
        when(usuarioRepository.save(any())).thenReturn(updatedUser);

        ResponseEntity<Usuario> response = usuarioController.update(updatedUser, id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
