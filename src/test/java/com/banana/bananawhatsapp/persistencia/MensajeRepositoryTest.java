package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
//@ActiveProfiles("dev")
class MensajeRepositoryTest {

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
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() {
    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() {
    }

}