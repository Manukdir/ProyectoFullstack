package com.example.ms_envios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * Define la información general que muestra Swagger para este servicio.
 */
public class OpenApiConfig {

    @Bean
    public OpenAPI enviosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ms-envios")
                        .version("1.0")
                        .description("Documentacion OpenAPI para envios y seguimientos"));
    }
}
