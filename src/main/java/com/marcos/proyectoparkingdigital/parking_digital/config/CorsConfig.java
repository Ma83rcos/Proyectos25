package com.marcos.proyectoparkingdigital.parking_digital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Replace with your API endpoint
                        .allowedOrigins("*", "http://localhost:3000")
                        //                        .allowedOrigins("https://frontendgrado.netlify.app")
                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false) // Permitir cookies
                        .exposedHeaders("Authorization");
            }
        };
    }
}


