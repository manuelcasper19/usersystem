package com.system.user.usersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    //Aplicamos configuración de cors para hacer peticiones desde la url de angular
    //Si la api crece acetará todas las peticiones que venga precedidas de /api/
    @Bean
   public WebMvcConfigurer corsConfigure(){
       return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/login")
                       .allowedOrigins("http://localhost:4200")
                       .allowedMethods("*")
                       .exposedHeaders("*");
               registry.addMapping("/api/**")
                       .allowedOrigins("http://localhost:4200")
                       .allowedMethods("*");
           }
       };

   }
}
