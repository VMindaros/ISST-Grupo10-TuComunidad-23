package es.upm.dit.isst.grupo10tucomunidad;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import es.upm.dit.isst.grupo10tucomunidad.model.Noticia;
import es.upm.dit.isst.grupo10tucomunidad.model.PeticionVecino;
import es.upm.dit.isst.grupo10tucomunidad.model.Rol;
import es.upm.dit.isst.grupo10tucomunidad.repository.NoticiaRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.PeticionRegistroRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.RolRepository;
import es.upm.dit.isst.grupo10tucomunidad.model.Sugerencia;
import es.upm.dit.isst.grupo10tucomunidad.model.Usuario;
import es.upm.dit.isst.grupo10tucomunidad.repository.SugerenciaRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.UsuarioRepository;
import es.upm.dit.isst.grupo10tucomunidad.model.Comentario;
import es.upm.dit.isst.grupo10tucomunidad.model.DatosVecino;
import es.upm.dit.isst.grupo10tucomunidad.model.ERol;
import es.upm.dit.isst.grupo10tucomunidad.repository.ComentarioRepository;
import es.upm.dit.isst.grupo10tucomunidad.repository.DatosVecinoRepository;

@SpringBootApplication
public class Grupo10tucomunidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Grupo10tucomunidadApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialNoticiaData(NoticiaRepository noticiaRepository) {
		return (args) -> {
			noticiaRepository.save(new Noticia(
					null,
					"La piscina quedará cerrada por mantenimiento hasta verano de 2054",
					"Debido al reciente incidente en el cual se introdujeron 100 kg de lentejas en el sistema, nos vemos"
							+
							" en la oblicación de mantener el servicio de piscina cerrado. El culpable ha sido debidamente sancionado.",
					1L,
					LocalDateTime.now(),
					null));
			noticiaRepository.save(new Noticia(
					null,
					"Abierta la convocatoria del año 2024 para plazas de garaje del edificio",
					"Convocatoria abierta hasta el 1 de mayo, inclusiva. Proporcionar información de vehículo y personal al presidente",
					1L,
					LocalDateTime.now(),
					null));
			noticiaRepository.save(new Noticia(
					null,
					"Queda habilitada la sala de reuniones para poder visualizar la final de la Champions League",
					"Debido a la demanda de peticiones y tras una reflexión por parte del presidente, vicepresidente y administrador "
							+
							"se permitirá a los vecinos lo descrito en el título. Recordamos que dicha sala se encuentra en el el bajo, sección B, número 3. "
							+
							"El aforo máximo es de 60 personas, se ruega no sobrepasar dicho límite y cuidar del material. Gracias.",
					1L,
					LocalDateTime.now(),
					null));
			noticiaRepository.save(new Noticia(
					null,
					"Horario de verano para la recogida de basuras",
					"Debido a la demanda de peticiones y tras una reflexión por parte del presidente, vicepresidente y administrador "
							+
							"se permitirá a los vecinos lo descrito en el título. Recordamos que dicha sala se encuentra en el el bajo, sección B, número 3. "
							+
							"El aforo máximo es de 60 personas, se ruega no sobrepasar dicho límite y cuidar del material. Gracias.",
					1L,
					LocalDateTime.now(),
					null));
		};
	}

	@Bean
	public CommandLineRunner initialSugerenciaData(SugerenciaRepository sugerenciaRepository) {
		return (args) -> {
			sugerenciaRepository.save(new Sugerencia(
					1L,
					"Sustituir las bombillas del vestíbulo, pasillos y escaleras por luces LED",
					"Dada la cantidad que hay, creo que podríamos ahorrar bastante a largo plazo si las cambiáramos por luces LED.",
					2L,
					LocalDateTime.now()));

			sugerenciaRepository.save(new Sugerencia(
					2L,
					"Cambiar la cerradura del garaje",
					"Hace poco al vecino del 5ºF le robaron su llave, creo que sería lo más prudente.",
					2L,
					LocalDateTime.now()));

			sugerenciaRepository.save(new Sugerencia(
					3L,
					"Obra para poner una rampa en las escaleras de la entrada",
					"Debido a la edad y la situación de algunos propietarios, se plantea hacer una obra para poner una rampa, seria muy util "
							+
							"para las personas con movilidad reducida y también para familias con carritos de bebes, entre otras cosas",
					2L,
					LocalDateTime.now()));

			sugerenciaRepository.save(new Sugerencia(
					4L,
					"Cambio de socorrista en la piscina",
					"Algunos propietarios nos hemos dado cuenta de que el socorrista de este año se pasa las horas con el móvil, sin hacer caso "
							+
							"a lo que sucede en la piscina, además de que no llama la atención cuando los niños se tiran y algún dia puede pasar una desgracia.",
					2L,
					LocalDateTime.now()));

		};
	}

	@Bean
	public CommandLineRunner initialComentarioData(ComentarioRepository comentarioRepository) {
		return (args) -> {
			comentarioRepository.save(new Comentario(null,
					"Me parece buena idea!",
					3L,
					null));

			comentarioRepository.save(new Comentario(null,
					"Yo no lo veo necesario",
					3L,
					null));

			comentarioRepository.save(new Comentario(null,
					"A mi la verdad es que el socorrista me parece muy majo y hace bien su trabajo.",
					4L,
					null));
		};
	}


	@Bean
	public CommandLineRunner initialJuntaData(JuntaRepository juntaRepository) {
		return (args) -> {
			juntaRepository.save(new Junta(null,
					"Junta extraordinaria para la remodelación de la fachada",
					"La empresa a la que hemos pedido presupuesto nos ha dicho que el precio total era de 54875€, lo que supondría"
					+"una derrama de 3429€ por propietario. En esta junta se va a votar si se está a favor o en contra de contratar la obra.",
					LocalDateTime.now(),
					1L));
		};
	}

	@Bean
	public CommandLineRunner initialUsuarioData(UsuarioRepository usuarioRespository) {
		return (args) -> {
			usuarioRespository.save(new Usuario(
					"000000000",
					"$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG"));
			usuarioRespository.save(new Usuario(
					"111111111",
					"$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG"));
			usuarioRespository.save(new Usuario(
					"222222222",
					"$2a$12$oH/Ci..a5Tz8pjNhZesP3..BUIvGy4SU89M4JSOpbo8j7w.04W.FG"));
		};
	}

	@Bean
	public CommandLineRunner initialRolData(RolRepository rolRepository) {
		return (args) -> {
			rolRepository.save(new Rol(ERol.ROLE_ADMIN));
			rolRepository.save(new Rol(ERol.ROLE_PRESIDENTE));
			rolRepository.save(new Rol(ERol.ROLE_VECINO));
		};
	}

	@Bean
	public CommandLineRunner initialDatosVecinoData(DatosVecinoRepository datosVecinoRepository) {
		return (args) -> {
			datosVecinoRepository.save(new DatosVecino(
					4,
					"A",
					"38347413L"));
			datosVecinoRepository.save(new DatosVecino(
					3,
					"B",
					"86904624K"));
			datosVecinoRepository.save(new DatosVecino(
					5,
					"C",
					"41410277L"));
		};
	}

	@Bean
	public CommandLineRunner initialPeticionRegistroData(PeticionRegistroRepository prRepo) {
		return (args) -> {
			prRepo.save(new PeticionVecino(
					"444444444",
					"$2a$12$WpaRpwjONum/Z3WXgikhaeJEqIN3DwxJfFH8h2hxw0xvZ2rmVaEjC",
					9,
					"A",
					"53691215S",
					null));
			prRepo.save(new PeticionVecino(
					"555555555",
					"$2a$12$WpaRpwjONum/Z3WXgikhaeJEqIN3DwxJfFH8h2hxw0xvZ2rmVaEjC",
					8,
					"B",
					"44037514N",
					null));
			prRepo.save(new PeticionVecino(
					"666666666",
					"$2a$12$WpaRpwjONum/Z3WXgikhaeJEqIN3DwxJfFH8h2hxw0xvZ2rmVaEjC",
					7,
					"C",
					"28080969Q",
					null));
		};
	}

	@Bean
	public CommandLineRunner initialUserData(JdbcTemplate jdbcTemplate) {
		return (args) -> {
			String[] instructionArr = {
					// ADMIN
					"SELECT @userId := id FROM usuarios WHERE tlf_number = '000000000' ORDER BY id DESC LIMIT 1",
					"SELECT @roleId1 := id FROM roles WHERE nombre = 0",
					"INSERT INTO usuarios_roles (usuarios_id, roles_id) VALUES (@userId, @roleId1)",
					"SELECT @datosvecinoId := id FROM datosvecino WHERE dni = '38347413L'",
					"INSERT INTO usuarios_datos (usuarios_id, datosvecino_id) VALUES (@userId, @datosvecinoId)",

					// PRESIDENTE
					"SELECT @userId := id FROM usuarios WHERE tlf_number = '111111111' ORDER BY id DESC LIMIT 1",
					"SELECT @roleId1 := id FROM roles WHERE nombre = 1",
					"INSERT INTO usuarios_roles (usuarios_id, roles_id) VALUES (@userId, @roleId1)",
					"SELECT @datosvecinoId := id FROM datosvecino WHERE dni = '86904624K'",
					"INSERT INTO usuarios_datos (usuarios_id, datosvecino_id) VALUES (@userId, @datosvecinoId)",

					// VECINO
					"SELECT @userId := id FROM usuarios WHERE tlf_number = '222222222' ORDER BY id DESC LIMIT 1",
					"SELECT @roleId1 := id FROM roles WHERE nombre = 2",
					"INSERT INTO usuarios_roles (usuarios_id, roles_id) VALUES (@userId, @roleId1)",
					"SELECT @datosvecinoId := id FROM datosvecino WHERE dni = '41410277L'",
					"INSERT INTO usuarios_datos (usuarios_id, datosvecino_id) VALUES (@userId, @datosvecinoId)"
			};
			for (String sqlInstruction : instructionArr)
				jdbcTemplate.execute(sqlInstruction);
		};
	}
}
