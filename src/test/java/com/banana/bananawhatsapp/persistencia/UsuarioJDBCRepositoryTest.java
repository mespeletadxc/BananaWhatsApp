package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class UsuarioJDBCRepositoryTest {


    @Autowired
    private ApplicationContext context;

    @Autowired
    private IUsuarioRepository repo;

    @Test
    void testBeans() {
        assertNotNull(context);
        assertNotNull(repo);
        System.out.println(repo.getUrlConn());
    }

    @Test
    void dadoUnUsuarioValido_cuandoCrear_entoncesUsuarioValido() throws SQLException {
        Usuario usuario = new Usuario(null, "Luisa", "prueba@prueba.com", LocalDate.now(), true);
        repo.crear(usuario);
        System.out.println(usuario);

        assertThat(usuario.getId(), greaterThan(0));
        System.out.println("Id insertado: " + usuario.getId());
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoCrear_entoncesExcepcion() throws SQLException {
        Usuario usuario = new Usuario(null, "Lu", "prueba", LocalDate.of(2023, 02, 15), true);

        assertThrows(UsuarioException.class, () -> {
            repo.crear(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoActualizar_entoncesUsuarioValido() throws SQLException{
        Usuario usuario = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), true);
        repo.actualizar(usuario);

        assertThat(usuario.getId(), greaterThan(0));
    }



    @Test
    void dadoUnUsuarioNOValido_cuandoActualizar_entoncesExcepcion() throws SQLException{
        Usuario usuario = new Usuario(1, "Juana", "juana@j.com", LocalDate.now(), false);

        assertThrows(UsuarioException.class, () -> {
            repo.actualizar(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrar_entoncesOK() throws SQLException{
        Usuario usuario = new Usuario(3, "Luisa", "prueba@prueba.com", LocalDate.now(), true);

        assertTrue(repo.borrar(usuario));
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrar_entoncesExcepcion() throws SQLException{
        Usuario usuario = new Usuario(1000, "Luisa", "prueba@prueba.com", LocalDate.now(), true);

        assertThrows(UsuarioException.class, () -> {
            repo.borrar(usuario);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtenerPosiblesDestinatarios_entoncesLista() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtenerPosiblesDestinatarios_entoncesExcepcion() {
    }

}