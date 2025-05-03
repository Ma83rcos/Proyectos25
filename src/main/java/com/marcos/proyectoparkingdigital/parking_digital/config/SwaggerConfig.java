package com.marcos.proyectoparkingdigital.parking_digital.config;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Configura un grupo de API para Swagger
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")  // Nombre del grupo de APIs, por ejemplo "public"
                .packagesToScan("com.marcos.proyectoparkingdigital")  // Escanea los paquetes para encontrar los controladores
                .pathsToMatch("/**")  // Expone todas las rutas de tu API
                .build();
    }
}
