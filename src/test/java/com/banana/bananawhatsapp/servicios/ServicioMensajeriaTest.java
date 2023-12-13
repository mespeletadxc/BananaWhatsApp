package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ServicioMensajeriaTest {


    @Autowired
    private IServicioMensajeria servicio;

    @Test
    void testBeans() {
        assertThat(servicio, notNullValue());
    }


    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() throws SQLException {
        Usuario remitente = new Usuario(12, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(11, "Luis", "luis@l.com", LocalDate.now(), true);

        Mensaje mensaje = servicio.enviarMensaje(remitente,destinatario,"Probando crear srv");

        assertThat(mensaje.getId(), greaterThan(0));

    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() {
        Usuario remitente = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(2, "Luis", "luis@l.com", LocalDate.now(), true);

        assertThrows(MensajeException.class, () -> {
            servicio.enviarMensaje(remitente,destinatario,"P");
        });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() throws SQLException {
        Usuario remitente = new Usuario(11, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(12, "Luis", "luis@l.com", LocalDate.now(), true);

        List<Mensaje> mensajes = servicio.mostrarChatConUsuario(remitente,destinatario);

        System.out.println(mensajes);

        assertThat(mensajes.size(), greaterThan(0));
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() {
        Usuario remitente = new Usuario(null, "Juana", "j", LocalDate.now(), true);
        Usuario destinatario = new Usuario(14, "Luis", "luis@l.com", LocalDate.now(), true);


        assertThrows(UsuarioException.class, () -> {
            List<Mensaje> mensajes = servicio.mostrarChatConUsuario(remitente,destinatario);
        });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() throws SQLException {
        Usuario remitente = new Usuario(12, "Juana", "juana@j.com", LocalDate.now(), true);
        Usuario destinatario = new Usuario(11, "Luis", "luis@l.com", LocalDate.now(), true);

        assertTrue(servicio.borrarChatConUsuario(remitente, destinatario));
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() {
        Usuario remitente = new Usuario(null, "Juana", "j", LocalDate.now(), true);
        Usuario destinatario = new Usuario(14, "Luis", "luis@l.com", LocalDate.now(), true);


        assertThrows(UsuarioException.class, () -> {
            servicio.borrarChatConUsuario(remitente, destinatario);
        });
    }
}