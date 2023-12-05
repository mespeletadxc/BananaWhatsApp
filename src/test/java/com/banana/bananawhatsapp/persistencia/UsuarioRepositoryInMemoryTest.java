package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@ActiveProfiles("dev")
class UsuarioRepositoryInMemoryTest {

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

}