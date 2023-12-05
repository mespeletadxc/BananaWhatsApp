package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ServicioUsuariosTest {

    @Autowired
    private IServicioUsuarios servicio;

    @Test
    void testBeans() {
        assertThat(servicio, notNullValue());
    }

    @Test
    void dadoUnUsuarioValido_cuandoCrearUsuario_entoncesUsuarioValido() throws SQLException {
        Usuario usuario = new Usuario(null, "Luisa", "prueba@prueba.com", LocalDate.now(), true);
        servicio.crearUsuario(usuario);
        System.out.println(usuario);

        assertThat(usuario.getId(), greaterThan(0));
        System.out.println("Id insertado: " + usuario.getId());
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrearUsuario_entoncesExcepcion() throws SQLException {
        Usuario usuario = new Usuario(null, "Lu", "prueba", LocalDate.of(2023, 02, 15), true);

        assertThrows(UsuarioException.class, () -> {

            servicio.crearUsuario(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarUsuario_entoncesUsuarioValido() throws SQLException {
        Usuario usuario = new Usuario(10, "Juana", "juana@j.com", LocalDate.now(), true);
        servicio.borrarUsuario(usuario);

        assertThat(usuario.getId(), greaterThan(0));

    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarUsuario_entoncesExcepcion() throws SQLException {
        Usuario usuario = new Usuario(1000, "Luisa", "prueba@prueba.com", LocalDate.now(), true);

        assertThrows(UsuarioException.class, () -> {
            servicio.borrarUsuario(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizarUsuario_entoncesUsuarioValido() throws SQLException {
        Usuario usuario = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), true);
        servicio.actualizarUsuario(usuario);

        assertThat(usuario.getId(), greaterThan(0));

    }

    @Test
    void dadoUnUsuarioNOValido_cuandoActualizarUsuario_entoncesExcepcion() throws SQLException {
        Usuario usuario = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), false);

        assertThrows(UsuarioException.class, () -> {
            servicio.actualizarUsuario(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDesinatarios_entoncesUsuarioValido() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDesinatarios_entoncesExcepcion() {
    }
}