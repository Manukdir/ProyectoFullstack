package com.example.ms_productos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura el título y la descripción mostrados en Swagger UI.
 * Swagger queda disponible en: http://localhost:8082/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ms-productos")
                        .version("1.0")
                        .description("Documentacion OpenAPI para productos y categorias"));
    }
}
