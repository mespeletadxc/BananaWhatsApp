package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class MensajeJDBCRepositoryTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private IMensajeRepository repo;

    @Test
    void testBeans() {
        assertNotNull(context);
        assertNotNull(repo);
        System.out.println(repo.getUrlConn());
    }

    @Test
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws SQLException {
        Usuario remitente = new Usuario(11, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(12, "Luis", "luis@l.com", LocalDate.now(), true);

        Mensaje mensaje = new Mensaje(null, remitente, destinatario, "Probando crear", LocalDate.now());
        repo.crear(mensaje);
        System.out.println(mensaje);

        assertThat(mensaje.getId(), greaterThan(0));
        System.out.println("Id insertado: " + mensaje.getId());
    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() throws SQLException {
        Usuario remitente = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(2, "Luis", "luis@l.com", LocalDate.now(), true);
        Mensaje mensaje = new Mensaje(null, remitente, destinatario, "P", LocalDate.now());

        assertThrows(MensajeException.class, () -> {
            repo.crear(mensaje);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() throws SQLException{
        Usuario remitente = new Usuario(11, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(12, "Luis", "luis@l.com", LocalDate.now(), true);


        List<Mensaje> mensajes = repo.obtener(remitente,destinatario);

        System.out.println(mensajes);

        assertThat(mensajes.size(), greaterThan(0));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() throws SQLException {
        Usuario remitente = new Usuario(null, "Juana", "j", LocalDate.now(), true);
        Usuario destinatario = new Usuario(14, "Luis", "luis@l.com", LocalDate.now(), true);


        assertThrows(UsuarioException.class, () -> {
            List<Mensaje> mensajes = repo.obtener(remitente,destinatario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() {
    }

}