package com.banana.bananawhatsapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({ReposConfig.class, ServiciosConfig.class, PropertiesConfig.class, PropertiesConfigDev.class})
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistencia", "com.banana.bananawhatsapp.servicios", "com.banana.bananawhatsapp.controladores"})
//@PropertySource("classpath:application.properties","classpath:application-dev.properties") -- no me deja meter dos
public class SpringConfig {
}
