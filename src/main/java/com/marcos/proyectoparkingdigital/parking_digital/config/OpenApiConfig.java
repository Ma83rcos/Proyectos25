package com.marcos.proyectoparkingdigital.parking_digital.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "parking",
                        email = "parking@gmail.com",
                        url = "https://parking.com"

                ),
                title="Parkin-digital Ejemplos - Documentación",
                description ="OpenAPI documentation para RESTful API",
                version = "1.0",
                termsOfService = "Uso restringido a pruebas del proyecto de parking-digital"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://127.0.0.1:8080"
                )
        }
)
public class OpenApiConfig {
}
