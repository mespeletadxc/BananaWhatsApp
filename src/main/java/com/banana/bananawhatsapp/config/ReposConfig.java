package com.banana.bananawhatsapp.config;


import com.banana.bananawhatsapp.persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class ReposConfig {


    @Autowired
    Environment env;

    @Value("${db.conn}")
    String dbUrl;

    @Bean
    public DBConnector createDBConnector() {
        return new DBConnector();
    }


    @Bean
    @Profile("default")
    public IMensajeRepository getMensajeRepository() {


        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        MensajeJDBCRepository repo = new MensajeJDBCRepository();
        repo.setUrlConn(dbUrl);
        return repo;
    }

    @Bean
    @Profile("dev")
    public IMensajeRepository getMensajeRepositoryDEV() {

        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        MensajeRepositoryInMemory repo = new MensajeRepositoryInMemory();
        repo.setUrlConn(dbUrl);
        return repo;
    }

    @Bean
    @Profile("default")
    public IUsuarioRepository getUsuarioRepository() {


        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        UsuarioJDBCRepository repo = new UsuarioJDBCRepository();
        repo.setUrlConn(dbUrl);
        return repo;
    }

    @Bean
    @Profile("dev")
    public IUsuarioRepository getUsuarioRepositoryDEV() {

        String dbUrlEnv = env.getProperty("db.conn", String.class);
        System.out.println("dbUrlEnv:" + dbUrlEnv);

        UsuarioRepositoryInMemory repo = new UsuarioRepositoryInMemory();
        repo.setUrlConn(dbUrl);
        return repo;
    }
}
